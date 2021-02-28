package ru.job4j.todo.repository;

import ru.job4j.todo.model.Category;

import java.util.List;

public class CategoryHibernate {
    private final SessionWrapper wrapper = new SessionWrapper();

    private static final class Lazy {
        private static final CategoryHibernate INST = new CategoryHibernate();
    }

    public static CategoryHibernate instOf() {
        return CategoryHibernate.Lazy.INST;
    }

    public List<Category> findAll() {
        return wrapper.tx(session -> session.createQuery("from Category", Category.class).list());
    }

    public Category findById(int id) {
        return wrapper.tx(session -> session.find(Category.class, id));
    }
}
