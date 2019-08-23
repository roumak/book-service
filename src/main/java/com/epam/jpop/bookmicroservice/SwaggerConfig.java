package com.epam.jpop.bookmicroservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final Contact AUTHOR_CONTACT;
    private static final HashSet<String> PRODUCES_CONSUMES= new HashSet<>();

    static {
        AUTHOR_CONTACT = new Contact("Roumak Chakraborty",
                "https://bitbucket.org/account/user/jpopepam/projects/JPOP",
                "rc.roumak@gmail.com");

        PRODUCES_CONSUMES.add("application/json");
    }

    @Bean
    public Docket apiDoc(){
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiMetaData())
                .produces(PRODUCES_CONSUMES)
                .consumes(PRODUCES_CONSUMES);
    }

    private ApiInfo apiMetaData() {
        return new ApiInfo("Book service","It is a user microservice that interact with other components",
                "v1.0","",AUTHOR_CONTACT,"Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }

}
