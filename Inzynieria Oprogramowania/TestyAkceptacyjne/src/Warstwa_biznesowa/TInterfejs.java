/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

import java.util.Scanner;
import java.io.IOException;
import java.util.IllegalFormatCodePointException;

/**
 *
 * @author Pawel Szynal & Krzysztof Soboscinski
 */
public class TInterfejs {

    public TInterfejs() {
    }

  public  TLogowanie weryfikacja = new TLogowanie();
   public TAplikacja aplikacja = new TAplikacja();

    boolean zalogowany = false;

    public void Logowanie() throws IllegalFormatCodePointException {
        String login, haslo;
        Scanner odczyt = new Scanner(System.in);
        System.out.flush();
        odczyt.nextLine();
        System.out.println("Podaj login:");
        login = odczyt.nextLine();
        System.out.println("Podaj haslo:");
        haslo = odczyt.nextLine();

        zalogowany = weryfikacja.Weryfikacja_Danych_Logowania(login, haslo);
    }

    public TOsoba PodajOsobe(TOsoba osoba) throws IllegalFormatCodePointException {

        Scanner odczyt = new Scanner(System.in);
        Czyszczenie_Konsoli();
        System.out.println("Podaj Imie:");
        osoba.Set_Imie(odczyt.next());

        Czyszczenie_Konsoli();
        System.out.println("Podaj Nazwisko:");
        osoba.Set_Nazwisko(odczyt.next());

        Czyszczenie_Konsoli();
        System.out.println("Podaj Obywatelstwo:");
        osoba.Set_Obywatelstwo(odczyt.next());

        Czyszczenie_Konsoli();
        System.out.println("Podaj DateUrodzenia (dd.mm.rrrr):");
        osoba.Set_DataUrodzenia(odczyt.next());

        Czyszczenie_Konsoli();
        System.out.println("Podaj DateZgonu (dd.mm.rrrr / - ):");
        osoba.Set_DataZgonu(odczyt.next());

        Czyszczenie_Konsoli();
        System.out.println("Podaj Stan Cywilny:");
        osoba.Set_StanCywilny(odczyt.next());

        return osoba;
    }

    public void Czyszczenie_Konsoli() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws IOException, IllegalFormatCodePointException {
        Scanner odczyt = new Scanner(System.in);
        TInterfejs interfejs = new TInterfejs();
        interfejs.weryfikacja.Dodaj_Konto("p", "h");
        char operation = '0';

        while (operation != 'e') {

            System.out.println("EwidencjaLudnosci");
            System.out.println("1. Logowanie");
            System.out.println("2. Kontynuuj bez logowania");
            System.out.println("\nPodaj numer operacji lub (e) aby opuscic program:");

            operation = (char) System.in.read();
            System.out.flush();
            switch (operation) {
                case '1': {
                    interfejs.Logowanie();
                    if (interfejs.zalogowany) {
                        while (operation != '4') {
                            System.out.println("EwidencjaLudnosci - zalogowany");
                            System.out.println("1. Dodaj osobe do Ewidencji");
                            System.out.println("2. Aktualizuj osobe w Ewidencji");
                            System.out.println("3. Wyswieltanie danych mieszkanca");
                            System.out.println("4. Wyloguj");
                            System.out.println("\nPodaj numer operacji:");

                            operation = (char) System.in.read();

                            switch (operation) {

                                case '1': {
                                    operation = '0';
                                    long pesel = 0;
                                    System.in.read();
                                    System.out.println("Podaj pesel:");

                                    pesel = odczyt.nextLong();

                                    if (!interfejs.aplikacja.Czy_Istnieje(pesel)) {
                                        TOsoba nowy = new TOsoba(pesel);
                                        nowy = interfejs.PodajOsobe(nowy);
                                        interfejs.aplikacja.Dodaj_Osobe_Do_Ewidencji(nowy);

                                    } else {
                                        interfejs.Czyszczenie_Konsoli();
                                        System.out.println("Istnieje osoba o podanycm PESELu\n");
                                    }
                                    break;
                                }
                                case '2': {
                                    operation = '0';

                                    long pesel;
                                    System.in.read();
                                    System.out.println("Podaj pesel:");
                                    try {
                                        pesel = odczyt.nextLong();
                                        interfejs.aplikacja.Aktualizacja_Danych_Osobowych(pesel);
                                    } catch (Exception e) {
                                        System.err.println("Niprawidlowy format\n");
                                    }

                                    break;
                                }

                                case '3': {
                                    operation = '0';

                                    long pesel;
                                    System.out.println("Podaj pesel:");
                                    try {
                                        pesel = odczyt.nextLong();
                                        TOsoba daneOsobowe = new TOsoba(pesel);
                                        daneOsobowe = interfejs.PodajOsobe(daneOsobowe);
                                        interfejs.aplikacja.Wyswietlanie_Danych_Mieszkancow(daneOsobowe);
                                    } catch (Exception e) {
                                        System.out.println("Nieprawidlowy format\n");
                                    }
                                    break;
                                }

                                case '4': {
                                    operation = '4';
                                    break;
                                }
                                default: {
                                    break;
                                }
                            }
                        }

                    } else {
                        System.out.println("Nieprawidlowe dane logowania");
                        System.in.read();

                    }
                    break;
                }
                case '2': {
                    operation = '0';

                    while (operation != '2') {
                        System.out.println("EwidencjaLudnosci - ograniczony dostep");
                        System.out.println("1. Wyswieltanie danych mieszkanca");
                        System.out.println("2. Powrot do logowania");
                        System.out.println("\nPodaj numer operacji:");

                        System.out.println("\nPodaj numer operacji:");
                        operation = (char) System.in.read();

                        switch (operation) {
                            case '1': {
                                TOsoba daneOsobowe = new TOsoba();
                                daneOsobowe = interfejs.PodajOsobe(daneOsobowe);

                                interfejs.aplikacja.Wyswietlanie_Danych_Mieszkancow(daneOsobowe);

                                break;
                            }
                            case '2': {
                                break;
                            }
                            default: {
                                System.out.println("Podano nieprawidłowy numer operacji\n"
                                        + "Podaj numer operacji:");
                            }

                        }
                    }
                    break;
                }

            }
        }
    }

}
