/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digilog;

/**
 *
 * @author kalmikko
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class DigilogSovellus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DigilogSovellus.class);
    }

    @Autowired
    Interface tekstikayttoliittyma;

    @Override
    public void run(String... args) throws Exception {
        Scanner reader = new Scanner(System.in);
        tekstikayttoliittyma.run(reader);
    }
}