/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testy_fitnesse_fixture;

import Warstwa_biznesowa.TOsoba;
import fit.ColumnFixture;

/**
 *
 * @author Pawel Szynal
 */
public class Test_TOsoba extends ColumnFixture {

    long PESEL;
    String imie;
    String nazwisko;
    String obywatelstwo = "PL";
    String dataUrodzenia;
    String dataZgonu;
    String stanCywilny;

    long testPESEL;
    String testImie;
    String testNazwisko;
    String testObywatelstwo = "PL";
    String testDataUrodzenia;
    String testDataZgonu;
    String testStanCywilny;

    public boolean Test_Set_Get_TOsoba() {

        TOsoba osoba = new TOsoba(PESEL);

        osoba.Set_Imie(imie);
        osoba.Set_Nazwisko(nazwisko);
        osoba.Set_Obywatelstwo(obywatelstwo);
        osoba.Set_DataUrodzenia(dataUrodzenia);
        osoba.Set_DataZgonu(dataZgonu);
        osoba.Set_StanCywilny(stanCywilny);

        return osoba.Get_PESEL() == testPESEL
                && osoba.Get_Imie().equals(testImie)
                && osoba.Get_Nazwisko().equals(testNazwisko)
                && osoba.Get_Obywatelstwo().equals(testObywatelstwo)
                && osoba.Get_DataUrodzenia().equals(testDataUrodzenia)
                && osoba.Get_DataZgonu().equals(testDataZgonu)
                && osoba.Get_StanCywilny().equals(testStanCywilny);

    }
}
