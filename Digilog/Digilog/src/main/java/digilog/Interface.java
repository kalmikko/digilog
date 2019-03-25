package digilog;

import java.util.Scanner;
import org.springframework.stereotype.Component;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kalmikko
 */
@Component
public class Interface {
    
    //use this method to do a basic text interface
    public void run(Scanner reader){
        System.out.println("\nWelcome to Digilog version 0.03\n");
        while(true){
            System.out.println("----------------------------------------------------"
                    + "\n\n\nCommands:\n"
                    + "\t x -- stops program\n"
                    + "\t ? -- lists commands");
            String komento = reader.nextLine();
            if(komento.equals("x")){break;}
            else if(komento.equals("?")){}
            else{System.out.println("command not recognized");;}
            
        }
    }
    
    /*
    methods here to do the user input logic
    */
}

