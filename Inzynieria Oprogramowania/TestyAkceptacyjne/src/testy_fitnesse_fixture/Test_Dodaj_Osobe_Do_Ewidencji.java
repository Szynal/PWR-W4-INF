/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testy_fitnesse_fixture;

import Warstwa_biznesowa.TOsoba;
import fit.ColumnFixture;
import java.util.IllegalFormatCodePointException;

/**
 *
 * @author Pawel Szynal & Krzysztof Sobocinski
 */
public class Test_Dodaj_Osobe_Do_Ewidencji extends ColumnFixture {

    long PESEL;
    String imie;
    String nazwisko;
    String obywatelstwo = "PL";
    String dataUrodzenia;
    String dataZgonu;
    String stanCywilny;
    int numer;
    
    public boolean Dodaj_Osobe_Do_Ewidencji() throws IllegalFormatCodePointException {

        try {
            SetUp.aplikacja.aplikacja.Dodaj_Osobe_Do_Ewidencji(
                    new TOsoba(PESEL, imie, nazwisko, obywatelstwo, dataUrodzenia, dataZgonu, stanCywilny));

        } catch (IllegalFormatCodePointException e) {
            return false;
        }
        return SetUp.aplikacja.aplikacja._Ewidencja.size()==numer;
    }
}
