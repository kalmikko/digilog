/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import digilog.Interface;
import java.sql.SQLException;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kalmikko
 */
public class InterfaceTest {
    Interface tekstikayttoliittyma;
    
    public InterfaceTest() {
    }
    
//    @BeforeAll
//    public static void setUpClass() {
//        Interface tekstikayttoliittyma;
//    }
    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
    @Test
    public void tekstikayttoliittymaAvautuu() throws SQLException, ClassNotFoundException{
        Scanner reader = new Scanner(System.in);
        tekstikayttoliittyma.run(reader);
        assertEquals("1","1");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
