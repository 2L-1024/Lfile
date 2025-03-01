package org.liaolong.base.oss.huawei;

import com.obs.services.ObsClient;
import org.liaolong.base.utils.AesUtils;
import org.liaolong.base.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Huawei OBS 服务
 *
 * @author ll
 * @since 2025-03-01 15:13
 */
public class ObsService implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ObsService.class);

    private static ObsClient obsClient;

    private static final String OBS_ENDPOINT_KEY = "obs.endpoint";

    private static final String OBS_AK_KEY = "obs.ak";

    private static final String OBS_SK_KEY = "obs.sk";

    private static final String OBS_AES_SECRET_KEY = "OBS_AES_SECRET_KEY";

    @Override
    public void afterPropertiesSet() throws Exception {
        initClient();
    }

    private void initClient() throws Exception {
        String endPoint = ConfigLoader.getString(OBS_ENDPOINT_KEY);
        String ak = AesUtils.decrypt(System.getenv(OBS_AES_SECRET_KEY), ConfigLoader.getString(OBS_AK_KEY));
        String sk = AesUtils.decrypt(System.getenv(OBS_AES_SECRET_KEY), ConfigLoader.getString(OBS_SK_KEY));
        obsClient = new ObsClient(ak, sk, endPoint);
        LOGGER.info("obs client init successfully!");
    }
}
