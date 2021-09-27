package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.function.Function;

public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private synchronized <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void update(Accident accident) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.update(accident);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
/*
    public void update(Accident accident) {
        tx(session ->
                session.update(accident)
                         .createQuery("update Accident set "
                                + "name =: name, "
                                + "description =: desc, "
                                + "address =: address, "
                                + "type =: type, "
                                + "rules =: rules "
                                + "where id =: id")
                        .setParameter("name", accident.getName())
                        .setParameter("desc", accident.getDescription())
                        .setParameter("address", accident.getAddress())
                        .setParameter("type", accident.getType())
                        .setParameter("rules", accident.getRules())
                        .setParameter("id", accident.getId())
        );
    }
    */

    public Collection<Accident> getAccidents() {
        return tx(session ->
                session
                        .createQuery("select distinct ac " +
                                "from Accident ac " +
                                "join fetch ac.type " +
                                "join fetch ac.rules")
                        .list()
        );
    }

    public Collection<AccidentType> getTypes() {
        return tx(session -> session.createQuery("from AccidentType").list());
    }

    public AccidentType findTypeById(int id) {
        return tx(session ->
                session
                        .createQuery("select tp from AccidentType tp where tp.id =: id", AccidentType.class)
                        .setParameter("id", id)
                        .uniqueResult()
        );
    }

    public Accident findAccidentById(int id) {
        return tx(session ->
                session
                        .createQuery("select distinct ac "
                                        + "from Accident ac "
                                        + "join fetch ac.type "
                                        + "join fetch ac.rules "
                                        + "where ac.id =: id",
                                Accident.class)
                        .setParameter("id", id)
                        .uniqueResult()
        );
    }

    public void add(Accident accident) {
        tx(session -> session.save(accident));
    }

    public Collection<Rule> getRules() {
        return tx(session -> session.createQuery("from Rule").list());
    }

    public Rule findRuleById(int id) {
        return tx(session ->
                session
                        .createQuery("select r from Rule r where r.id =: id", Rule.class)
                        .setParameter("id", id)
                        .uniqueResult()
        );
    }
}
