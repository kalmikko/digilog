package digilog.user;

import digilog.sql.SQLdatabase;
import java.io.File;
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
public class InterfaceLogic {
    
    @Autowired
    SQLdatabase database;
    String dbname = "Digilog";
    
    public boolean databaseFound() {
        File f = new File("./" + dbname + ".mv.db");
        if (f.isFile()) {
            return true;
        }
        return false;
    }
    
    public boolean addAddition(String command, Scanner reader) throws SQLException{
        List<String> mediatypes = new ArrayList<>();
        try {
            mediatypes = database.listTypes(dbname);
        } catch (JdbcSQLException e){
            return false;
        }
        for(int i = 0; i < mediatypes.size(); i++){
            if(command.contains(mediatypes.get(i) + " ")){
                String title = command.substring(5+mediatypes.get(i).length());
                String type = mediatypes.get(i);
                System.out.println("add a publication date? (format "
                        + "YYYY-MM-DD, blank to not add one)");
                String command2 = reader.nextLine().toLowerCase();
                String pdate;
                if(command2.length()==10){
                    if(command2.substring(4, 5).equals("-") && 
                            command2.substring(7,8).equals("-")){
                        pdate = command2;
                        System.out.println("...done");
                    }else{
                        pdate = "1000-01-01";
                        System.out.println("...no publication added");
                    }
                }else{
                    pdate = "1000-01-01";
                    System.out.println("...no publication added");
                }
                database.addMedia(dbname, title, 0, pdate);
                while(true){
                    System.out.println("adding '"+command.substring(5+
                            mediatypes.get(i).length())+
                        "' to "+ mediatypes.get(i) + "s \n\tcomment "
                                + "COMMENT_STRING "
                        + "- add a comment\n\tgenre - add a genre\n\t"
                                + "type anything else to finish");
                    String command3 = reader.nextLine().toLowerCase();
                    String comment = " ";
                    if(command3.contains("comment") && command3.length()>10){
                        comment = command.substring(8);
                        System.out.println("...done");
                    }
                    else if(command3.contains("genre")){
                        while(true){
                            System.out.println("\tselect which genres to use\n");
                            List<String> genres = new ArrayList<>();
                            try{
                                genres = database.listGenres(dbname);
                            }catch(JdbcSQLException e){
                                System.out.println("\tno genres in profile...");
                                break;
                            }
                            for(int k = 0; k < genres.size(); k++){
                                System.out.println((k+1) + ". " + 
                                        genres.get(k));
                            }
                            System.out.println("select genres, 'x' to "
                                    + "stop selecting genres");
                            String command4 = reader.nextLine()
                                    .toLowerCase();
                            if(command4.equals("x")){break;}
                            int num = Integer.parseInt(command4);
                            if(num>0 && num<=genres.size()){
                                database.genreToMedia(dbname, title, 
                                        genres.get(num-1));
                                System.out.println("...done");
                            }
                        }
                    }else{
                        DateTimeFormatter dtf = DateTimeFormatter
                                .ofPattern("yyyy-MM-dd");
                        LocalDate localDate = LocalDate.now();
                        String date = dtf.format(localDate);
                        database.typeToMedia(dbname, title, type);
                        database.addAddition(dbname, date, " ");
                        database.additionToMedia(dbname, title);
                        System.out.println("...done\n");
                        break;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean listAdditions(String type) throws SQLException {
        List<List<String>> additions = new ArrayList<>();
        try{
            additions = database.listAdditions(dbname);
        } catch (JdbcSQLException e) {
            return false;
        }
        if (additions.size() > 0) {
            System.out.println("TITLE\t\t\tMEDIATYPE\tGENRES"
                    + "\t\t\tPUBLICATION\tID");
        } else {
            System.out.println("no additions yet...\n");
            return false;
        }
        for (int l = 0; l < additions.size(); l++) {
            if (type.equals("all") || 
                    type.equals(additions.get(l).get(1))) {
                if(additions.get(l).get(0).length()>16) {
                System.out.print(additions.get(l).get(0)
                        .substring(0,13)
                        +"...");
            } else {
                System.out.print(additions.get(l).get(0));
            }
            if (additions.get(l).get(0).length()<8) {
                System.out.print("\t\t\t");
            } else if (additions.get(l).get(0).length()<16) {
                System.out.print("\t\t");
            } else {
                System.out.print("\t");
            }
            if (additions.get(l).get(1).length() > 8) {
                System.out.print(additions.get(l).get(1).substring(0,7)+"...\t");
            } else {
                System.out.print(additions.get(l).get(1)+"\t");
            }
            List<String> additiongenre = new ArrayList<>();
            try {
                additiongenre = database.listAdditionGenres(
                        dbname, additions.get(l).get(0));
            } catch (JdbcSQLException e) {

            }
            int gnum = additiongenre.size();
            if (additions.get(l).get(1).length() < 8) {
                    System.out.print("\t");
                }
            if (gnum == 0) {
                System.out.print("-\t\t\t");
            } else if (gnum==1) {
                System.out.print(additiongenre.get(0)+"\t");
                int glen = additiongenre.get(0).length();
                if(glen < 8) {
                    System.out.print("\t\t");
                } else if (glen < 16) {
                    System.out.print("\t");
                }
            } else if (gnum == 2) {
                System.out.print(additiongenre.get(0)+", ");
                System.out.print(additiongenre.get(1)+"\t");
                int glen = additiongenre.get(0).length()+
                        additiongenre.get(1).length()+2;
                if (glen < 8) {
                    System.out.print("\t\t");
                } else if (glen < 16) {
                    System.out.print("\t");
                }
            } else {
                System.out.print(additiongenre.get(0)+", ");
                System.out.print(additiongenre.get(1)+"...\t");
                int glen = additiongenre.get(0).length()+
                        additiongenre.get(1).length()+5;
                if (glen < 8) {
                    System.out.print("\t\t");
                } else if (glen < 16) {
                    System.out.print("\t");
                }
            }
            String pubdate = additions.get(l).get(3);
            if (pubdate.equals("1000-01-01")) {
                System.out.print("-\t\t");
            } else {
                System.out.print(pubdate+"\t");
            }
            System.out.print((l+1)+"\n");
            }
        }
        if (additions.size() > 0) {
            System.out.println("\n'remove ADDITION_ID' to remove a "
                    + "title\n");
        }
        return true;
    }
    
    public void profile(String profile) throws SQLException {
        if (profile.equals("movie")) {
            database.addType("movie", dbname);
            database.addGenre("action", dbname);
            database.addGenre("drama", dbname);
            database.addGenre("comedy", dbname);
            database.addGenre("romance", dbname);
        }
        if (profile.equals("book")) {
            database.addType("book", dbname);
            database.addGenre("history", dbname);
            database.addGenre("scifi", dbname);
            database.addGenre("fantasy", dbname);
            database.addGenre("philosophy", dbname);
        }
    }
}
