/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;
import java.util.Scanner;
import java.io.IOException;
/**
 *
 * @author Pawel Szynal & Krzysztof Soboscinski
 */
public class TInterfejs 
{
    public TInterfejs() {}
    
    public void Logowanie()
    {
        String login,haslo;
        Scanner odczyt = new Scanner(System.in);
        
        System.out.println("Podaj login:");
        login = odczyt.nextLine();
        System.out.println("\nPodaj haslo:");        
        haslo = odczyt.nextLine(); 
        
        TLogowanie weryfikacja = new TLogowanie();
        weryfikacja.Weryfikacja_Danych_Logowania(login, haslo);
        
    }

    public void Komunikat(String tekst)
    {
        System.out.println(tekst);
    }

    public void PodajDane()
    {
        
    }

    public void Wyswietlanie(String parameter)
    {
        
    }
}
