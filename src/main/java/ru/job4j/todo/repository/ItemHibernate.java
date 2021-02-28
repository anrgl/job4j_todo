package ru.job4j.todo.repository;

import ru.job4j.todo.model.Item;

import java.util.List;

public class ItemHibernate {
    private final SessionWrapper wrapper = new SessionWrapper();

    private static final class Lazy {
        private static final ItemHibernate INST = new ItemHibernate();
    }

    public static ItemHibernate instOf() {
        return Lazy.INST;
    }

    public void add(Item item) {
        wrapper.tx(session -> session.save(item));
    }

    public List<Item> findAll() {
        return wrapper.tx(
                session -> session.createQuery(
                        "select distinct i from Item i join fetch i.categories order by i.id",
                        Item.class)
                        .list());
    }

    public List<Item> findCompleteTasks() {
        return wrapper.tx(session -> session.createQuery(
                "select distinct i from Item i "
                        + "join fetch i.categories "
                        + "where i.done = false order by i.id",
                Item.class)
                .list());
    }

    public void updateStatus(int id, boolean done) {
        wrapper.tx(session -> session.createQuery(
                "update ru.job4j.todo.model.Item set done = :done where id = :id")
                .setParameter("done", done)
                .setParameter("id", id)
                .executeUpdate());
    }
}
