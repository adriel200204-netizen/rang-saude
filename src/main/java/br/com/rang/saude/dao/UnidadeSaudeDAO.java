package br.com.rang.saude.dao;

import br.com.rang.saude.model.UnidadeSaude;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UnidadeSaudeDAO {

    private final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("rangSaudePU");

    public UnidadeSaude buscarPorCep(Integer cep) {

        EntityManager entityManager = factory.createEntityManager();

        try {

            return entityManager.createQuery(
                    "SELECT u FROM UnidadeSaude u " +
                    "WHERE u.cepInicio <= :cep " +
                    "AND u.cepFim >= :cep",
                    UnidadeSaude.class)
                    .setParameter("cep", cep)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

        } finally {
            entityManager.close();
        }
    }

    public void salvar(UnidadeSaude unidade) {

        EntityManager entityManager = factory.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(unidade);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw e;

        } finally {

            entityManager.close();
        }
    }
}