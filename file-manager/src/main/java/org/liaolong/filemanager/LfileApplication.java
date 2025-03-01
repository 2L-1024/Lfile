package org.liaolong.filemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {
        "org.liaolong.filemanager"
})
public class LfileApplication {
    public static void main( String[] args ){
        SpringApplication.run(LfileApplication.class, args);
    }
}
