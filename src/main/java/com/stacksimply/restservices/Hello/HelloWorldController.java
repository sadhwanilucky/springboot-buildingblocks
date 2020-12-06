package com.stacksimply.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import aj.org.objectweb.asm.Label;

@RestController
public class HelloWorldController {
	@Autowired
	private ResourceBundleMessageSource messageSource;
//	@RequestMapping(method = RequestMethod.GET,path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello Wolrd From Lucky";
	}

	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Lucky", "Sadhwani", "Pune");
	}
	
	@GetMapping("/hello-int")
	public String getMessagesInI18nFormat(@RequestHeader(name="Accept-Language",required = false) String locale) {
		return messageSource.getMessage("label.hello",null,new  Locale(locale));
	}
}
