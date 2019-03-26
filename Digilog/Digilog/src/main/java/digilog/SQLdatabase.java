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
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
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
                + " FOREIGN KEY (typeID) REFERENCES Type(id));");
    }
    
    public Statement connectToDB() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String getConnectionString = "jdbc:sqlite:Digilog.db";
        Connection conn = DriverManager.getConnection(getConnectionString);
        Statement stat = conn.createStatement();
        return stat;
    }
    
    //add a movie/book (do user input logic elsewhere)
    public void addAddition(String username){
        
    }
    
    //remove data (do user input logic elsewhere)
    public void removeAddition(){
        
    }
    
    public void addGenre(String gName){
        int count = 0;
        jdbcTemplate.update("INSERT INTO Genre (id,genre) "
                + "VALUES (?,?)", count,gName);
    }
    
    public void removeGenre(String gName){
        jdbcTemplate.update("DELETE FROM Genre WHERE genre=(?)",gName);
    }
    
    public void addMediatype(String tName){
        int count = 0;
        jdbcTemplate.update("INSERT INTO MeadiaType (id,type) "
                + "VALUES (?,?)", count,tName);
    }
    
    public void removeMediaType(String tName){
        jdbcTemplate.update("DELETE FROM MediaType WHERE type=(?)",tName);
    }
    
    public int getMediaTypeCount(){
        List<String> mRows = jdbcTemplate.query(
        "SELECT * FROM MediaType",
        (rs, rowNum) -> rs.getString("type"));
        return mRows.size();
    }
    
    public int getGenreTypeCount(){
        List<String> gRows = jdbcTemplate.query(
        "SELECT * FROM Genre",
        (rs, rowNum) -> rs.getString("genre"));
        return gRows.size();
    }
}
