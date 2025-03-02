package org.liaolong.base.oss.huawei;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.HttpMethodEnum;
import com.obs.services.model.ObsBucket;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.model.PutObjectResult;
import com.obs.services.model.TemporarySignatureRequest;
import com.obs.services.model.TemporarySignatureResponse;
import org.liaolong.base.constant.Constants;
import org.liaolong.base.utils.AesUtils;
import org.liaolong.base.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.InputStream;

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

    /**
     * 创建桶
     *
     * @param bucketName 桶名称
     * @return ObsException
     */
    public ObsBucket createBucket(String bucketName) throws ObsException {
        // 创建桶
        return obsClient.createBucket(bucketName);
    }

    /**
     * 上传文件
     *
     * @param bucketName 桶名
     * @param objectKey  对象key
     * @param uploadFile 待上传的文件
     * @return 上传结果
     */
    public PutObjectResult putObject(String bucketName, String objectKey, File uploadFile) {
        PutObjectRequest request = new PutObjectRequest(bucketName, objectKey, uploadFile);
        request.setProgressListener(new DefaultProgressListener(objectKey));
        request.setProgressInterval(Constants.ONE_MB);
        return obsClient.putObject(request);
    }

    /**
     * 流式上传
     *
     * @param bucketName 桶名
     * @param objectKey 对象key
     * @param input 输入流
     * @return 上传结果
     */
    public PutObjectResult putObject(String bucketName, String objectKey, InputStream input) {
        PutObjectRequest request = new PutObjectRequest(bucketName, objectKey, input);
        request.setProgressListener(new DefaultProgressListener(objectKey));
        request.setProgressInterval(Constants.ONE_MB);
        return obsClient.putObject(request);
    }

    /**
     * 获取临时上传链接，过期时间为一小时
     *
     * @param bucketName 桶名
     * @param objectKey  对象key
     * @return 上传结果
     */
    public String getTempUploadUrl(String bucketName, String objectKey) {
        // 设置过期时间（单位：秒）
        long expireSeconds = 3600;
        // 生成预签名 URL
        TemporarySignatureRequest request = new TemporarySignatureRequest(HttpMethodEnum.PUT, expireSeconds);
        request.setBucketName(bucketName);
        request.setObjectKey(objectKey);
        TemporarySignatureResponse response = obsClient.createTemporarySignature(request);
        return response.getSignedUrl();
    }
}
