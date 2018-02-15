/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_integracji;

import io.TytulKsiazki;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lukasz
 */
@Stateless
public class TytulKsiazkiFacade extends AbstractFacade<TytulKsiazki> {

    @PersistenceContext(unitName = "WebKsiazkiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TytulKsiazkiFacade() {
        super(TytulKsiazki.class);
    }
    
}
