package com.cadastroPessoas.erp.util;

import java.io.Serializable;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction trx = em.getTransaction();
        boolean criador = false;

        try {
            if (!trx.isActive()) {
                // truque para fazer rollback no que já passou
                // (senão, um futuro commit confirmaria até mesmo operações sem
                // transação)
                trx.begin();
                trx.rollback();

                // agora sim inicia a transação
                trx.begin();

                criador = true;
            }

            return context.proceed();
        } catch (Exception e) {
            if (trx != null && criador) {
                trx.rollback();
            }

            throw e;
        } finally {
            if (trx != null && trx.isActive() && criador) {
                trx.commit();
            }
        }
    }

}
