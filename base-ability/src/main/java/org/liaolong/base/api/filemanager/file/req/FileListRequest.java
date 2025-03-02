package org.liaolong.base.api.filemanager.file.req;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ll
 * @since 2025-03-02 11:13
 */
@Setter
@Getter
public class FileListRequest {
    private String bucketName;

    private String dir;
}
