
import digilog.sql.SQLdatabase;
import digilog.user.InterfaceLogic;
import digilog.DigilogSovellus;
import digilog.sql.SQLdatabase;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
/**
 *
 * @author kalmikko
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {InterfaceLogic.class, SQLdatabase.class})
public class interfacelogicTest {
    @Autowired
    InterfaceLogic logic;
    
    @Autowired
    SQLdatabase sqldb;
            
    String dbname = "Digilog";
    
    public interfacelogicTest(){
        
    }
    
    @Test
    public void logicDetectsDatabaseCorrectly(){
        Boolean dbFound = logic.databaseFound();
        File f = new File("./" + dbname + ".mv.db");
        Boolean fileFound;
        if (f.isFile()) {
            fileFound = true;
        }else{
            fileFound = false;
        }
        assertTrue(dbFound=fileFound);
    }
    
    @Test
    public void logicPrintsAdditionsCorrectly() throws SQLException{
        Boolean printAdditions = logic.listAdditions("all");
        if(sqldb.getAdditionCount(dbname)==0){
            assertTrue(printAdditions = false);
        } else {
            assertTrue(printAdditions = true);
        }
    }
}
