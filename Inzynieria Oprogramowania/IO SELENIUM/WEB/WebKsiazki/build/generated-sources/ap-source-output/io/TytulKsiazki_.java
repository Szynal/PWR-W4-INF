package io;

import io.Ksiazka;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-17T23:12:39")
@StaticMetamodel(TytulKsiazki.class)
public class TytulKsiazki_ { 

    public static volatile CollectionAttribute<TytulKsiazki, Ksiazka> ksiazkaCollection;
    public static volatile SingularAttribute<TytulKsiazki, Integer> tytulId;
    public static volatile SingularAttribute<TytulKsiazki, String> isbn;
    public static volatile SingularAttribute<TytulKsiazki, String> tytul;
    public static volatile SingularAttribute<TytulKsiazki, String> autorImie;
    public static volatile SingularAttribute<TytulKsiazki, String> autorNazwisko;
    public static volatile SingularAttribute<TytulKsiazki, String> wydawnictwo;

}