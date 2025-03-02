package org.liaolong.base.api.filemanager.file.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件上传结果
 *
 * @author ll
 * @since 2025-03-02 11:11
 */
@Setter
@Getter
@Builder
public class UploadFileResult {
    private String uploadUrl;
}
