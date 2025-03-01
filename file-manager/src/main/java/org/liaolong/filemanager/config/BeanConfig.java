package org.liaolong.filemanager.config;

import org.liaolong.base.oss.huawei.ObsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ll
 * @since 2025-03-01 17:51
 */
@Configuration
public class BeanConfig {
    @Bean
    public ObsService obsService() {
        return new ObsService();
    }
}
