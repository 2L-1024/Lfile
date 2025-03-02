package org.liaolong.base.api.filemanager.file.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author ll
 * @since 2025-03-02 17:53
 */
@Setter
@Getter
@Builder
public class FileListResult {
    private String dir;

    private List<FileAttr> fileList;

    @Setter
    @Getter
    @Builder
    public static class FileAttr {
        private String objectKey;

        private String owner;
    }
}
