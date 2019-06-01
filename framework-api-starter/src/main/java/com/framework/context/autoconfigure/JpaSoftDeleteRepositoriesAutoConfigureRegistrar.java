package com.framework.context.autoconfigure;

import com.framework.annotation.EnableJpaSoftDeleteRepositories;
import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Locale;

/**
 * @author zhiqiang.huang
 */
public class JpaSoftDeleteRepositoriesAutoConfigureRegistrar
        extends AbstractRepositoryConfigurationSourceSupport {

    private BootstrapMode bootstrapMode = null;

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableJpaSoftDeleteRepositories.class;
    }

    @Override
    protected Class<?> getConfiguration() {
        return JpaSoftDeleteRepositoriesAutoConfigureRegistrar.EnableJpaRepositoriesConfiguration.class;
    }

    @Override
    protected RepositoryConfigurationExtension getRepositoryConfigurationExtension() {
        return new JpaRepositoryConfigExtension();
    }

    @Override
    protected BootstrapMode getBootstrapMode() {
        return (this.bootstrapMode == null) ? super.getBootstrapMode()
                : this.bootstrapMode;
    }

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
        configureBootstrapMode(environment);
    }

    private void configureBootstrapMode(Environment environment) {
        String property = environment
                .getProperty("spring.data.jpa.repositories.bootstrap-mode");
        if (StringUtils.hasText(property)) {
            this.bootstrapMode = BootstrapMode
                    .valueOf(property.toUpperCase(Locale.ENGLISH));
        }
    }

    @EnableJpaSoftDeleteRepositories
    private static class EnableJpaRepositoriesConfiguration {

    }

}