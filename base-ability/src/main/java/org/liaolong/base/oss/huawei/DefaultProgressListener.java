package org.liaolong.base.oss.huawei;

import com.obs.services.model.ProgressListener;
import com.obs.services.model.ProgressStatus;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 上传进度监听
 *
 * @author ll
 * @since 2025-03-02 9:12
 */
@Getter
public class DefaultProgressListener implements ProgressListener {
    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultProgressListener.class);

    private final String objectKey;

    public DefaultProgressListener(String objectKey) {
        this.objectKey = objectKey;
    }

    @Override
    public void progressChanged(ProgressStatus progressStatus) {
        // 获取上传平均速率
        LOGGER.info("[objectKey: {}], [averageSpeed: {}]", objectKey, progressStatus.getAverageSpeed());
        // 获取上传进度百分比
        LOGGER.info("[objectKey: {}], [transferPercentage:{}]", objectKey, progressStatus.getTransferPercentage());
    }


}
