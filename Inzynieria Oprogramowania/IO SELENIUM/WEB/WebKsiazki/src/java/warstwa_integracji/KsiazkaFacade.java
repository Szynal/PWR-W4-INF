/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_integracji;

import io.Ksiazka;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lukasz
 */
@Stateless
public class KsiazkaFacade extends AbstractFacade<Ksiazka> {

    @PersistenceContext(unitName = "WebKsiazkiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KsiazkaFacade() {
        super(Ksiazka.class);
    }
    
}
