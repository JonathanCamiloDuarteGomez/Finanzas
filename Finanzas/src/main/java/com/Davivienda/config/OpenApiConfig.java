package com.Davivienda.config;




import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class OpenApiConfig {

@Bean
public OpenAPI apiInfo() {
	return new OpenAPI()
			       .info(new Info()
					             .title("API de Finanzas Personales")
					             .description("Documentación oficial de los endpoints del sistema de finanzas personales.")
					             .version("1.0.0")
					             .contact(new Contact()
							                      .name("Jonathan Duarte")
							                      .email("soporte@finanzas.com")
					             )
			       )
			       .servers(List.of(
					       new Server().url("http://localhost:8080").description("Servidor local")
			       ))
			       .tags(List.of(
					       new Tag().name("Usuarios").description("Gestión de usuarios y perfil"),
					       new Tag().name("Categorías").description("Administración de categorías financieras"),
					       new Tag().name("Transacciones").description("CRUD de transacciones")
			       ));
}
}
