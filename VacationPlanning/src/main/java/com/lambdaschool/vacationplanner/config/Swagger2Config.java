package com.lambdaschool.vacationplanner.config;

import com.fasterxml.classmate.TypeResolver;
import com.lambdaschool.vacationplanner.models.ErrorDetail;
import com.lambdaschool.vacationplanner.models.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:2019/swagger-ui.html
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config
{
    @Autowired
    private TypeResolver resolver;

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
                //                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.lambdaschool"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false) // Allows only my exception responses
                .ignoredParameterTypes(Pageable.class) // allows only my paging parameter list
                .apiInfo(apiEndPointsInfo())
                .pathMapping("/")
                .additionalModels(resolver.resolve(TokenModel.class),
                        resolver.resolve(ErrorDetail.class)) // these needed to be added, they weren't showing up
                .ignoredParameterTypes(SimpleGrantedAuthority.class); // used internally, dont need to see it
    }

    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("Java Spring Back End Starting Project")
                .description("A starting application for developing Java Spring Back End Projects")
                .contact(new Contact("Diana Grams",
                        "http://bw-vacaplanning.herokuapp.com",
                        "deegrams221@gmail.com"))
                .license("MIT")
                .licenseUrl("https://github.com/Build-Week-Vacation-Planning/Back-End/blob/master/LICENSE")
                .version("1.0.0")
                .build();
    }
}
