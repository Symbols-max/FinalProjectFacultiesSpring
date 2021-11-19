package org.epam.final_project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan("org.epam.final_project")
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("static/**")
                .addResourceLocations("/WEB-INF/static/");
    }
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/pages/WEB-INF/pages/",".jsp");
//    }

}
