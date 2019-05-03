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
import digilog.user.TextInterface;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigilogSovellus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DigilogSovellus.class);
    }

    @Autowired
    TextInterface tekstikayttoliittyma;

    @Override
    public void run(String... args) throws Exception {
        Scanner reader = new Scanner(System.in);
        tekstikayttoliittyma.run(reader);
    }
}