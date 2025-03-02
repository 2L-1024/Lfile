package org.liaolong.filemanager.file.controller;

import org.liaolong.base.api.ApiResponse;
import org.liaolong.base.api.filemanager.file.data.UploadFileResult;
import org.liaolong.base.api.filemanager.file.req.UploadFileRequest;
import org.liaolong.filemanager.file.service.FileOperateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author ll
 * @since 2025-03-02 9:54
 */
@RestController
@RequestMapping("/api/file")
public class FileOperateController {
    @Resource
    private FileOperateService fileOperateService;


    /**
     * 上传文件
     *
     * @param uploadFileRequest req
     * @return res
     */
    @PostMapping(path = "/upload/file")
    public ApiResponse<UploadFileResult> uploadFile(@Valid @RequestBody UploadFileRequest uploadFileRequest) {
        UploadFileResult uploadFileResult = fileOperateService.uploadFile(uploadFileRequest);
        return ApiResponse.success(uploadFileResult);
    }
}
