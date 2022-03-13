package it.marczuk.invoicemanager.infrastructure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"it.marczuk.invoicemanager.infrastructure.persistance.database"})
@EntityScan(basePackages = "it.marczuk.invoicemanager.infrastructure.persistance.database")
@ComponentScan(basePackages = {"it.marczuk.invoicemanager.infrastructure"})
public class InvoiceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceManagerApplication.class, args);
    }

}
