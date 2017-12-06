/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
/**
 *
 * @author Pawel Szynal & Krzysztof Soboscinski
 */
public class TEksportowanie 
{
    private String _Sciezka = "plik.txt";
    private PrintWriter _ZapisDoPliku;
   
    public TEksportowanie() {}
    
    public String Get_Sciezka()
    {
     return this._Sciezka;
    }    
       
    public void Eksportowanie_Do_Pliku( List <TOsoba> ewidencja , String sciezka)
    {
        
        try
        {
            _ZapisDoPliku = new PrintWriter(sciezka);
            for(int i =0 ; i < ewidencja.size() ; i++)
            {
             _ZapisDoPliku.println("PESEL: " +  ewidencja.get(i).Get_PESEL() +
                     "  Imie: " + ewidencja.get(i).Get_Imie() + 
                     "  Nazwisko: " + ewidencja.get(i).Get_Nazwisko() + 
                     "  Obywatelstwo: " + ewidencja.get(i).Get_Obywatelstwo() +
                     "  Data Urodzenia: " + ewidencja.get(i).Get_DataUrodzenia() +
                     "  Data Zgonu: " + ewidencja.get(i).Get_DataZgonu() +
                     "  Stan Cywilny: " + ewidencja.get(i).Get_StanCywilny()
                     );
             _ZapisDoPliku.println("\n");
            }           
            _ZapisDoPliku.close();
        }
        catch (FileNotFoundException e )
        {
            System.err.println("Nieprawidlowa sciezka");
        }
         
    }
}
