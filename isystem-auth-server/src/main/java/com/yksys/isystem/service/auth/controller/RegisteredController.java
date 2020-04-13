package com.yksys.isystem.service.auth.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.yksys.isystem.common.core.annotation.ActionLog;
import com.yksys.isystem.common.core.constants.*;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.exception.ParameterException;
import com.yksys.isystem.common.core.utils.AppUtil;
import com.yksys.isystem.common.core.utils.ObjectConvertUtil;
import com.yksys.isystem.common.core.utils.RedisUtil;
import com.yksys.isystem.common.core.utils.StringUtil;
import com.yksys.isystem.common.model.EmailTplMessage;
import com.yksys.isystem.common.model.register.EmailRegister;
import com.yksys.isystem.common.pojo.SystemUser;
import com.yksys.isystem.service.auth.service.SystemUserInfoService;
import com.yksys.isystem.service.auth.service.feign.EmailService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @program: YK-iSystem
 * @description: 用户注册controller
 * @author: YuKai Fan
 * @create: 2020-04-09 10:53
 **/
@Slf4j
@Api(tags = "用户注册管理")
@RestController
@RequestMapping("/api/registered")
public class RegisteredController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SystemUserInfoService systemUserInfoService;
    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * 用户邮箱注册
     *
     * @param register
     * @return
     */
    @ApiOperation("用户邮箱注册")
    @PostMapping("/emailRegistered")
    @ActionLog(logType = LogTypeEnum.USER_EMAIL_REGISTERED)
    public Result emailRegistered(@RequestBody @ApiParam(name = "邮箱注册实体", required = true) @Valid EmailRegister register,
                                  BindingResult result) {
        //参数验证
        validateParam(register, result);

        //判断用户名是否存在
        Map<String, Object> map = Maps.newHashMap();
        map.put("email", register.getEmail());
        map.put("userName", register.getUserName());
        boolean b = systemUserInfoService.checkUserIsExist(map);
        if (b) {
            throw new ParameterException("该邮箱或用户名已存在!请登录");
        }

        String error = null;
        try {
            //密码加密
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodePwd = passwordEncoder.encode(register.getPassword());

            //保存信息
            SystemUser systemUser = new SystemUser();
            systemUser.setAccount(register.getEmail());
            systemUser.setPassword(encodePwd);
            systemUser.setEmail(register.getEmail());
            systemUser.setUserName(register.getUserName());
            systemUserInfoService.addSystemUser(systemUser);

            return new Result(HttpStatus.OK.value(), "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("注册失败: {}", e.getMessage());
            error = e.getMessage();
        } finally {
            String subject;
            String content;
            EmailTplMessage message = new EmailTplMessage();
            message.setRecipients(StringUtil.delimitedListToStringArray(register.getEmail(), ";"));
            message.setTplCode(TemplateConstant.EMAIL_REGISTER_CALLBACK);
            if (StringUtil.isBlank(error)) {
                subject = "YKSystem注册成功";
                content = "恭喜" + register.getUserName() + ", 您已成功注册YKSystem账号, 赶快登陆体验吧!!!";
            } else {
                subject = "YKSystem注册失败";
                content = String.format("恭喜" + register.getUserName() + ", 您注册YKSystem账号失败了, 请重新申请! 如果还是失败, 请联系管理员 (发送邮件到 %s%)!", ComConstants.ADMIN_EMAIL);
            }
            message.setSubject(subject);
            Map<String, Object> tplParamMap = Maps.newHashMap();
            tplParamMap.put("content", content);
            tplParamMap.put("userName", register.getUserName());
            tplParamMap.put("adminEmail", ComConstants.ADMIN_EMAIL);
            message.setTplParams(tplParamMap);

            amqpTemplate.convertAndSend(RabbitConstant.REGISTER_CALLBACK_EMAIL_NOTIFY_EXCHANGE,
                    RabbitConstant.REGISTER_CALLBACK_EMAIL_NOTIFY_QUEUE_RK, ObjectConvertUtil.handleObjectToBytes(message));

        }

        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册失败");
    }

    /**
     * 发送验证邮箱
     *
     * @param email
     * @param userName
     */
    @ApiOperation("发送验证邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", required = true, value = "注册邮箱", paramType = "string"),
            @ApiImplicitParam(name = "userName", required = true, value = "用户名", paramType = "string")
    })
    @PostMapping("/sendRegisterEmail")
    @ActionLog(logType = LogTypeEnum.USER_SEND_REGISTER_EMAIL)
    public Result sendRegisterEmail(@RequestParam String email, @RequestParam String userName) {
        try {
            Map<String, String> map = Maps.newHashMap();
            map.put("adminEmail", ComConstants.ADMIN_EMAIL);
            map.put("userName", userName);
            //随机生成6位数验证码
            String checkCode = AppUtil.getCheckCode();
            map.put("verificationCode", checkCode);
            //存入redis
            redisUtil.set(email, checkCode, RedisConstants.CHECK_CODE_EXPIRE_TIME);

            String tplParams = JSON.toJSONString(map);
            emailService.sendTplEmail(email, null, "YKSystem 注册验证码", TemplateConstant.EMAIL_REGISTER_VERIFICATION, tplParams, null);
            return new Result(HttpStatus.OK.value(), "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送注册验证邮件失败: {}", e.getMessage());
        }
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "发送失败");
    }


    private void validateParam(EmailRegister register, BindingResult result) {
        if (result.hasErrors()) {
            throw new ParameterException(result.getFieldError().getDefaultMessage());
        }
        //验证码是否正确
        if (StringUtil.isBlank(redisUtil.get(register.getEmail()))) {
            throw new ParameterException("该验证码已过期, 请重新发送!");
        }

        String checkCodeByRedis = (String) redisUtil.get(register.getEmail());
        if (!checkCodeByRedis.equals(register.getCheckCode())) {
            throw new ParameterException("验证码不正确, 请重新输入!");
        }
    }

}