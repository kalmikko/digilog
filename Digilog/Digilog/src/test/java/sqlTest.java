/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import digilog.DigilogSovellus;
import digilog.SQLdatabase;
import java.sql.SQLException;
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
    public void addingGenreType() throws SQLException, ClassNotFoundException{
        sqldb.createEmptyDatabase("testdb");
        int genrecount = sqldb.getGenreCount("testdb");
        sqldb.addGenre("testgenre1","testdb");
        System.out.println(genrecount+"  "+sqldb.getGenreCount("testdb"));
        assertTrue(genrecount==sqldb.getGenreCount("testdb")-1);
    }
//    @Test
//    public void addingMediaType() throws SQLException, ClassNotFoundException{
//        sqldb.createEmptyDatabase("testdb");
//        int typecount = sqldb.getTypeCount("testdb");
//        sqldb.addType("testtype1","testdb");
//        assertTrue(typecount==sqldb.getTypeCount("testdb")-1);
//    }
}
