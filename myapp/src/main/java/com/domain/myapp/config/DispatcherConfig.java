package com.domain.myapp.config;

import no.bouvet.jsonclient.spring.JsonClientJackson2ObjectMapperFactoryBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@ComponentScan("com.domain.myapp")
@EnableWebMvc
public class DispatcherConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new JsonClientJackson2ObjectMapperFactoryBean().getObject());
        converters.add(converter);
        super.configureMessageConverters(converters);
    }
}
