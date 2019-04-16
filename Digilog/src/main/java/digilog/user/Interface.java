package digilog.user;

import digilog.sql.SQLdatabase;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        System.out.println("\nWelcome to Digilog version 0.05\n");
        while(true){
            System.out.println("----------------------------------------------------"
                    + "\n"
                    + "\nThis database has: ("+database.getMediaCount("Digilog")
                    +") titles, ("+database.getTypeCount("Digilog")
                    +") mediatypes and ("+database.getGenreCount("Digilog")+") genres."
                    + "\nCommands:\n"
                    + "\t x -- stops program\n"
                    + "\t ? -- lists commands\n"
                    + "\t 1 -- add media\n"
                    + "\t 2 -- remove media\n"
                    + "\t 3 -- list entries\n"
                    + "\t 4 -- change settings\n");
            String command = reader.nextLine();
            if(command.equals("x")){
                System.out.println("See you again!");
                break;}
            else if(command.equals("?")){}
            else if(command.equals("luotaulut")){
                database.createEmptyDatabase("Digilog");
            }
            else if(command.equals("1")){
                System.out.println("give type (book, movie etc)");
                String aType = reader.nextLine();
                System.out.println("\ntype: "+aType);
                System.out.println("\ngive title");
                String aTitle = reader.nextLine();
                System.out.println("\ntitle: "+aTitle);
                System.out.println("\nedit further [Y/N]");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.now();
                String date = dtf.format(localDate);
                String fEdit = reader.nextLine();
                if(fEdit.equals("Y")){
                    System.out.println("add title length (give int)");
                    int length = Integer.parseInt(reader.nextLine());
                    System.out.println("add publication date (yyyy-MM-dd)");
                    String pdate = reader.nextLine();
                    System.out.println("add a comment");
                    String comment = reader.nextLine();
                    database.addAddition("Digilog", date, comment);
                    database.addMedia("Digilog", aTitle, length, pdate);
                    database.additionToMedia("Digilog", aTitle);
                    while (true){
                        System.out.println("add genres ('x' to stop, give an existing genre)");
                        String gName = reader.nextLine();
                        if(gName.equals("x")){
                            break;
                        }
                        database.genreToMedia("Digilog", aTitle, gName);
                    }
                }else{
                    database.addAddition("Digilog", date,
                        " ");
                    database.addMedia("Digilog", aTitle, 0, date);
                    database.additionToMedia("Digilog", aTitle);
                }
                database.typeToMedia("Digilog", aTitle, aType);
            }
            else if(command.equals("2")){
                System.out.println("give title of removable addition");
                String rName = reader.nextLine();
                database.removeAddition("Digilog", rName);
            }
            else if(command.equals("3")){
                List<String> additions = database.listAdditions("Digilog");
                int i = 0;
                System.out.println("Entries:\n");
                while (true){
                    if(i==additions.size()){
                        break;
                    }
                    System.out.println(additions.get(i));
                    i++;
                }
            }
            else if(command.equals("4")){changeSettings(reader);}
            else{System.out.println("command not recognized");}
        }
    }
    
    public void changeSettings(Scanner reader) throws SQLException{
        
        while(true){
            int mediatypecount = database.getTypeCount("Digilog");
            int genretypecount = database.getGenreCount("Digilog");
            System.out.println("----------------------------------------------------\n"
                + "\tSettings you can change are:\n"
                + "\t\tMediatypes: you currently have ("+mediatypecount+") different media types.\n"
                + "\t\tGenretypes: you currently have ("+genretypecount+") different genre types.\n");
            if(mediatypecount>0){
                List<String> types = database.listTypes("Digilog");
                System.out.print("\tmedia types: ");
                for(int i=1;i<mediatypecount;i++){
                    System.out.print(types.get(i-1)+", ");
                }
                System.out.print(types.get(mediatypecount-1)+".");
                System.out.println("");
            }
            if(genretypecount>0){
                List<String> genres = database.listGenres("Digilog");
                System.out.print("\tgenre types: ");
                for(int i=1;i<genretypecount;i++){
                    System.out.print(genres.get(i-1)+", ");
                }
                System.out.print(genres.get(genretypecount-1)+".");
                System.out.println("");
            }
            System.out.println("\n\t x -- go back\n"
                + "\t 1 -- add mediatype\n"
                + "\t 2 -- add genretype\n"
                + "\t 3 -- remove mediatype\n"
                + "\t 4 -- remove genretype\n");
            String command = reader.nextLine();
            if(command.equals("x")){break;}
            else if(command.equals("1")){
                System.out.println("\t\t give new mediatype name (Book, Movie etc.)\n");
                String tName = reader.nextLine();
                database.addType(tName,"Digilog");
            }
            else if(command.equals("2")){
                System.out.println("\t\t give new genre name (comedy, drama etc.)\n");
                String gName = reader.nextLine();
                database.addGenre(gName,"Digilog");
            }
            else if(command.equals("3")){
                System.out.println("give name of mediatype to be removed.");
                String tName = reader.nextLine();
                database.removeType(tName,"Digilog");
            }
            else if(command.equals("4")){
                System.out.println("give name of genretype to be removed.");
                String gName = reader.nextLine();
                database.removeGenre(gName,"Digilog");
            }
        }
        
    }
    
}

