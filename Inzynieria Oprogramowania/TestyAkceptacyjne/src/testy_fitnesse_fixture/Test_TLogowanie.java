/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testy_fitnesse_fixture;

import Warstwa_biznesowa.TLogowanie;
import fit.ColumnFixture;

/**
 *
 * @author Pawel Szynal
 */
public class Test_TLogowanie extends ColumnFixture {

    String login;
    String haslo;
    String TestLogin;
    String TestHaslo;

    public boolean Weryfikacja_Danych_Logowania() {

        TLogowanie test = new TLogowanie();
        test.Dodaj_Konto(login, haslo);
        return test.Weryfikacja_Danych_Logowania(TestLogin, TestHaslo);

    }
    
    public boolean Dodaj_Osobe() {

        TLogowanie test = new TLogowanie();
        test.Dodaj_Konto(login, haslo);
        return test.Weryfikacja_Danych_Logowania(TestLogin, TestHaslo);

    }

}
