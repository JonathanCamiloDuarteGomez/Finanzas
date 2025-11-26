package com.Davivienda.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

@Bean
public OpenAPI apiDocs() {
	
	return new OpenAPI()
			       .info(new Info()
					             .title("API Finanzas Personales")
					             .version("1.0")
					             .description(
							             "Documentación oficial del sistema de finanzas personales.\n\n" +
									             "### Orden recomendado de uso:\n" +
									             "1. **Crear usuario** (`POST /api/usuarios/registro`)\n" +
									             "2. **Login** (`POST /auth/login`) → obtener JWT\n" +
									             "3. **Crear categorías** (`/api/categorias/...`)\n" +
									             "4. **Registrar transacciones** (`/api/transacciones/...`)\n" +
									             "5. **Consultar saldo** (`GET /api/usuarios/{id}/saldo`)"
					             )
					             .contact(new Contact()
							                      .name("Jonathan Duarte")
							                      .email("soporte@finanzas.com")
					             )
			       )
			       
			       // 🔐 Agregar requisito de seguridad global
			       .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
			       
			       // 🔐 Definir esquema de seguridad JWT
			       .components(new io.swagger.v3.oas.models.Components()
					                   .addSecuritySchemes("BearerAuth",
							                   new SecurityScheme()
									                   .type(SecurityScheme.Type.HTTP)
									                   .scheme("bearer")
									                   .bearerFormat("JWT")
					                   )
			       );
}
}
