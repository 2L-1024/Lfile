package org.liaolong.filemanager.file.service;

import com.obs.services.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.liaolong.base.api.ApiResponse;
import org.liaolong.base.api.filemanager.file.data.UploadFileResult;
import org.liaolong.base.api.filemanager.file.req.CreateDirRequest;
import org.liaolong.base.api.filemanager.file.req.UploadFileRequest;
import org.liaolong.base.oss.huawei.ObsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;

/**
 * @author ll
 * @since 2025-03-02 11:15
 */
@Service
public class FileOperateService {
    /**
     * TODO: 接入数据库后改为配置项
     */
    private String bucketName = "zfile-liaolong";

    @Resource
    private ObsService obsService;

    public ApiResponse<Void> mkdir(CreateDirRequest request) {
        String objectKey = String.join("/", request.getParentPath(),
                request.getDirname(), "");
        obsService.putObject(bucketName, objectKey, new ByteArrayInputStream(new byte[0]));
        return ApiResponse.success();
    }

    public UploadFileResult uploadFile(UploadFileRequest request) {
        String objectKey = String.join("/", request.getPath(), request.getFileName());
        String tempUploadUrl = obsService.getTempUploadUrl(bucketName, objectKey);
        return UploadFileResult.builder().uploadUrl(tempUploadUrl).build();
    }
}
