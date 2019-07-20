package control.sun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@EnableRetry    // 开启Retry重试
public class SunApplication {

    public static void main(String[] args) {

        SpringApplication.run(SunApplication.class, args);

    }

}
