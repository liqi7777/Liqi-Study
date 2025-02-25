package com.jz.test.redistest.config;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;


/**
 * @author liqi
 * create  2021-10-12 16:38
 */
@Configuration
@Slf4j
public class Knife4jConfiguration {

    // @Bean
    // public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
    //     return openApi -> {
    //         // 全局添加鉴权参数
    //         if (openApi.getPaths() != null) {
    //             openApi.getPaths().forEach((s, pathItem) -> {
    //                 pathItem.readOperations().forEach(operation -> {
    //                     operation.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));
    //                 });
    //             });
    //         }
    //
    //     };
    // }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HIT-IECS")
                        .version("1.0")
                        .description("HIT-IECS接口文档!")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com"))
                )
                .servers(Lists.newArrayList(
                        new Server().url("/iecs").description("通过网关访问的服务"),
                        new Server().url("/").description("直接访问微服务")
                ))
                .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                .components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme()
                        .name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP).scheme("bearer")));
    }

}
