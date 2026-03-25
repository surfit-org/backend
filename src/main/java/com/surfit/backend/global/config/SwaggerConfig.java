package com.surfit.backend.global.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI surfitOpenApi() {
		// 보안 스키마 설정 (JWT 인증)
		String jwtScheme = "jwtAuth";
		SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtScheme);
		Components components = new Components()
			.addSecuritySchemes(jwtScheme, new SecurityScheme()
				.name(jwtScheme)
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT"));

		// 서버 접속 설정(후에 다른 url 추가 가능)
		Server localServer = new Server();
		localServer.setUrl("http://localhost:8080");
		localServer.setDescription("로컬 서버");

		return new OpenAPI()
			.info(new Info()
				.title("Surfit API 명세")
				.description("Surfit 백엔드 API 문서입니다.")
				.version("v1.0.0"))
			.addSecurityItem(securityRequirement)
			.components(components)
			.servers(List.of(localServer));
	}
}
