package com.framework.context.autoconfigure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

/**
 * @author zhiqiang.huang
 */
public class SpringApplicationPostProcessor implements EnvironmentPostProcessor {
    private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication springApplication) {
        Resource path = new ClassPathResource("bootstrap.properties");

        List<PropertySource<?>> propertySourceList = loadProps(path);
        for (PropertySource<?> propertySource : propertySourceList) {
            environment.getPropertySources().addFirst(propertySource);
        }
    }

    private List<PropertySource<?>> loadProps(Resource path) {
        if (!path.exists()) {
            throw new IllegalArgumentException("Resource " + path + " does not exist");
        }
        try {
            return this.loader.load("custom-resource", path);
        } catch (IOException ex) {
            throw new IllegalStateException(
                    "Failed to load props configuration from " + path, ex);
        }
    }
}
