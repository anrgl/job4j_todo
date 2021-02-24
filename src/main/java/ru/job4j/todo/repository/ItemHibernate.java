package ru.job4j.todo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.todo.model.Item;

import java.util.Collection;
import java.util.List;

public class ItemHibernate {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public void add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
    }

    public List<Item> findAll() {
        List<Item> items;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            items = session.createQuery("from ru.job4j.todo.model.Item", Item.class)
                    .list();
            session.getTransaction().commit();
        }
        return items;
    }

    public List<Item> findCompleteTasks() {
        List<Item> items;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            items = session.createQuery("from ru.job4j.todo.model.Item where done = false", Item.class)
                    .list();
            session.getTransaction().commit();
        }
        return items;
    }
}
