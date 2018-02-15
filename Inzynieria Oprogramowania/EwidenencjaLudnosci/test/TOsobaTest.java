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
import Warstwa_biznesowa.TOsoba;

/**
 *
 * @author Pawel Szynal
 */
public class TOsobaTest {

    public TOsobaTest() {
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
    public void testOsoby() {

        TOsoba osobaTestowa = new TOsoba(19763903);

        assertEquals(19763903, osobaTestowa.Get_PESEL());
        assertNotEquals(00000000, osobaTestowa.Get_PESEL());

        osobaTestowa.Set_DataUrodzenia("1888");
        assertEquals("1888", osobaTestowa.Get_DataUrodzenia());
        assertNotEquals("-", osobaTestowa.Get_DataUrodzenia());

        osobaTestowa.Set_DataZgonu("1973");
        assertEquals("1973", osobaTestowa.Get_DataZgonu());
        assertNotEquals("-", osobaTestowa.Get_DataZgonu());

        osobaTestowa.Set_Imie("Jan");
        assertEquals("Jan", osobaTestowa.Get_Imie());
        assertNotEquals("-", osobaTestowa.Get_Imie());

        osobaTestowa.Set_Nazwisko("Kowalski");
        assertEquals("Kowalski", osobaTestowa.Get_Nazwisko());
        assertNotEquals("-", osobaTestowa.Get_Nazwisko());

        osobaTestowa.Set_Obywatelstwo("PL");
        assertEquals("PL", osobaTestowa.Get_Obywatelstwo());
        assertNotEquals("-", osobaTestowa.Get_Obywatelstwo());

        osobaTestowa.Set_StanCywilny("Kawaler");
        assertEquals("Kawaler", osobaTestowa.Get_StanCywilny());
        assertNotEquals("-", osobaTestowa.Get_StanCywilny());
        
    }
}
