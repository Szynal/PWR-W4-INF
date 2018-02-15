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
import Warstwa_biznesowa.TAplikacja;

/**
 *
 * @author Pawel Szynal & Krzysztof Sobocinski
 */
public class TAplikcjaTest {

    public TAplikcjaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of Usun_typ_sprzetu method, of class TAplikacja.
     */
    @Test
    public void testDodajOsobeDoEwidencji() {
        System.out.println("Dodaj Osoby");
        TAplikacja aplikacja = new TAplikacja();
        aplikacja.Dodaj_Osobe_Do_Ewidencji(new TOsoba(123456780, "A", "A", "PL", "01-01-2000", "-", "kawaler"));
        aplikacja.Dodaj_Osobe_Do_Ewidencji(new TOsoba(123456781, "B", "B", "PL", "02-02-2002", "-", "kawaler"));
        aplikacja.Dodaj_Osobe_Do_Ewidencji(new TOsoba(123456781, "C", "C", "PL", "03-03-2003", "10-03-2003", "kawaler"));
        aplikacja.Dodaj_Osobe_Do_Ewidencji(new TOsoba(123456783, "D", "D", "PL", "04-04-2004", "-", "kawaler"));
        aplikacja.Dodaj_Osobe_Do_Ewidencji(new TOsoba(123456784, "E", "E", "PL", "05-05-2005", "-", "panna"));
        aplikacja.Dodaj_Osobe_Do_Ewidencji(new TOsoba(123456785, "F", "F", "PL", "06-06-2006", "-", "wdowiec"));
        aplikacja.Dodaj_Osobe_Do_Ewidencji(new TOsoba(123456786, "A", "G", "CH", "07-07-2007", "-", "panna"));
        aplikacja.Dodaj_Osobe_Do_Ewidencji(new TOsoba(123456787, "H", "H", "PL", "08-08-2008", "-", "kawaler"));

        assertNotNull(aplikacja._Ewidencja.get(0));
        assertNotNull(aplikacja._Ewidencja.get(1));
        assertNotNull(aplikacja._Ewidencja.get(2));
        assertNotNull(aplikacja._Ewidencja.get(3));
        assertNotNull(aplikacja._Ewidencja.get(4));
        assertNotNull(aplikacja._Ewidencja.get(5));
        assertNotNull(aplikacja._Ewidencja.get(6));

        assertNotSame(aplikacja._Ewidencja.get(2), new TOsoba(123456781, "C", "C", "PL", "03-03-2003", "10-03-2003", "kawaler"));

    }

    @Test
    public void testCzy_Istnieje() {
        System.out.println("Czy_Istnieje");
        TAplikacja aplikacja = new TAplikacja();
        aplikacja._Ewidencja.add(new TOsoba(123456780, "A", "A", "PL", "01-01-2000", "-", "kawaler"));
        aplikacja._Ewidencja.add(new TOsoba(123456781, "B", "B", "PL", "02-02-2002", "-", "kawaler"));
        aplikacja._Ewidencja.add(new TOsoba(123456787, "C", "C", "PL", "03-03-2003", "10-03-2003", "kawaler"));
        aplikacja._Ewidencja.add(new TOsoba(123456782, "D", "D", "PL", "04-04-2004", "-", "kawaler"));
        aplikacja._Ewidencja.add(new TOsoba(123456783, "E", "E", "PL", "05-05-2005", "-", "panna"));
        aplikacja._Ewidencja.add(new TOsoba(123456784, "F", "F", "PL", "06-06-2006", "-", "wdowiec"));
        aplikacja._Ewidencja.add(new TOsoba(123456785, "A", "G", "CH", "07-07-2007", "-", "panna"));
        aplikacja._Ewidencja.add(new TOsoba(123456786, "H", "H", "PL", "08-08-2008", "-", "kawaler"));

        assertTrue(aplikacja.Czy_Istnieje(123456780));
        assertTrue(aplikacja.Czy_Istnieje(123456781));
        assertFalse(aplikacja.Czy_Istnieje(123456788));
        assertTrue(aplikacja.Czy_Istnieje(123456782));
        assertTrue(aplikacja.Czy_Istnieje(123456783));
        assertTrue(aplikacja.Czy_Istnieje(123456784));
        assertTrue(aplikacja.Czy_Istnieje(123456785));
        assertTrue(aplikacja.Czy_Istnieje(123456786));

    }

    @Test
    public void testWyszukaj_Osobe() {
        System.out.println("Czy_Istnieje");
        TAplikacja aplikacja = new TAplikacja();
        aplikacja._Ewidencja.add(new TOsoba(123456780, "A", "A", "PL", "01-01-2000", "-", "kawaler"));
        aplikacja._Ewidencja.add(new TOsoba(123456781, "B", "B", "PL", "02-02-2002", "-", "kawaler"));
        aplikacja._Ewidencja.add(new TOsoba(123456787, "C", "C", "PL", "03-03-2003", "10-03-2003", "kawaler"));
        aplikacja._Ewidencja.add(new TOsoba(123456782, "D", "D", "PL", "04-04-2004", "-", "kawaler"));
        aplikacja._Ewidencja.add(new TOsoba(123456783, "E", "E", "PL", "05-05-2005", "-", "panna"));
        aplikacja._Ewidencja.add(new TOsoba(123456784, "F", "F", "PL", "06-06-2006", "-", "wdowiec"));
        aplikacja._Ewidencja.add(new TOsoba(123456785, "A", "G", "CH", "07-07-2007", "-", "panna"));
        aplikacja._Ewidencja.add(new TOsoba(123456786, "H", "H", "PL", "08-08-2008", "-", "kawaler"));

        assertEquals(aplikacja._Ewidencja.get(0), aplikacja.Wyszukaj_Osobe(123456780));
        assertEquals(aplikacja._Ewidencja.get(1), aplikacja.Wyszukaj_Osobe(123456781));
        assertNotEquals(aplikacja._Ewidencja.get(2), aplikacja.Wyszukaj_Osobe(123456788));
        assertEquals(aplikacja._Ewidencja.get(3), aplikacja.Wyszukaj_Osobe(123456782));
        assertEquals(aplikacja._Ewidencja.get(4), aplikacja.Wyszukaj_Osobe(123456783));
        assertEquals(aplikacja._Ewidencja.get(5), aplikacja.Wyszukaj_Osobe(123456784));
        assertEquals(aplikacja._Ewidencja.get(6), aplikacja.Wyszukaj_Osobe(123456785));
        assertEquals(aplikacja._Ewidencja.get(7), aplikacja.Wyszukaj_Osobe(123456786));

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
