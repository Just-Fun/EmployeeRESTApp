package ua.com.serzh.controllers;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.*;
import static com.google.common.collect.Lists.*;
import static springfox.documentation.builders.PathSelectors.*;
import static springfox.documentation.schema.AlternateTypeRules.*;

/**
 * Created by Serzh on 11/21/16.
 */

// Добавил к каждому запросу method = RequestMethod, иначе оно генерит все возможные варианты(PUT, DELETE, POST...)

// Сгенеренное API доступно по ссылке:
// http://localhost:9999/swagger-ui.html

@Configuration
@EnableSwagger2  //Loads the spring beans required by the framework, Enables Springfox swagger 2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) // Docket, Springfox’s, primary api configuration mechanism is initialized for swagger specification 2.0
                .apiInfo(apiInfo())
                .select() // method returns an instance of ApiSelectorBuilder, which provides a way to control the endpoints exposed by Swagger.
                .apis(RequestHandlerSelectors.any())//  allows selection of RequestHandler’s using a predicate. The example here uses an `any predicate (default). Out of the box predicates provided are any, none, withClassAnnotation, withMethodAnnotation and basePackage.
                .paths(Predicates.not(PathSelectors.regex("/error.*")))// Avoiding default basic-error-controller from swagger api
                .paths(PathSelectors.any())// allows selection of Path’s using a predicate. The example here uses an `any predicate (default)
                .build()
        ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("EmployeeRestAppApplication")
                .description("Some DESCRIPTION")
                .version("1.0")
                .termsOfServiceUrl("http://terms-of-services.url")
                .license("Licence Type if need")
                .licenseUrl("http://url-to-license.com")
                .build();
    }
}