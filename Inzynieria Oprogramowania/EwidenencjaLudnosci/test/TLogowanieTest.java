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
import Warstwa_biznesowa.TLogowanie;
import Warstwa_biznesowa.TKonto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pawel Szynal
 */
public class TLogowanieTest {

    public TLogowanieTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void Weryfikacja_Danych_LogowaniaTest() {
        
        TLogowanie test = new TLogowanie();

        test.Dodaj_Konto("pracownik1", "haslo1");
        test.Dodaj_Konto("pracownik2", "haslo2");
        test.Dodaj_Konto("pracownik3", "haslo3");
        test.Dodaj_Konto("pracownik4", "haslo4");
        test.Dodaj_Konto("pracownik5", "haslo5");
        test.Dodaj_Konto("pracownik6", "haslo6");
        test.Dodaj_Konto("pracownik7", "haslo7");
        test.Dodaj_Konto("pracownik8", "haslo8");
        
        assertTrue(test.Weryfikacja_Danych_Logowania("pracownik1", "haslo1"));
        assertTrue(test.Weryfikacja_Danych_Logowania("pracownik3", "haslo3"));
        assertTrue(test.Weryfikacja_Danych_Logowania("pracownik4", "haslo4"));
        assertTrue(test.Weryfikacja_Danych_Logowania("pracownik7", "haslo7"));
      
        assertFalse(test.Weryfikacja_Danych_Logowania("praconik1", "haslo1"));
        assertFalse(test.Weryfikacja_Danych_Logowania("pracownik1", "haso1"));
        assertFalse(test.Weryfikacja_Danych_Logowania("pracownik", "haslo1"));
        assertFalse(test.Weryfikacja_Danych_Logowania("pracownik8", "haslo1"));
        
    }

    @Test
    public void Dodaj_KontoTest() {

        List<TKonto> listaKontTestowych = new ArrayList<TKonto>();

        TLogowanie test = new TLogowanie();

        listaKontTestowych.add(new TKonto("pracownik1", "haslo1"));
        listaKontTestowych.add(new TKonto("pracownik2", "haslo2"));
        listaKontTestowych.add(new TKonto("pracownik3", "haslo3"));
        listaKontTestowych.add(new TKonto("pracownik4", "haslo4"));
        listaKontTestowych.add(new TKonto("pracownik5", "haslo5"));
        listaKontTestowych.add(new TKonto("pracownik6", "haslo6"));
        listaKontTestowych.add(new TKonto("pracownik7", "haslo7"));
        listaKontTestowych.add(new TKonto("pracownik8", "haslo8"));

        test.Dodaj_Konto("pracownik1", "haslo1");
        test.Dodaj_Konto("pracownik2", "haslo2");
        test.Dodaj_Konto("pracownik3", "haslo3");
        test.Dodaj_Konto("pracownik4", "haslo4");
        test.Dodaj_Konto("pracownik5", "haslo5");
        test.Dodaj_Konto("pracownik6", "haslo6");
        test.Dodaj_Konto("pracownik7", "haslo7");
        test.Dodaj_Konto("pracownik8", "haslo8");

        assertEquals(test._ListaOsob.get(0).Get_Login(), listaKontTestowych.get(0).Get_Login());
        assertEquals(test._ListaOsob.get(1).Get_Login(), listaKontTestowych.get(1).Get_Login());
        assertEquals(test._ListaOsob.get(2).Get_Login(), listaKontTestowych.get(2).Get_Login());
        assertEquals(test._ListaOsob.get(3).Get_Login(), listaKontTestowych.get(3).Get_Login());
        assertEquals(test._ListaOsob.get(4).Get_Login(), listaKontTestowych.get(4).Get_Login());
        assertEquals(test._ListaOsob.get(5).Get_Login(), listaKontTestowych.get(5).Get_Login());
        assertEquals(test._ListaOsob.get(6).Get_Login(), listaKontTestowych.get(6).Get_Login());
        assertEquals(test._ListaOsob.get(7).Get_Login(), listaKontTestowych.get(7).Get_Login());

        assertEquals(test._ListaOsob.get(0).Get_Haslo(), listaKontTestowych.get(0).Get_Haslo());
        assertEquals(test._ListaOsob.get(1).Get_Haslo(), listaKontTestowych.get(1).Get_Haslo());
        assertEquals(test._ListaOsob.get(2).Get_Haslo(), listaKontTestowych.get(2).Get_Haslo());
        assertEquals(test._ListaOsob.get(3).Get_Haslo(), listaKontTestowych.get(3).Get_Haslo());
        assertEquals(test._ListaOsob.get(4).Get_Haslo(), listaKontTestowych.get(4).Get_Haslo());
        assertEquals(test._ListaOsob.get(5).Get_Haslo(), listaKontTestowych.get(5).Get_Haslo());
        assertEquals(test._ListaOsob.get(6).Get_Haslo(), listaKontTestowych.get(6).Get_Haslo());
        assertEquals(test._ListaOsob.get(7).Get_Haslo(), listaKontTestowych.get(7).Get_Haslo());

    }
}
