/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_biznesowa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Pawel Szynal & Krzysztof Sobocinski
 */
public class Dane {

    public List<TOsoba> osobyTestowe = new LinkedList<TOsoba>(Arrays.asList(new TOsoba(123456780, "A", "A", "PL", "01-01-2000", "-", "kawaler"),
            new TOsoba(123456781, "B", "B", "PL", "02-02-2002", "-", "kawaler"),
            new TOsoba(123456782, "C", "C", "PL", "03-03-2003", "10-03-2003", "kawaler"),
            new TOsoba(123456783, "D", "A", "PL", "04-04-2004", "-", " panna"),
            new TOsoba(123456784, "E", "E", "PL", "05-05-2005", "-", "kawaler"),
            new TOsoba(123456785, "A", "F", "PL", "06-06-2006", "-", "kawaler"),
            new TOsoba(123456786, "G", "G", "PL", "07-07-2007", "-", "kawaler"),
            new TOsoba(123456787, "H", "H", "PL", "08-08-2008", "-", " panna")
    ));

    public List<TKonto> kontaTestowe = new LinkedList<TKonto>(Arrays.asList(new TKonto("pracownik1", "111AAA"),
            new TKonto("pracownik2", "222BBB"),
            new TKonto("pracownik3", "333CCC"),
            new TKonto("pracownik4", "444DDD"),
            new TKonto("pracownik5", "555EEE"),
            new TKonto("pracownik6", "666FFF"),
            new TKonto("pracownik7", "777GGG"),
            new TKonto("pracownik8", "888HHH")
    ));
}
