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
public class TOsoba {

    private long _PESEL;
    private String _Imie;
    private String _Nazwisko;
    private String _Obywatelstwo = "PL";
    private String _DataUrodzenia;
    private String _DataZgonu;
    private String _StanCywilny;

    public TOsoba() {
    }

    public TOsoba(long pesel) {
        this._PESEL = pesel;
    }

    public TOsoba(long pesel, String imie, String nazwisko, String obywatelstwo, String dataUrodzenia, String dataZgonu, String stanCywilny)
            throws IllegalFormatCodePointException {
        this._PESEL = pesel;
        this._Imie = imie;
        this._Nazwisko = nazwisko;
        this._Obywatelstwo = obywatelstwo;
        this._DataUrodzenia = dataUrodzenia;
        this._DataZgonu = dataZgonu;
        this._StanCywilny = stanCywilny;
    }

    public long Get_PESEL() {
        return _PESEL;
    }

    public void Set_Imie(String imie) throws IllegalFormatCodePointException {
        this._Imie = imie;
    }

    public String Get_Imie() {
        return _Imie;
    }

    public void Set_Nazwisko(String nazwisko) throws IllegalFormatCodePointException {
        this._Nazwisko = nazwisko;
    }

    public String Get_Nazwisko() {
        return _Nazwisko;
    }

    public void Set_Obywatelstwo(String obywatelstwo) throws IllegalFormatCodePointException {
        this._Obywatelstwo = obywatelstwo;
    }

    public String Get_Obywatelstwo() {
        return _Obywatelstwo;
    }

    public void Set_StanCywilny(String stanCywilny) throws IllegalFormatCodePointException {
        this._StanCywilny = stanCywilny;
    }

    public String Get_StanCywilny() {
        return _StanCywilny;
    }

    public void Set_DataUrodzenia(String dataUrodzenia) throws IllegalFormatCodePointException {
        this._DataUrodzenia = dataUrodzenia;
    }

    public String Get_DataUrodzenia() {
        return _DataUrodzenia;
    }

    public void Set_DataZgonu(String dataZgonu) throws IllegalFormatCodePointException {
        this._DataZgonu = dataZgonu;
    }

    public String Get_DataZgonu() {
        return _DataZgonu;
    }
}
