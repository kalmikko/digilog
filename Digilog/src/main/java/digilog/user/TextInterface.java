package digilog.user;

import digilog.sql.SQLdatabase;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.h2.jdbc.JdbcSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author kalmikko
 */
@Component
public class TextInterface {
    @Autowired
    SQLdatabase database;
    
    @Autowired
    InterfaceLogic logic;
    
    String dbname = "Digilog";
    
    public void run(Scanner reader) throws SQLException, ClassNotFoundException {
        String command;
        System.out.println("\nchecking database...");
        if(!logic.databaseFound()){
            System.out.println("no database found, creating one...");
            database.createEmptyDatabase(dbname);
            System.out.println("done!");
            System.out.println("What profile do you want to use "
                    + "(you can modify it later)?\n"
                    + "1 - movies\n2 - books\nanything "
                    + "else for blank");
            command = reader.nextLine();
            if(command.equals("1")){logic.profile("movie");}
            if(command.equals("2")){logic.profile("book");}
            System.out.println(".\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.");
        }else{
            System.out.println("database found!");
            System.out.println(".\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.\n.");
        }
        System.out.println(".    _  __                 \n" +
        ". __| ||  |   ____   ___  \n" +
        "./ __ ||  |  /  _ \\ / __\\ \n" +
        "/ /_/ ||  |_(  (_) ) /_/ )\n" +
        "\\_____||____/\\____/\\__  / \n" +
        " v0.07 of digilog /____/");
        System.out.println("? for help, c to close program\n");
        List<String> mediatypes = new ArrayList<>();
        while (true){
            command = reader.nextLine().toLowerCase();
            if(command.equals("show") || command.equals("s")){
                command = "show media all";
            }
            if(command.contains("remove ")){
                int id = Integer.parseInt(command.substring(7));
                if(id>0 && id<=database.getAdditionCount(dbname)){
                    List<List<String>> additions = database.listAdditions(dbname);
                    database.removeAddition(dbname, additions.get(id-1).get(0));
                    System.out.println("...done");
                    command = "show media all";
                }else{
                    System.out.println("incorrect addition id");
                    command = "show media all";
                }
            }
            if(command.contains("show ")){
                System.out.println("");
                if(command.contains(" media ")){
                    if(command.contains("all")){
                        logic.listAdditions("all");
                    }else{
                        mediatypes = database.listTypes(dbname);
                        for(int f = 0; f < mediatypes.size(); f++){
                        if(command.contains(mediatypes.get(f))){
                            logic.listAdditions(mediatypes.get(f));
                        }
                    }
                    }
                }
            }
            if(command.contains("add ")){
                if(command.contains(" mediatype ")){
                    database.addType(command.substring(14), dbname);
                    System.out.println("...done\n");
                }
                if(command.contains(" genre ")){
                    database.addGenre(command.substring(10), dbname);
                    System.out.println("...done\n");
                }
                logic.addAddition(command, reader);
            }
            if(command.equals("?") || command.equals("help") || command.equals("h")){
                try {
                    mediatypes = database.listTypes(dbname);
                } catch (JdbcSQLException e){}
                System.out.println("\nshow media all - list all additions");
                for(int i = 0; i < mediatypes.size(); i++){
                    System.out.println("show media "+mediatypes.get(i)+" - list "
                            + mediatypes.get(i) + " additions");
                }
                System.out.println("\ninfo all - get all profile info\n");
                for(int j = 0; j < mediatypes.size(); j++){
                    System.out.println("add " + mediatypes.get(j) + " " 
                            + mediatypes.get(j).toUpperCase() + "_NAME "
                            + "- add " + mediatypes.get(j) + " addition");
                }
                System.out.println("add mediatype MEDIATYPE_NAME - "
                        + "add a mediatype\n"
                        + "add genre GENRE_NAME - add a genre\n");
            }
            if(command.equals("c")){break;}
            if(command.equals("x")){break;}
            if(command.equals("close")){break;}
            if(command.equals("exit")){break;}
            if(command.contains("info ")){
                if(command.contains(" all")){
                    System.out.println("");
                    int mediatypecount = database.getTypeCount(dbname);
                    int genretypecount = database.getGenreCount(dbname);
                    int additioncount = database.getAdditionCount(dbname);
                    System.out.println("this profile has ("+additioncount+") "
                            + "different additions");
                    System.out.print("this profile has ("+mediatypecount+") "
                            + "different mediatypes");
                    if(mediatypecount>0){
                    List<String> types = database.listTypes(dbname);
                    if(types.size()>0){
                        System.out.print(": ");
                    }
                    for(int i=1;i<mediatypecount;i++){
                        System.out.print(types.get(i-1)+", ");
                    }
                    System.out.print(types.get(mediatypecount-1));
                    }
                    System.out.print("\nthis profile has ("+genretypecount+") "
                            + "different genres");
                    if(genretypecount>0){
                        List<String> genres = database.listGenres(dbname);
                        if(genres.size()>0){
                            System.out.print(": ");
                        }
                        for(int i=1;i<genretypecount;i++){
                            System.out.print(genres.get(i-1)+", ");
                        }
                        System.out.print(genres.get(genretypecount-1));
                    }
                    System.out.println("\n");
                }
            }
        }
    }    
}

