package ru.job4j.todo.repository;

import ru.job4j.todo.model.User;

public class UserHibernate {
    private final SessionWrapper wrapper = new SessionWrapper();

    private static final class Lazy {
        private static final UserHibernate INST = new UserHibernate();
    }

    public static UserHibernate instOf() {
        return Lazy.INST;
    }

    public void add(User user) {
        wrapper.tx(session -> session.save(user));
    }

    public User findByEmail(String email) {
        return wrapper.tx(
                session -> session.createQuery(
                        "from ru.job4j.todo.model.User where email = :email",
                        User.class)
                        .setParameter("email", email)
                        .getSingleResult());
    }
}
