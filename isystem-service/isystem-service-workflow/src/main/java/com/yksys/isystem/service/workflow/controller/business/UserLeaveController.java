package com.yksys.isystem.service.workflow.controller.business;

import com.yksys.isystem.common.core.constants.ComConstants;
import com.yksys.isystem.common.core.dto.DataTableViewPage;
import com.yksys.isystem.common.core.dto.Result;
import com.yksys.isystem.common.core.security.AppSession;
import com.yksys.isystem.common.core.security.YkUserDetails;
import com.yksys.isystem.common.pojo.UserLeave;
import com.yksys.isystem.common.vo.UserLeaveVo;
import com.yksys.isystem.service.workflow.service.UserLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Arrays;


/**
 * @program: YK-iSystem
 * @description: 用户请假申请表
 * @author: YuKai Fan
 * @create: 2020-03-17 16:08:06
 *
 */
@RestController
@RequestMapping("/api/userLeave")
public class UserLeaveController {
    @Autowired
    private UserLeaveService userLeaveService;

    /**
     * 新增用户请假申请表
     * @return
     */
    @PostMapping("/addUserLeave")
    public Result addUserLeave(@RequestBody UserLeaveVo userLeaveVo) {
        UserLeave userLeave = userLeaveVo.convert();
        return new Result(HttpStatus.OK.value(), "新增成功", userLeaveService.addUserLeave(userLeave));
    }

    /**
     * 根据id查询用户请假申请表
     * @param id
     * @return
     */
    @GetMapping("/getUserLeaveById")
    public Result getUserLeaveById(@RequestParam String id) {
        return new Result(HttpStatus.OK.value(), "查询成功", userLeaveService.getUserLeaveById(id));
    }

    /**
     * 更新用户请假申请表
     *
     * @return
     */
    @PutMapping("/editUserLeave")
    public Result editUserLeave(@RequestBody UserLeaveVo userLeaveVo) {
        UserLeave userLeave = userLeaveVo.convert();
        userLeaveService.editUserLeave(userLeave);
        return new Result(HttpStatus.OK.value(), "更新成功");
    }

    /**
     * 根据id删除用户请假申请表
     * @param id
     * @return
     */
    @DeleteMapping("/delUserLeave")
    public Result delUserLeaveById(@RequestParam String id) {
        userLeaveService.delUserLeaveById(id);
        return new Result(HttpStatus.OK.value(), "删除成功");
    }

    /**
     * 根据ids批量删除用户请假申请表
     * @param ids
     * @return
     */
    @DeleteMapping("/delUserLeave/{ids}")
    public Result delUserLeaveByIds(@PathVariable("ids") String[] ids) {
        userLeaveService.delUserLeaveByIs(Arrays.asList(ids));
        return new Result(HttpStatus.OK.value(), "批量删除成功");
    }

    /**
     * 获取所有的用户请假申请表(不分页)
     * @param map 参数
     * @return
     */
    @GetMapping("/getUserLeaves/noPage")
    public Result getUserLeaves(@RequestParam Map<String, Object> map) {
        return new Result(HttpStatus.OK.value(), "获取成功", userLeaveService.getUserLeaves(map));
    }

    /**
     * 获取所有用户请假申请表
     * @param start 开始记录
     * @param pageSize 分页大小
     * @param map 参数
     * @return
     */
    @GetMapping("/getUserLeaves")
    public Result getUserLeaves(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "30") int pageSize,
                                 @RequestParam Map<String, Object> map) {
        YkUserDetails currentUser = AppSession.getCurrentUser();
        if (!currentUser.getRoles().get(0).equals(ComConstants.ROOT_ADMIN)) {
            map.put("userId", AppSession.getCurrentUserId());
        }
        List<Map<String, Object>> list = userLeaveService.getUserLeaves(start, pageSize, map);
        return new Result(HttpStatus.OK.value(), "获取成功", new DataTableViewPage(list));
    }

}
