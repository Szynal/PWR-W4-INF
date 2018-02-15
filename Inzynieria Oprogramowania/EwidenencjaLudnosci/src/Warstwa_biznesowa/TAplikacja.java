/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.IllegalFormatCodePointException;

/**
 *
 * @author Pawel Szynal & Krzysztof Soboscinski
 */
public class TAplikacja {

    public List<TOsoba> _Ewidencja = new ArrayList<TOsoba>();

    public void Dodaj_Osobe_Do_Ewidencji(TOsoba daneOsobowe) throws IllegalFormatCodePointException {
        if (!(Czy_Istnieje(daneOsobowe.Get_PESEL()))) {
            _Ewidencja.add(daneOsobowe);
            System.out.println("Osoba zostala dodana do Ewidencji\n");
        } else {
            System.out.println("Osoba o podanym PESELu istnieje.");
            System.out.println("Osoba nie zostala dodana do Ewidencji\n");
        }
    }

    public boolean Czy_Istnieje(long pesel) throws IllegalFormatCodePointException {
        boolean czyJest = false;

        for (int i = 0; i < _Ewidencja.size(); i++) {
            if (_Ewidencja.get(i).Get_PESEL() == pesel) {
                czyJest = true;
            }
        }
        return czyJest;
    }

    public TOsoba Wyszukaj_Osobe(long pesel) throws IllegalFormatCodePointException {
        TOsoba wyszukiwanaOsoba = new TOsoba(pesel);
        for (int i = 0; i < _Ewidencja.size(); i++) {
            if (_Ewidencja.get(i).Get_PESEL() == pesel) {
                wyszukiwanaOsoba = _Ewidencja.get(i);
                break;
            }
        }
        return wyszukiwanaOsoba;
    }

    public void Aktualizacja_Danych_Osobowych(long pesel) throws IllegalFormatCodePointException {

        for (int i = 0; i < _Ewidencja.size(); i++) {
            if (_Ewidencja.get(i).Get_PESEL() == pesel) {
                char wybor;

                try {
                    boolean wyjscie = true;
                    Scanner odczyt = new Scanner(System.in);
                    while (wyjscie) {
                        System.out.print("Aktualzuj: \n");
                        System.out.print("1. Imie \n");
                        System.out.print("2. Nazwisko \n");
                        System.out.print("3. Obywatelstwo \n");
                        System.out.print("4. Data urodzenia \n");
                        System.out.print("5. Data zgonu \n");
                        System.out.print("6. Stan cywilny \n");
                        System.out.print("7. Wyjscie \n");

                        wybor = (char) System.in.read();
                        odczyt.nextLine();
                        String zamiana;
                        switch (wybor) {

                            case '1': {
                                System.out.print("Imie: ");
                                zamiana = odczyt.nextLine();
                                _Ewidencja.get(i).Set_Imie(zamiana);
                                System.out.print("Zaktualizowano\n");
                                break;
                            }
                            case '2': {
                                System.out.print("Nazwisko: ");
                                zamiana = odczyt.nextLine();
                                _Ewidencja.get(i).Set_Nazwisko(zamiana);
                                System.out.print("Zaktualizowano\n");
                                break;
                            }

                            case '3': {
                                System.out.print("Obywatelstwo: ");
                                zamiana = odczyt.nextLine();
                                _Ewidencja.get(i).Set_Obywatelstwo(zamiana);
                                System.out.print("Zaktualizowano\n");
                                break;
                            }

                            case '4': {
                                System.out.print("Data urodzenia: ");
                                zamiana = odczyt.nextLine();
                                _Ewidencja.get(i).Set_DataUrodzenia(zamiana);
                                System.out.print("Zaktualizowano\n");
                                break;
                            }

                            case '5': {
                                System.out.print("Data zgonu: ");
                                zamiana = odczyt.nextLine();
                                _Ewidencja.get(i).Set_DataZgonu(zamiana);
                                System.out.print("Zaktualizowano\n");
                                break;
                            }

                            case '6': {
                                System.out.print("Stan cywilny ");
                                zamiana = odczyt.nextLine();
                                _Ewidencja.get(i).Set_StanCywilny(zamiana);
                                System.out.print("Zaktualizowano\n");
                                break;
                            }

                            case '7': {
                                wyjscie = false;
                                break;
                            }

                            default: {
                                break;
                            }
                        }

                        System.out.flush(); // czyszczenie konsoli
                    }

                } catch (IOException e) {
                    System.err.println("Nieprawidlowy format wejsciowy");
                }

                break;
            }
        }
    }

    public List<TOsoba> Wyswietlanie_Danych_Mieszkancow(TOsoba daneOsobowe) throws IllegalFormatCodePointException {
        List<TOsoba> wyszukiwaneOsoby = new LinkedList<TOsoba>();

        for (int i = 0; i < _Ewidencja.size(); i++) {
            if (_Ewidencja.get(i).Get_PESEL() == daneOsobowe.Get_PESEL()
                    || _Ewidencja.get(i).Get_Imie() == daneOsobowe.Get_Imie()
                    || _Ewidencja.get(i).Get_Nazwisko() == daneOsobowe.Get_Nazwisko()
                    || _Ewidencja.get(i).Get_Obywatelstwo() == daneOsobowe.Get_Obywatelstwo()
                    || _Ewidencja.get(i).Get_DataUrodzenia() == daneOsobowe.Get_DataUrodzenia()
                    || _Ewidencja.get(i).Get_DataZgonu() == daneOsobowe.Get_DataZgonu()
                    || _Ewidencja.get(i).Get_StanCywilny() == daneOsobowe.Get_StanCywilny()) {
                wyszukiwaneOsoby.add(_Ewidencja.get(i));
            }
        }

        for (int i = 0; i < wyszukiwaneOsoby.size(); i++) {
            System.out.println("PESEL: " + String.format("%d", wyszukiwaneOsoby.get(i).Get_PESEL())
                    + "  Imie: " + wyszukiwaneOsoby.get(i).Get_Imie()
                    + "  Nazwisko: " + wyszukiwaneOsoby.get(i).Get_Nazwisko()
                    + "  Obywatelstwo: " + wyszukiwaneOsoby.get(i).Get_Obywatelstwo()
                    + "  Data Urodzenia: " + wyszukiwaneOsoby.get(i).Get_DataUrodzenia()
                    + "  Data Zgonu: " + wyszukiwaneOsoby.get(i).Get_DataZgonu()
                    + "  Stan Cywilny: " + wyszukiwaneOsoby.get(i).Get_StanCywilny()
            );
            System.out.println("\n\n");
        }

        System.out.println("Czy eksportowac do pliku? [T/N] ");
        Scanner odczyt = new Scanner(System.in);
        String o = odczyt.nextLine();
        //  

        if (o.equals("t") || o.equals("T")) {
            TEksportowanie eksportowanie = new TEksportowanie();
            eksportowanie.Eksportowanie_Do_Pliku(wyszukiwaneOsoby, eksportowanie.Get_Sciezka());
        }

        return wyszukiwaneOsoby;
    }
}
