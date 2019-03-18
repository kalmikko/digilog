package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void luodunKortinSaldoOikea() {
        assertEquals("saldo: 0.10", kortti.toString());      
    }
    
    @Test
    public void kortinSaldoOikeinKunLataaRahaa() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 5.10", kortti.toString());  
    }
    
    @Test
    public void kortinSaldoOikeaKunOttaaRahaa() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());  
    }
    
    @Test
    public void kortinSaldoEiMuutuJosseiRahaa() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());  
    }
}
