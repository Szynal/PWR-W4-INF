/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

import java.util.IllegalFormatCodePointException;

/**
 *
 * @author Pawel Szynal & Krzysztof Soboscinski
 */
public class TKonto {

    private String _Login;
    private String _Haslo;

    public TKonto() {

    }

    public TKonto(String login, String haslo) throws IllegalFormatCodePointException {
        this._Login = login;
        this._Haslo = haslo;
    }

    public void Set_Login(String login) throws IllegalFormatCodePointException {
        this._Login = login;
    }

    public String Get_Login() {
        return _Login;
    }

    public void Set_Haslo(String haslo) throws IllegalFormatCodePointException {
        this._Haslo = haslo;
    }

    public String Get_Haslo() {
        return _Haslo;
    }

}
