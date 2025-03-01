package org.liaolong.base.utils;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 文件配置加载-热更新
 *
 * @author ll
 * @since 2025-03-01 15:56
 */
public class ConfigLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigLoader.class);

    private static ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration> builder;

    private static final String DEFAULT_CONFIG_FILE = "service.config.properties";

    public static void initDefault() {
        init(DEFAULT_CONFIG_FILE);
    }

    /**
     * 初始化文件配置
     *
     * @param filePath 文件路径
     */
    public static void init(String filePath) {
        try {
            // 配置文件路径
            Parameters params = new Parameters();
            File file = new File(filePath);

            // 创建可重新加载的配置构造器
            builder = new ReloadingFileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                    .configure(params.fileBased().setFile(file));

            // 启动定时检查，每 30秒检查一次
            PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(
                    builder.getReloadingController(),
                    null,  // 使用默认的ScheduledExecutorService
                    30,      // 检查间隔
                    TimeUnit.SECONDS);
            trigger.start();
        } catch (Exception e) {
            throw new RuntimeException("初始化配置失败", e);
        }
    }

    /**
     * 获取配置值
     *
     * @param key 配置键
     * @return 配置值，若不存在返回空串
     */
    public static String getString(String key) {
        try {
            // 获取当前配置实例
            return builder.getConfiguration().getString(key, StringUtils.EMPTY);
        } catch (ConfigurationException e) {
            LOGGER.error("getString occur an exception: {}", e.getMessage());
            return StringUtils.EMPTY;
        }
    }
}
