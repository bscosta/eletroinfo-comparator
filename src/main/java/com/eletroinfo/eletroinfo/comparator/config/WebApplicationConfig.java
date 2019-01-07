package com.eletroinfo.eletroinfo.comparator.config;

import com.eletroinfo.eletroinfo.comparator.formatter.BigDecimalFormatter;
import com.eletroinfo.eletroinfo.comparator.formatter.LocalDateFormatter;
import com.eletroinfo.eletroinfo.comparator.interceptors.RunTimeInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.math.BigDecimal;

/**
 * @author Bruno Costa
 */

@Configuration
//@EnableAutoConfiguration(exclude = { SecurityConfig.class })
//@ComponentScan("com.eletroinfo.eletroinfo.comparator.controllers")
@EnableWebMvc
public class WebApplicationConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private RunTimeInterceptor runTimeInterceptor;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public MessageSource messageSource(){
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8"); // codigos para colocar no arquivos de message properties para nao dar erro de encondig //http://www.utf8-chartable.de/
        return messageSource;
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        LocalDateFormatter localDateFormatter = new LocalDateFormatter();
        formatterRegistry.addFormatter(localDateFormatter);

        BigDecimalFormatter bigDecimalFormatter = new BigDecimalFormatter("#,##0.00");
        formatterRegistry.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);
    }

    /**
     * definindo a localizacao padrao para os arquivos estaticos css,js,img
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(runTimeInterceptor);
    }

    @Bean
    public RunTimeInterceptor runTimeInterceptor(){
        return new RunTimeInterceptor();
    }
}
