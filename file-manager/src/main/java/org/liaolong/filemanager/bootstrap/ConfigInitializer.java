package org.liaolong.filemanager.bootstrap;

import org.liaolong.base.utils.ConfigLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author ll
 * @since 2025-03-01 17:22
 */
public class ConfigInitializer implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ConfigLoader.initDefault();
    }
}
