/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lukasz
 */
@Entity
@Table(name = "KSIAZKA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ksiazka.findAll", query = "SELECT k FROM Ksiazka k")
    , @NamedQuery(name = "Ksiazka.findByKsiazkaId", query = "SELECT k FROM Ksiazka k WHERE k.ksiazkaId = :ksiazkaId")
    , @NamedQuery(name = "Ksiazka.findByNumer", query = "SELECT k FROM Ksiazka k WHERE k.numer = :numer")})
public class Ksiazka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KSIAZKA_ID")
    private Integer ksiazkaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMER")
    private int numer;
    @JoinColumn(name = "ID_TYTUL", referencedColumnName = "TYTUL_ID")
    @ManyToOne(optional = false)
    private TytulKsiazki idTytul;

    public Ksiazka() {
    }

    public Ksiazka(Integer ksiazkaId) {
        this.ksiazkaId = ksiazkaId;
    }

    public Ksiazka(Integer ksiazkaId, int numer) {
        this.ksiazkaId = ksiazkaId;
        this.numer = numer;
    }

    public Integer getKsiazkaId() {
        return ksiazkaId;
    }

    public void setKsiazkaId(Integer ksiazkaId) {
        this.ksiazkaId = ksiazkaId;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public TytulKsiazki getIdTytul() {
        return idTytul;
    }

    public void setIdTytul(TytulKsiazki idTytul) {
        this.idTytul = idTytul;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ksiazkaId != null ? ksiazkaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ksiazka)) {
            return false;
        }
        Ksiazka other = (Ksiazka) object;
        if ((this.ksiazkaId == null && other.ksiazkaId != null) || (this.ksiazkaId != null && !this.ksiazkaId.equals(other.ksiazkaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "io.Ksiazka[ ksiazkaId=" + ksiazkaId + " ]";
    }
    
}
