package ru.job4j.todo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Item;

import java.util.List;
import java.util.function.Function;

public class ItemHibernate {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final ItemHibernate INST = new ItemHibernate();
    }

    public static ItemHibernate instOf() {
        return Lazy.INST;
    }

    private <T> T tx(final Function<Session, T> command) {
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

    public void add(Item item) {
        this.tx(session -> session.save(item));
    }

    public List<Item> findAll() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.todo.model.Item order by id", Item.class)
                        .list());
    }

    public List<Item> findCompleteTasks() {
        return this.tx(
                session -> session.createQuery("from ru.job4j.todo.model.Item where done = false", Item.class)
                        .list());
    }


    public void updateStatus(int id, boolean done) {
        this.tx(session -> session.createQuery(
                "update ru.job4j.todo.model.Item set done = :done where id = :id")
                .setParameter("done", !done)
                .setParameter("id", id)
                .executeUpdate());
    }
}
