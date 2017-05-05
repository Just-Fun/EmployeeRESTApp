package ua.com.serzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Properties;

@SpringBootApplication
public class EmployeeRestAppApplication {
    // Get PORT and HOST from Environment or set default
    private static final Optional<String> host;
    private static final Optional<String> port;
    private static final Properties myProps = new Properties();

    static {
        host = Optional.ofNullable(System.getenv("HOSTNAME"));
        port = Optional.ofNullable(System.getenv("PORT"));
    }

    public static void main(String[] args) {
//		SpringApplication.run(EmployeeRestAppApplication.class, args);

        // Set properties
        myProps.setProperty("server.address", host.orElse("localhost"));
        myProps.setProperty("server.port", port.orElse("8083"));

        SpringApplication app = new SpringApplication(EmployeeRestAppApplication.class);
        app.setDefaultProperties(myProps);
        app.run(args);

    }
}
