package com.stacksimply.restservices.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.stacksimply.restservices"))//only our Application COntroller will be getting displyed otherwise Spring framwork details is also coming
				.paths(PathSelectors.ant("/users/**")).build();//only Controller for which requestmapping is starting with users
	}
	 
	//Swagger Metadata http://localhost:8080/v2/api-docs
	//Swagger UI URL http://localhost:8080/swagger-ui.html
	//https://editor.swagger.io/ to verify json  => copy the content between of http://localhost:8080/v2/api-docs
	//while copying dont copy<json> tags and then check for the error , which means that our API is not "Open Api Complient" as opn api.
	//here we got error while returning optional bject from getuser api so for that we need to return User object not the optional user.
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("User Management Service")
				.description("This Page list all APIS's of user managment service")
				.version("2.0")
				.contact(new Contact("Lucky Sadhwani", "http://localhost:8080/swagger-ui.html", "sadhwanilucky@gmail.com"))
				.license("3.0")
				.licenseUrl("http://localhost:8080/license.html")
				.build();
	}
	

}
