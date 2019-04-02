package digilog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.ArrayList;
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
    public void createEmptyDatabase(String name) throws SQLException, ClassNotFoundException{
        Statement stat = connectToDB(name);
        stat.executeUpdate("CREATE TABLE Addition(id int, pvm date,comment varchar(500),user varchar(50), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Media(id int, name varchar(100), published date, length bigint, PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Genre(id int, name varchar(100), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Type(id int, name varchar(100), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE AdditionMediaLT(additionID int, mediaID int, FOREIGN KEY (additionID) REFERENCES Addition(id),"
                + " FOREIGN KEY (mediaID) REFERENCES Media(id));");
        stat.executeUpdate("CREATE TABLE MediaGenreLT(mediaID int, genreID int, FOREIGN KEY (mediaID) REFERENCES Media(id),"
                + " FOREIGN KEY(genreID) REFERENCES Genre(id));");
        stat.executeUpdate("CREATE TABLE MediaTypeLT(mediaID int, typeID int, FOREIGN KEY (mediaID) REFERENCES Media(id),"
                + " FOREIGN KEY (typeID) REFERENCES Type(id));");
    }
    
    public Statement connectToDB(String name) throws SQLException{
//        String dir = "/home/kalmikko/kurssit/_ot2019/ot-harjoitustyo/Digilog/"
//                + "Digilog/src/main/java/digilog";
        Connection conn = DriverManager.getConnection("jdbc:h2:./"+name,"sa","");
        Statement stat = conn.createStatement();
        return stat;
    }
    
    //add a movie/book (do user input logic elsewhere)
    public void addAddition(String username){
        
    }
    
    //remove data (do user input logic elsewhere)
    public void removeAddition(){
        
    }
    
    public List<String> listGenres(String dbName) throws SQLException{
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT name AS table FROM Genre;");
        output.first();
        int i = 0;
        List<String> genres = new ArrayList<>();
        while(true){
            i++;
            //System.out.println(output.getString("table"));
            genres.add(output.getString("table"));
            if(i==getGenreCount(dbName)){
                break;
            }
            output.next();
        }
        return genres;
    }
    
    public List<String> listTypes(String dbName) throws SQLException{
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT name AS table FROM Type;");
        output.first();
        int i = 0;
        List<String> types = new ArrayList<>();
        while(true){
            i++;
            //System.out.println(output.getString("table"));
            types.add(output.getString("table"));
            if(i==getTypeCount(dbName)){
                break;
            }
            output.next();
        }
        return types;
    }
    
    public List<String> listAdditions(){
        
        return null;
    }
    
    public void addGenre(String gName, String dbName) throws SQLException{
        Statement stat = connectToDB("Digilog");
        int id = getGenreCount(dbName) + 1;
        stat.executeUpdate("INSERT INTO Genre (id, name) VALUES ("+id+", '"
        +gName+"');");
    }
    
    public void removeGenre(String gName, String dbName) throws SQLException{
        Statement stat = connectToDB(dbName);
        stat.executeUpdate("DELETE FROM Genre WHERE name='"+gName+"';");
    }
    
    public void addType(String tName, String dbName) throws SQLException{
        Statement stat = connectToDB(dbName);
        int id = getTypeCount(dbName) + 1;
        stat.executeUpdate("INSERT INTO Type (id, name) VALUES ("+id+", '"
        +tName+"');");
    }
    
    public void removeType(String tName,String dbName) throws SQLException{
        Statement stat = connectToDB(dbName);
        stat.executeUpdate("DELETE FROM Type WHERE name='"+tName+"';");
    }
    
    public int getTypeCount(String dbName) throws SQLException{
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Type;");
        output.last();
//        System.out.println("type rows: "+output.getRow());
        return output.getRow();
    }
    
    public int getGenreCount(String dbName) throws SQLException{
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Genre;");
        output.last();
//        System.out.println("genre rows: "+output.getRow());
        return output.getRow();
    }
}
