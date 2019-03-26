package digilog;

import java.sql.SQLException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    SQLdatabase database;
    
    //use this method to do a basic text interface
    public void run(Scanner reader) throws SQLException, ClassNotFoundException{
        System.out.println("\nWelcome to Digilog version 0.03\n");
        while(true){
            System.out.println("----------------------------------------------------"
                    + "\n"
                    + "Commands:\n"
                    + "\t x -- stops program\n"
                    + "\t ? -- lists commands\n"
                    + "\t 1 -- add media\n"
                    + "\t 2 -- remove media\n"
                    + "\t 3 -- list media\n"
                    + "\t 4 -- change settings\n");
            String command = reader.nextLine();
            if(command.equals("x")){break;}
            else if(command.equals("?")){}
            else if(command.equals("tyhjaSQLdatabase")){
                database = new SQLdatabase();
                database.createEmptyDatabase();
            }
            else if(command.equals("4")){changeSettings(reader);}
            else{System.out.println("command not recognized");;}
            
        }
    }
    
    public void changeSettings(Scanner reader){
        int mediatypecount = database.getMediaTypeCount();
        int genretypecount = database.getGenreTypeCount();
        
        while(true){
            System.out.println("----------------------------------------------------\n"
                + "\tSettings you can change are:\n"
                + "\t\tMediatypes: you currently have ("+mediatypecount+") different media types.\n"
                + "\t\tGenretypes: you currently have ("+genretypecount+") different genre types.\n\n"
                + "\t x -- go back\n"
                + "\t 1 -- add mediatype\n"
                + "\t 2 -- add genretype\n"
                + "\t 3 -- list mediatypes\n"
                + "\t 4 -- list genretypes\n");
            String command = reader.nextLine();
            if(command.equals("x")){break;}
            else if(command.equals("1")){
                System.out.println("\t\t give new mediatype name (Book, Movie etc.)\n");
                String tName = reader.nextLine();
                database.addMediatype(tName);
            }
            else if(command.equals("2")){
                System.out.println("\t\t give new genre name (comedy, drama etc.)\n");
                String gName = reader.nextLine();
                database.addMediatype(gName);
            }
        }
        
    }
    
    /*
    methods here to do the user input logic
    */
    
}

