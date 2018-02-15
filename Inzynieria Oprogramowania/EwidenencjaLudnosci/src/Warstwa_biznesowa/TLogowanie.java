/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

import java.util.ArrayList;
import java.util.List;
import java.util.IllegalFormatCodePointException;

/**
 *
 * @author Pawel Szynal & Krzysztof Soboscinski
 */
public class TLogowanie {

    public List<TKonto> _ListaOsob = new ArrayList<TKonto>();

    public TLogowanie() {
    }

    public boolean Weryfikacja_Danych_Logowania(String login, String haslo) throws IllegalFormatCodePointException {
        boolean weryfikacja = false;

        for (int i = 0; i < _ListaOsob.size(); i++) {
            if (_ListaOsob.get(i).Get_Login().equals(login)) {
                if (_ListaOsob.get(i).Get_Haslo().equals(haslo)) {
                    weryfikacja = true;
                }
            }
        }
        return weryfikacja;
    }

    public void Dodaj_Konto(String login, String haslo) throws IllegalFormatCodePointException {
        TKonto e = new TKonto(login, haslo);
        boolean jest = false;
        for (int i = 0; i < _ListaOsob.size(); i++) {
            if (_ListaOsob.get(i).Get_Login().equals(login)) {
                jest = true;
            }
        }
        if (jest == false) {
            _ListaOsob.add(e);
        }
    }
}
