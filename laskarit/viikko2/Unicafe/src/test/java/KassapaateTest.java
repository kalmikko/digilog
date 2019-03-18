/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kalmikko
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @Test
    public void kateisOstoLisaaKassaanRahaa(){
        int kassalopussa = kassa.kassassaRahaa()+240;
        int palautus = kassa.syoEdullisesti(500);
        assertTrue(kassa.kassassaRahaa()==kassalopussa);
        assertTrue(palautus == 500-240);
    }
    @Test
    public void edullisenOstoLisaaMyytyjaKateisella(){
        kassa.syoEdullisesti(500);
        assertTrue(kassa.edullisiaLounaitaMyyty()==1);
    }
    @Test
    public void maukkaanOstoLisaaMyytyjaKateisella(){
        kassa.syoMaukkaasti(500);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
    }
    @Test
    public void palautaRahatJosseiTarpeeksiRahaaKateisella(){
        int alkukassa = kassa.kassassaRahaa();
        int palautus = kassa.syoEdullisesti(10);
        assertTrue(palautus==10);
        assertTrue(alkukassa==kassa.kassassaRahaa());
        assertTrue(kassa.edullisiaLounaitaMyyty()==0);
    }
    @Test
    public void korttiOstoLisaaKassaanRahaa(){
        int kassalopussa = kassa.kassassaRahaa();
        boolean palautus = kassa.syoEdullisesti(kortti);
        assertTrue(kassa.kassassaRahaa()==kassalopussa);
        assertTrue(palautus==true);
    }
    @Test
    public void edullisenOstoLisaaMyytyjaKortilla(){
    kassa.syoEdullisesti(kortti);
    assertTrue(kassa.edullisiaLounaitaMyyty()==1);
    }
    @Test
    public void maukkaanOstoLisaaMyytyjaKortilla(){
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty()==1);
    }
    @Test
    public void palautaRahatJosseiTarpeeksiRahaaKortilla(){
        int alkukassa = kassa.kassassaRahaa();
        kortti.otaRahaa(490);
        boolean palautus = kassa.syoEdullisesti(kortti);
        assertTrue(palautus!=true);
        assertTrue(alkukassa==kassa.kassassaRahaa());
        assertTrue(kassa.edullisiaLounaitaMyyty()==0);
    }
    @Test
    public void korttiLatausLisaaKassaanJaKorttiinRahaa(){
        int loppusaldo = kortti.saldo()+100;
        int loppukassa = kassa.kassassaRahaa()+100;
        kassa.lataaRahaaKortille(kortti, 100);
        assertTrue(kortti.saldo()==loppusaldo);
        assertTrue(kassa.kassassaRahaa()==loppukassa);
    }

}
