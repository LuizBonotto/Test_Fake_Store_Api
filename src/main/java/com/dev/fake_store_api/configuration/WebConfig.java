//package com.dev.fake_store_api.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Description;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.spring6.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    @Description("Thymeleaf template resolver serving HTML 5")
//    public ClassLoaderTemplateResolver templateResolver() {
//        var templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("mytemplates/"); // Pasta onde estão os templates
//        templateResolver.setCacheable(false);
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML");
//        templateResolver.setCharacterEncoding("UTF-8");
//        return templateResolver;
//    }
//
//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
//        var viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine);
//        return viewResolver;
//    }
//}
