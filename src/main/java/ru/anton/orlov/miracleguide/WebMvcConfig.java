package ru.anton.orlov.miracleguide;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Author:      oav <br>
 * Date:        19.10.15, 19:07 <br>
 * Company:     SofIT labs <br>
 * Revision:    $Id$ <br>
 * Description: <br>
 */
//@Configuration
//@EnableMustache(provider = MustacheProvider.AUTO)
//@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("WEB-INF/pages/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
//@Bean
//public ViewResolver getViewResolver(ResourceLoader resourceLoader) {
//    MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
//    mustacheViewResolver.setPrefix("/WEB-INF/views/");
//    mustacheViewResolver.setSuffix(".html");
//    mustacheViewResolver.setCache(false);
//    mustacheViewResolver.setContentType("text/html;charset=utf-8");
//
//    MustacheTemplateLoader mustacheTemplateLoader = new MustacheTemplateLoader();
//    mustacheTemplateLoader.setResourceLoader(resourceLoader);
//
//    mustacheViewResolver.setTemplateLoader(mustacheTemplateLoader);
//    return mustacheViewResolver;
//}


}
