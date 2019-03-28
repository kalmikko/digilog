package digilog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
/**
 *
 * @author kalmikko
 */
@Component
public class SQLdatabase {
    
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    
    //create a blank SQL database
    public void createEmptyDatabase() throws SQLException, ClassNotFoundException{
        Statement stat = connectToDB();
        stat.executeUpdate("CREATE TABLE Addition(id int, pvm date,comment varchar(500),user varchar(50), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Media(id int, name varchar(100), published date, length bigint, PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Genre(id int, genre varchar(100), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Mediatype(id int, type varchar(100), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE AdditionMedia(additionID int, mediaID int, FOREIGN KEY (additionID) REFERENCES Addition(id),"
                + " FOREIGN KEY (mediaID) REFERENCES Media(id));");
        stat.executeUpdate("CREATE TABLE MediaGenre(mediaID int, genreID int, FOREIGN KEY (mediaID) REFERENCES Media(id),"
                + " FOREIGN KEY(genreID) REFERENCES Genre(id));");
        stat.executeUpdate("CREATE TABLE MediaTypeliittotaulu(mediaID int, typeID int, FOREIGN KEY (mediaID) REFERENCES Media(id),"
                + " FOREIGN KEY (typeID) REFERENCES Mediatype(id));");
    }
    
    public Statement connectToDB() throws SQLException{
//        String dir = "/home/kalmikko/kurssit/_ot2019/ot-harjoitustyo/Digilog/"
//                + "Digilog/src/main/java/digilog";
        Connection conn = DriverManager.getConnection("jdbc:h2:./Digilog","sa","");
        Statement stat = conn.createStatement();
        return stat;
    }
    
    //add a movie/book (do user input logic elsewhere)
    public void addAddition(String username){
        
    }
    
    //remove data (do user input logic elsewhere)
    public void removeAddition(){
        
    }
    
    public void addGenre(String gName) throws SQLException{
        Statement stat = connectToDB();
        int id = getGenreTypeCount() + 1;
//        stat.executeUpdate("INSERT INTO Genre (id, genre) VALUES ("+id+", '"
//        +gName+"');");
    }
    
    public void removeGenre(){
        
    }
    
    public void addMediatype(String tName) throws SQLException{
        Statement stat = connectToDB();
        int id = getMediaTypeCount() + 1;
//        stat.executeUpdate("INSERT INTO MediaType(id, type) VALUES ("+id+", '"
//        +tName+"');");
    }
    
    public void removeMediaType(){
        
    }
    
    public int getMediaTypeCount() throws SQLException{
        Statement stat = connectToDB();
        ResultSet output = stat.executeQuery("SELECT COUNT(*) FROM Mediatype;");
        //System.out.println("testi "+output);
        return 0;
    }
    
    public int getGenreTypeCount() throws SQLException{
        Statement stat = connectToDB();
        ResultSet output = stat.executeQuery("SELECT COUNT(*) FROM Genre;");
        return 0;
    }
}
