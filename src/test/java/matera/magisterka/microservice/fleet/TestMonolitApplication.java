package matera.magisterka.microservice.fleet;

import org.springframework.boot.SpringApplication;

public class TestMonolitApplication {

    public static void main(String[] args) {
        SpringApplication.from(FleetServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
