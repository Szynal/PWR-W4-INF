/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Pawel Szynal & Krzysztof Soboscinski
 */
public class TAplikacja 
{
   private List<TOsoba> _Ewidencja;
    
    
    public void Dodaj_Osobe_Do_Ewidencji(TOsoba daneOsobowe)
    {
        _Ewidencja.add(daneOsobowe);
    }

    public boolean Czy_Istnieje(long pesel)
    {
        boolean czyJest = false;
        
        for(int i =0 ; i<_Ewidencja.size(); i++)
        {
           if(_Ewidencja.get(i).Get_PESEL() == pesel)
           {
               czyJest = true;
           }           
        }        
        return czyJest;
    }

    public TOsoba Wyszukaj_Osobe(long pesel)
    {
        TOsoba wyszukiwanaOsoba = new TOsoba(pesel);
        for(int i=0; i<_Ewidencja.size();i++)
        {
            if(_Ewidencja.get(i).Get_PESEL() == pesel)
            {
                  wyszukiwanaOsoba = _Ewidencja.get(i);
                  break;
            }                          
        }        
        return wyszukiwanaOsoba;
    }

    public void Aktualizacja_Danych_Osobowych(long pesel)
    {
        for(int i=0; i<_Ewidencja.size();i++)
        {
            if(_Ewidencja.get(i).Get_PESEL() == pesel)
            {
                  int wybor; 
                                    
                  try
                  {
                      boolean wyjscie = true;
                      Scanner odczyt = new Scanner(System.in);
                       while(wyjscie)
                    { 
                        System.out.print("Aktualzuj: \n");
                        System.out.print("1. Imie \n");
                        System.out.print("2. Nazwisko \n");
                        System.out.print("3. Obywatelstwo \n");
                        System.out.print("4. Data urodzenia \n");  
                        System.out.print("5. Data zgonu \n");
                        System.out.print("6. Stan cywilny \n");  
                        System.out.print("7. Wyjscie \n"); 
                      
                         wybor = System.in.read();
                         
                         System.out.flush(); // czyszczenie konsoli
                         
                        switch (wybor)
                        {   
                                                            
                               case 1:
                               {   
                                   System.out.print("Imie: ");                                   
                                   _Ewidencja.get(i).Set_Imie(odczyt.nextLine());
                                break;
                               }
                                case 2:
                               {   
                                   System.out.print("Nazwisko: ");                                   
                                   _Ewidencja.get(i).Set_Nazwisko(odczyt.nextLine());
                                break;
                               } 

                                 case 3:
                               {   
                                   System.out.print("Obywatelstwo: ");                                  
                                   _Ewidencja.get(i).Set_Obywatelstwo(odczyt.nextLine());
                                break;
                               } 

                                case 4:
                               {   
                                   System.out.print("Data urodzenia: ");                                 
                                   _Ewidencja.get(i).Set_DataUrodzenia(odczyt.nextLine());
                                break;
                               } 

                                  case 5:
                               {   
                                   System.out.print("Data zgonu: ");
                                   _Ewidencja.get(i).Set_DataZgonu(odczyt.nextLine());
                                break;
                               }

                                case 6:
                               {   
                                   System.out.print("Stan cywilny ");
                                   _Ewidencja.get(i).Set_StanCywilny(odczyt.nextLine());
                                break;
                               }

                                case 7:
                               {   
                                   System.out.print("Czy napewno? T/N ");
                                   _Ewidencja.get(i).Set_StanCywilny(odczyt.nextLine());
                                   if(odczyt.nextLine() == "t" || odczyt.nextLine() == "T")
                                   {
                                      wyjscie = false;  
                                   }
                                break;
                               }

                               default:
                               {
                                   break;
                               }
                        } 
                        
                        System.out.flush(); // czyszczenie konsoli
                    }    
                       
                }
                  catch (IOException e)
                  {
                      System.err.println("Nieprawidlowy format wejsciowy");
                  }                                   
                                       
                  break;
            }                          
        }       
    }

    public List<TOsoba> Wyswietlanie_Danych_Mieszkancow(TOsoba daneOsobowe)
    {
        List <TOsoba> wyszukiwaneOsoby = new LinkedList<TOsoba>();
        
        for(int i = 0 ; i <_Ewidencja.size(); i++)
        {
            if(_Ewidencja.get(i).Get_PESEL() == daneOsobowe.Get_PESEL() ||
                    _Ewidencja.get(i).Get_Imie() == daneOsobowe.Get_Imie() ||
                    _Ewidencja.get(i).Get_Nazwisko() == daneOsobowe.Get_Nazwisko() ||
                    _Ewidencja.get(i).Get_Obywatelstwo() == daneOsobowe.Get_Obywatelstwo() ||
                    _Ewidencja.get(i).Get_DataUrodzenia() == daneOsobowe.Get_DataUrodzenia() ||
                    _Ewidencja.get(i).Get_DataZgonu() == daneOsobowe.Get_DataZgonu() ||
                    _Ewidencja.get(i).Get_StanCywilny() == daneOsobowe.Get_StanCywilny())
            {
                wyszukiwaneOsoby.add(_Ewidencja.get(i));
            }
        }    
        
        for (int i = 0; i<wyszukiwaneOsoby.size();i++)
        {
            System.out.println("PESEL: " +  wyszukiwaneOsoby.get(i).Get_PESEL() +
                     "  Imie: " + wyszukiwaneOsoby.get(i).Get_Imie() + 
                     "  Nazwisko: " + wyszukiwaneOsoby.get(i).Get_Nazwisko() + 
                     "  Obywatelstwo: " + wyszukiwaneOsoby.get(i).Get_Obywatelstwo() +
                     "  Data Urodzenia: " + wyszukiwaneOsoby.get(i).Get_DataUrodzenia() +
                     "  Data Zgonu: " + wyszukiwaneOsoby.get(i).Get_DataZgonu() +
                     "  Stan Cywilny: " + wyszukiwaneOsoby.get(i).Get_StanCywilny()
                     );
            System.out.println("\n\n");
        }
        
        System.out.println("Czy eksportowac do pliku? [T/N] ");
        Scanner odczyt = new Scanner(System.in);

        if(odczyt.nextLine() == "t" || odczyt.nextLine() == "T")
        {
        TEksportowanie eksportowanie = new TEksportowanie();
        eksportowanie.Eksportowanie_Do_Pliku(wyszukiwaneOsoby, eksportowanie.Get_Sciezka());
        }
        
        return wyszukiwaneOsoby;
    }   
}

