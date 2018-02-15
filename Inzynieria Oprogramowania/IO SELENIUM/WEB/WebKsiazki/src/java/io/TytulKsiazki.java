/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lukasz
 */
@Entity
@Table(name = "TYTUL_KSIAZKI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TytulKsiazki.findAll", query = "SELECT t FROM TytulKsiazki t")
    , @NamedQuery(name = "TytulKsiazki.findByTytulId", query = "SELECT t FROM TytulKsiazki t WHERE t.tytulId = :tytulId")
    , @NamedQuery(name = "TytulKsiazki.findByTytul", query = "SELECT t FROM TytulKsiazki t WHERE t.tytul = :tytul")
    , @NamedQuery(name = "TytulKsiazki.findByAutorNazwisko", query = "SELECT t FROM TytulKsiazki t WHERE t.autorNazwisko = :autorNazwisko")
    , @NamedQuery(name = "TytulKsiazki.findByAutorImie", query = "SELECT t FROM TytulKsiazki t WHERE t.autorImie = :autorImie")
    , @NamedQuery(name = "TytulKsiazki.findByIsbn", query = "SELECT t FROM TytulKsiazki t WHERE t.isbn = :isbn")
    , @NamedQuery(name = "TytulKsiazki.findByWydawnictwo", query = "SELECT t FROM TytulKsiazki t WHERE t.wydawnictwo = :wydawnictwo")})
public class TytulKsiazki implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TYTUL_ID")
    private Integer tytulId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TYTUL")
    private String tytul;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AUTOR_NAZWISKO")
    private String autorNazwisko;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AUTOR_IMIE")
    private String autorImie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "WYDAWNICTWO")
    private String wydawnictwo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTytul")
    private Collection<Ksiazka> ksiazkaCollection;

    public TytulKsiazki() {
    }

    public TytulKsiazki(Integer tytulId) {
        this.tytulId = tytulId;
    }

    public TytulKsiazki(Integer tytulId, String tytul, String autorNazwisko, String autorImie, String isbn, String wydawnictwo) {
        this.tytulId = tytulId;
        this.tytul = tytul;
        this.autorNazwisko = autorNazwisko;
        this.autorImie = autorImie;
        this.isbn = isbn;
        this.wydawnictwo = wydawnictwo;
    }

    public Integer getTytulId() {
        return tytulId;
    }

    public void setTytulId(Integer tytulId) {
        this.tytulId = tytulId;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getAutorNazwisko() {
        return autorNazwisko;
    }

    public void setAutorNazwisko(String autorNazwisko) {
        this.autorNazwisko = autorNazwisko;
    }

    public String getAutorImie() {
        return autorImie;
    }

    public void setAutorImie(String autorImie) {
        this.autorImie = autorImie;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(String wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }

    @XmlTransient
    public Collection<Ksiazka> getKsiazkaCollection() {
        return ksiazkaCollection;
    }

    public void setKsiazkaCollection(Collection<Ksiazka> ksiazkaCollection) {
        this.ksiazkaCollection = ksiazkaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tytulId != null ? tytulId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TytulKsiazki)) {
            return false;
        }
        TytulKsiazki other = (TytulKsiazki) object;
        if ((this.tytulId == null && other.tytulId != null) || (this.tytulId != null && !this.tytulId.equals(other.tytulId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "io.TytulKsiazki[ tytulId=" + tytulId + " ]";
    }
    
}
