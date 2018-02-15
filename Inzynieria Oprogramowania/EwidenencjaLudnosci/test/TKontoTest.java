/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Warstwa_biznesowa.TOsoba;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Warstwa_biznesowa.TKonto;

/**
 *
 * @author Pawel Szynal
 */
public class TKontoTest {

    public TKontoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TKontoLoginTest() {
        TKonto[] daneTestowe = new TKonto[8];
        daneTestowe[0] = new TKonto("pracownik1", "haslo1");
        daneTestowe[1] = new TKonto("pracownik2", "haslo2");
        daneTestowe[2] = new TKonto("pracownik3", "haslo3");
        daneTestowe[3] = new TKonto("pracownik4", "haslo4");
        daneTestowe[4] = new TKonto("pracownik5", "haslo5");
        daneTestowe[5] = new TKonto("pracownik6", "haslo6");
        daneTestowe[6] = new TKonto("pracownik7", "haslo7");
        daneTestowe[7] = new TKonto("pracownik8", "haslo8");
        
        daneTestowe[0].Set_Login("zamianaLoginu1");
        daneTestowe[1].Set_Login("zamianaLoginu2");
        daneTestowe[2].Set_Login("zamianaLoginu3");
        daneTestowe[3].Set_Login("zamianaLoginu4");
        daneTestowe[4].Set_Login("zamianaLoginu5");
        daneTestowe[5].Set_Login("zamianaLoginu6");
        daneTestowe[6].Set_Login("zamianaLoginu7");
        daneTestowe[7].Set_Login("zamianaLoginu8");

        assertEquals(daneTestowe[0].Get_Login(), "zamianaLoginu1");
        assertEquals(daneTestowe[1].Get_Login(), "zamianaLoginu2");
        assertEquals(daneTestowe[2].Get_Login(), "zamianaLoginu3");
        assertEquals(daneTestowe[3].Get_Login(), "zamianaLoginu4");
        assertEquals(daneTestowe[4].Get_Login(), "zamianaLoginu5");
        assertEquals(daneTestowe[5].Get_Login(), "zamianaLoginu6");
        assertEquals(daneTestowe[6].Get_Login(), "zamianaLoginu7");
        assertEquals(daneTestowe[7].Get_Login(), "zamianaLoginu8");

    }
    
    @Test
    public void TKontoHasloTest(){
        
        TKonto[] daneTestowe = new TKonto[8];
        daneTestowe[0] = new TKonto("pracownik1", "haslo1");
        daneTestowe[1] = new TKonto("pracownik2", "haslo2");
        daneTestowe[2] = new TKonto("pracownik3", "haslo3");
        daneTestowe[3] = new TKonto("pracownik4", "haslo4");
        daneTestowe[4] = new TKonto("pracownik5", "haslo5");
        daneTestowe[5] = new TKonto("pracownik6", "haslo6");
        daneTestowe[6] = new TKonto("pracownik7", "haslo7");
        daneTestowe[7] = new TKonto("pracownik8", "haslo8");
        
        daneTestowe[0].Set_Haslo("zamianaHasla1");
        daneTestowe[1].Set_Haslo("zamianaHasla2");
        daneTestowe[2].Set_Haslo("zamianaHasla3");
        daneTestowe[3].Set_Haslo("zamianaHasla4");
        daneTestowe[4].Set_Haslo("zamianaHasla5");
        daneTestowe[5].Set_Haslo("zamianaHasla6");
        daneTestowe[6].Set_Haslo("zamianaHasla7");
        daneTestowe[7].Set_Haslo("zamianaHasla8");
        
        assertEquals(daneTestowe[0].Get_Haslo(), "zamianaHasla1");
        assertEquals(daneTestowe[1].Get_Haslo(), "zamianaHasla2");
        assertEquals(daneTestowe[2].Get_Haslo(), "zamianaHasla3");
        assertEquals(daneTestowe[3].Get_Haslo(), "zamianaHasla4");
        assertEquals(daneTestowe[4].Get_Haslo(), "zamianaHasla5");
        assertEquals(daneTestowe[5].Get_Haslo(), "zamianaHasla6");
        assertEquals(daneTestowe[6].Get_Haslo(), "zamianaHasla7");
        assertEquals(daneTestowe[7].Get_Haslo(), "zamianaHasla8");
        
    }
}
