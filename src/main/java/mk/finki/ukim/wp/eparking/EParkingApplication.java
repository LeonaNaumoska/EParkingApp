package mk.finki.ukim.wp.eparking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
public class EParkingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EParkingApplication.class, args);
    }


}
