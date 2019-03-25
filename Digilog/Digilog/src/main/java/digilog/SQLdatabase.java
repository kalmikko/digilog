package digilog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.List;
/**
 *
 * @author kalmikko
 */
public class SQLdatabase {
    List<String> dbNames;
    
    //create a blank SQL database
    public void createEmptyDatabase(String username) throws SQLException{
        String getConnectionString = "jdbc:sqlite:"+username+".db";
        Connection conn = DriverManager.getConnection(getConnectionString);
        Statement stat = conn.createStatement();
        stat.executeUpdate("CREATE TABLE Addition(id int, pvm date,comment varchar(500), PRIMARY KEY (id))");
        stat.executeUpdate("CREATE TABLE Media(id int, name varchar(100), published date, length bigint, PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Genre(id int, genre varchar(100), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Mediatype(id int, type varchar(100), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE AdditionMedia(additionID int, mediaID int, FOREIGN KEY (additionID) REFERENCES Addition(id),"
                + " FOREIGN KEY (mediaID) REFERENCES Media(id));");
        stat.executeUpdate("CREATE TABLE MediaGenre(mediaID int, genreID int, FOREIGN KEY (mediaID) REFERENCES Media(id),"
                + " FOREIGN KEY(genreID) REFERENCES Genre(id));");
        stat.executeUpdate("CREATE TABLE MediaType(mediaID int, typeID int, FOREIGN KEY (mediaID) REFERENCES Media(id),"
                + " FOREIGN KEY (typeID) REFERENCES Type(id));");
    }
    
    //add a movie/book (do user input logic elsewhere)
    public void addAddition(String username){
        
    }
    
    //remove data (do user input logic elsewhere)
    public void removeData(){
        
    }
    
    
    
}
