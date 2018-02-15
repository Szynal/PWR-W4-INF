/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testy_fitnesse_fixture;

import Warstwa_biznesowa.TKonto;
import fit.ColumnFixture;

/**
 *
 * @author Pawel Szynal
 */
public class Test_TKonto extends ColumnFixture {

    String login;
    String haslo;

    String testLogin;
    String testHaslo;

    public boolean Test_Set_Get_TKonto() {

        TKonto osoba = new TKonto("login", "haslo");

        osoba.Set_Login(login);
        osoba.Set_Haslo(haslo);

        return osoba.Get_Haslo().equals(testHaslo) && osoba.Get_Login().equals(testLogin);

    }

}
