package digilog.sql;

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
    public void createEmptyDatabase(String name) 
            throws SQLException, ClassNotFoundException {
        Statement stat = connectToDB(name);
        stat.executeUpdate("CREATE TABLE Addition(id int, pvm date,comment "
                + "varchar(500),user varchar(50), PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Media(id int, name varchar(100), "
                + "published date, length bigint, PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Genre(id int, name varchar(100), "
                + "PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE Type(id int, name varchar(100), "
                + "PRIMARY KEY (id));");
        stat.executeUpdate("CREATE TABLE AdditionMediaLT(additionID int, "
                + "mediaID int, FOREIGN KEY (additionID) REFERENCES Addition(id),"
                + " FOREIGN KEY (mediaID) REFERENCES Media(id));");
        stat.executeUpdate("CREATE TABLE MediaGenreLT(mediaID int, genreID int, "
                + "FOREIGN KEY (mediaID) REFERENCES Media(id),"
                + " FOREIGN KEY(genreID) REFERENCES Genre(id));");
        stat.executeUpdate("CREATE TABLE MediaTypeLT(mediaID int, typeID int, "
                + "FOREIGN KEY (mediaID) REFERENCES Media(id),"
                + " FOREIGN KEY (typeID) REFERENCES Type(id));");
    }
    
    public Statement connectToDB(String name) throws SQLException {
//        String dir = "/home/kalmikko/kurssit/_ot2019/ot-harjoitustyo/Digilog/"
//                + "Digilog/src/main/java/digilog";
        Connection conn = DriverManager.getConnection("jdbc:h2:./" + name, ""
                + "sa", "");
        Statement stat = conn.createStatement();
        return stat;
    }
    
    public List<String> listAdditions(String user, String dbName) 
            throws SQLException {
        List<String> additionOutput = new ArrayList<>();
        Statement stat = connectToDB(dbName);
        additionOutput.add("id\ttitle\tadded\tpublished\tcomment(y/n)\n"
                + "-----------------------------------------------------");
        ResultSet outputSQL = stat.executeQuery(""
                + "SELECT Addition.id, Media.name, pvm, Media.published, comment FROM Addition"
                + " JOIN AdditionMediaLT ON AdditionMediaLT.additionID = Addition.id"
                + " JOIN Media ON Media.id = AdditionMediaLT.mediaID;");
        outputSQL.first();
        int i = 0; 
        int j = getAdditionCount(dbName);
        while (true) {
            if (j == i) {
                break;
            }
            i++;
            additionOutput.add(outputSQL.getInt("Addition.id") + "\t" + 
                    outputSQL.getString("Media.name") + "\t" + 
                    outputSQL.getDate("pvm") + "\t" + outputSQL.getDate("Media.published") + 
                    "\t" + outputSQL.getString("comment"));
            outputSQL.next();
        }
        return additionOutput;
    }
    
    public void additionToMedia(String username, String dbName, 
            String title) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output1 = stat.executeQuery("SELECT id AS id FROM Media"
                + " WHERE name = '" + title + "';");
        output1.first();
        int mediaID = output1.getInt("id");
        int additionID = getAdditionID(dbName);
        stat.executeUpdate("INSERT INTO AdditionMediaLT (additionID,mediaID)"
                + " VALUES (" + additionID + ", " + mediaID + " );");
    }
    
    public void typeToMedia(String username, String dbName, String name, 
            String type) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output1 = stat.executeQuery("SELECT id AS id FROM Type"
                + " WHERE name ='" + type + "';");
        output1.first();
        int typeID = output1.getInt("id");
        ResultSet output2 = stat.executeQuery("SELECT id AS id FROM Media"
                + " WHERE name ='" + name + "';");
        output2.first();
        int mediaID = output2.getInt("id");
        stat.executeUpdate("INSERT INTO MediaTypeLT (mediaID,typeID)"
                + " VALUES (" + mediaID + ", " + typeID + " );");
    }
    
    public void genreToMedia(String username, String dbName, String name, 
            String genre) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output1 = stat.executeQuery("SELECT id AS id FROM Genre"
                + " WHERE name ='" + genre + "';");
        output1.first();
        int genreID = output1.getInt("id");
        ResultSet output2 = stat.executeQuery("SELECT id AS id FROM Media"
                + " WHERE name ='" + name + "';");
        output2.first();
        int mediaID = output2.getInt("id");
        stat.executeUpdate("INSERT INTO MediaGenreLT (mediaID,genreID)"
                + " VALUES (" + mediaID + ", " + genreID + " );");
    }
    
    public void addMedia(String dbName, String title, int length, 
            String pdate) throws SQLException {
        Statement stat = connectToDB(dbName);
        int id = getMediaID(dbName) + 1;
        stat.executeUpdate("INSERT INTO Media (id, name, published, length)"
                + " VALUES (" + id + ", '" + title + "', '" + pdate + "', " 
                + length + ");");
    }
    
    public void addAddition(String username, String dbName, String date,
        String comment) throws SQLException {
        Statement stat = connectToDB(dbName);
        int id = getAdditionID(dbName) + 1;
        stat.executeUpdate("INSERT INTO Addition (id, pvm, comment, user) "
                + "VALUES (" + id + ", '" + date + "', "
                        + "'" + comment + "', '" + username + "');");
    }
    
    public void removeAddition(String username, String dbName, 
            String name) throws SQLException {
        Statement stat = connectToDB(dbName);
        int id = stat.executeQuery("SELECT id AS id FROM Addition"
                + " JOIN AdditionMediaLT ON AdditionMediaLT.additionID = Addition.id"
                + " JOIN Media on Media.id = AdditionMediaLT.mediaID"
                + " WHERE Media.name = '" + name + "';").getInt("id");
        stat.executeUpdate("DELETE FROM Addition WHERE (id =" + id + ")"
                + " AND (user = '" + username + "');");
    }
    
    public int getMediaCount(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Media;");
        output.last();
        return output.getRow();
    }
    
    public int getMediaID(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Media;");
        output.last();
        if (!output.last()) {
            return 1;
        }
        return output.getInt("id");
    }
    
    public int getAdditionCount(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Addition;");
        output.last();
        return output.getRow();
    }
    
    public int getAdditionID(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Addition;");
        output.last();
        if (!output.last()) {
            return 1;
        }
        return output.getInt("id");
    }
    
    public List<String> listGenres(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT name AS table FROM Genre;");
        output.first();
        int i = 0;
        List<String> genres = new ArrayList<>();
        while (true) {
            i++;
            genres.add(output.getString("table"));
            if (i == getGenreCount(dbName)) {
                break;
            }
            output.next();
        }
        return genres;
    }
    
    public List<String> listTypes(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT name AS table FROM Type;");
        output.first();
        int i = 0;
        List<String> types = new ArrayList<>();
        while (true) {
            i++;
            types.add(output.getString("table"));
            if (i == getTypeCount(dbName)) {
                break;
            }
            output.next();
        }
        return types;
    }
    
    public List<String> listMedia(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT name AS table FROM Media;");
        output.first();
        int i = 0;
        List<String> media = new ArrayList<>();
        while (true) {
            i++;
            media.add(output.getString("table"));
            if (i == getMediaCount(dbName)) {
                break;
            }
            output.next();
        }
        return media;
    }
    
    public void addGenre(String gName, String dbName) throws SQLException {
        Statement stat = connectToDB("Digilog");
        int id = getGenreID(dbName) + 1;
        stat.executeUpdate("INSERT INTO Genre (id, name) VALUES (" + id + ", '"
            + gName + "');");
    }
    
    public void removeGenre(String gName, String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        stat.executeUpdate("DELETE FROM Genre WHERE name='" + gName + "';");
    }
    
    public void addType(String tName, String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        int id = getTypeID(dbName) + 1;
        stat.executeUpdate("INSERT INTO Type (id, name) VALUES (" + id + ", '"
            + tName + "');");
    }
    
    public void removeType(String tName, String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        stat.executeUpdate("DELETE FROM Type WHERE name='" + tName + "';");
    }
    
    public int getTypeCount(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Type;");
        output.last();
        return output.getRow();
    }
    
    public int getTypeID(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Type;");
        output.last();
        if (!output.last()) {
            return 1;
        }
        return output.getInt("id");
    }
    
    public int getGenreID(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Genre;");
        output.last();
        if (!output.last()) {
            return 1;
        }
        return output.getInt("id");
    }
    
    public int getGenreCount(String dbName) throws SQLException {
        Statement stat = connectToDB(dbName);
        ResultSet output = stat.executeQuery("SELECT * FROM Genre;");
        output.last();
        return output.getRow();
    }
}
