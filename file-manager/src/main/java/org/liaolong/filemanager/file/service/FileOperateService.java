package org.liaolong.filemanager.file.service;

import org.liaolong.base.api.filemanager.file.data.UploadFileResult;
import org.liaolong.base.api.filemanager.file.req.UploadFileRequest;
import org.liaolong.base.oss.huawei.ObsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ll
 * @since 2025-03-02 11:15
 */
@Service
public class FileOperateService {
    private String basePath = "";

    @Resource
    private ObsService obsService;

    public UploadFileResult uploadFile(UploadFileRequest request) {
        String objectKey = String.join("/", basePath, request.getPath(), request.getFileName());
        String tempUploadUrl = obsService.getTempUploadUrl("zfile-liaolong", objectKey);
        return UploadFileResult.builder().uploadUrl(tempUploadUrl).build();
    }
}
