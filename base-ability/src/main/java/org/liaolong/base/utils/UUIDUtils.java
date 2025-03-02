package org.liaolong.base.utils;

import java.util.UUID;

/**
 * uuid utils
 *
 * @author ll
 * @since 2025-03-02 9:36
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
