package iuh.kttkpm.nhom11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CloudConfigServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }

    /*
    config cho việc build file war
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CloudConfigServerApplication.class);
    }

}
