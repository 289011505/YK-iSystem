package com.yksys.isystem.common.core.configure.auto;

import com.google.common.collect.Lists;
import com.yksys.isystem.common.core.swagger.YkSwaggerProperties;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.util.Collections;
import java.util.List;

/**
 * @program: YK-iSystem
 * @description: swagger自动配置
 * @author: YuKai Fan
 * @create: 2020-04-07 14:03
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties({YkSwaggerProperties.class})
@ConditionalOnProperty(prefix = "isystem.swagger2", name = "enabled", havingValue = "true")
@Import({Swagger2DocumentationConfiguration.class})
public class SwaggerAutoConfiguration {
    private YkSwaggerProperties ykSwaggerProperties;
    private MessageSource messageSource;

    public SwaggerAutoConfiguration(YkSwaggerProperties ykSwaggerProperties, MessageSource messageSource) {
        this.ykSwaggerProperties = ykSwaggerProperties;
        this.messageSource = messageSource;
        log.info("SwaggerProperties [{}]", ykSwaggerProperties);
    }

    @Bean
    public Docket createRestApi() {
        //添加header参数
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = Lists.newArrayList();
        parameterBuilder.name("Authorization").description("令牌").modelRef(new ModelRef("string"))
                .parameterType("header").required(true).build();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters)
                .securitySchemes(Collections.singletonList(securityScheme()));
    }

    /**
     * oauth2配置
     * 需要增加swagger授权回调地址
     * http://localhost:8888/webjars/springfox-swagger-ui/o2c.html
     * @return
     */
    @Bean
    SecurityScheme securityScheme() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(ykSwaggerProperties.getTitle())
                .description(ykSwaggerProperties.getDescription())
                .version(ykSwaggerProperties.getVersion())
                .build();
    }


}