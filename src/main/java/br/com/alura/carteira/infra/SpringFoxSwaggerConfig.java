package br.com.alura.carteira.infra;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxSwaggerConfig {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Carteira API", 
				"Documentacao da API Carteira de Investimentos",
				"1.0",
				"urn:tos",
				new Contact(
						"Fulano da Silva Dev Jr.", 
						"www.sitedofulanodevjr.com.br", 
						"fulano@email.com.br"),
			      "Apache 2.0",
			      "http://www.apache.org/licenses/LICENSE-2.0",
			      new ArrayList<>());
	}

}
