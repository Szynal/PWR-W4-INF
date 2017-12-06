/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;
import java.util.List;

/**
 *
 * @author Pawel Szynal & Krzysztof Soboscinski
 */
public class TLogowanie 
{
    private List<TKonto> _ListaOsob;   
    
    public TLogowanie(){}

    
    public boolean Weryfikacja_Danych_Logowania(String login, String haslo)
    {
        TKonto pracownik = new TKonto(login,haslo);
        boolean weryfikacja = false;
        
        for(int i =0 ;i< _ListaOsob.size(); i++)       
        {
            if(pracownik == _ListaOsob.get(i))
            {
                weryfikacja = true;
            }
        }                       
        return weryfikacja;
    }
}
