/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import digilog.DigilogSovellus;
import digilog.sql.SQLdatabase;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author kalmikko
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SQLdatabase.class)
public class sqlTest {
    @Autowired
    SQLdatabase sqldb;
    
    public sqlTest() {
    }
    
//    @BeforeAll
//    public void setUpClass() throws SQLException, ClassNotFoundException {
//        SpringApplication.run(DigilogSovellus.class);
//        sqldb.createEmptyDatabase("testdb");
//    }
    
    @Test
    public void addingAndRemovingGenreType() throws SQLException, ClassNotFoundException{
//        sqldb.createEmptyDatabase("testdb");
        int genrecount = sqldb.getGenreCount("Digilog");
        sqldb.addGenre("testgenre1","Digilog");
        //System.out.println(genrecount+"  "+sqldb.getGenreCount("testdb"));
        assertTrue(genrecount==sqldb.getGenreCount("Digilog")-1);
        sqldb.removeGenre("testgenre1", "Digilog");
        assertTrue(genrecount==sqldb.getGenreCount("Digilog"));
    }
    @Test
    public void addingAndRemovingAddition() throws SQLException, ClassNotFoundException{
//        sqldb.createEmptyDatabase("testdb");
        int additioncount = sqldb.getAdditionCount("Digilog");
        sqldb.addAddition("testuser1","Digilog", "2011-01-01", "testcomment1");
        //System.out.println(genrecount+"  "+sqldb.getGenreCount("testdb"));
        assertTrue(additioncount==sqldb.getAdditionCount("Digilog")-1);
    }
    @Test
    public void addingAndRemovingMedia() throws SQLException, ClassNotFoundException{
//        sqldb.createEmptyDatabase("testdb");
        int mediacount = sqldb.getMediaCount("Digilog");
        sqldb.addMedia("Digilog","testmedia1", 0, "1990-01-01");
        //System.out.println(genrecount+"  "+sqldb.getGenreCount("testdb"));
        assertTrue(mediacount==sqldb.getMediaCount("Digilog")-1);
    }
    @Test
    public void addingAndRemovingMediaType() throws SQLException, ClassNotFoundException{
        //sqldb.createEmptyDatabase("testdb");
        int typecount = sqldb.getTypeCount("Digilog");
        sqldb.addType("testtype1","Digilog");
        assertTrue(typecount==sqldb.getTypeCount("Digilog")-1);
        sqldb.removeType("testtype1", "Digilog");
        assertTrue(typecount==sqldb.getTypeCount("Digilog"));
    }
    @Test
    public void genreAndTypeListCountsAreCorrect() throws SQLException{
        sqldb.addGenre("testgenre1", "Digilog");
        sqldb.addType("testtype1", "Digilog");
        List<String> types = sqldb.listTypes("Digilog");
        List<String> genres = sqldb.listGenres("Digilog");
        int typecount = sqldb.getTypeCount("Digilog");
        int genrecount = sqldb.getGenreCount("Digilog");
        assertTrue(types.size()==typecount);
        assertTrue(genres.size()==genrecount);
        sqldb.removeType("testtype1", "Digilog");
        sqldb.removeGenre("testgenre1", "Digilog");
    }
    
    
}
