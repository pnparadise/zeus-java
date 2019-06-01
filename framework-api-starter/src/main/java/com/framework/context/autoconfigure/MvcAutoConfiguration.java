package com.framework.context.autoconfigure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.framework.annotation.EnableJpaSoftDeleteRepositories;
import com.framework.context.handler.JsonEntityResponseHandler;
import com.framework.jpa.respository.JpaSoftDeleteRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * @author zhiqiang.huang
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MvcAutoConfiguration implements WebMvcConfigurer {

    @Bean
    public ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        FormattingConversionService conversionService = new WebConversionService(JSON.DEFFAULT_DATE_FORMAT);
        new DateFormatterRegistrar().registerFormatters(conversionService);
        //we can add our custom converters and formatters
        // conversionService.addConverterFactory(new CaseInsensitiveStringToEnumConverterFactory());
        //conversionService.addFormatter(...);
        initializer.setConversionService(conversionService);
        //we can set our custom validator
        //initializer.setValidator(....);

        //here we are setting a custom PropertyEditor
        return initializer;
    }

    @Bean
    public JsonEntityResponseHandler jsonResponseHandler() {
        return new JsonEntityResponseHandler();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        //config.set ...
        config.setDateFormat(JSON.DEFFAULT_DATE_FORMAT);
        config.setSerializerFeatures(SerializerFeature.WriteBigDecimalAsPlain);
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
        converter.setFastJsonConfig(config);
        converter.setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_JSON_UTF8,
                MediaType.APPLICATION_JSON));
        converters.add(0, converter);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders(ALL)
                .allowedMethods(ALL)
                .allowedOrigins(ALL);
    }




}
