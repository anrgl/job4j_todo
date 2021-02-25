package ru.job4j.todo.servlet;

import ru.job4j.todo.repository.ItemHibernate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateItemServlet extends HttpServlet {
    private final ItemHibernate store = ItemHibernate.instOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("itemId"));
        boolean done = Boolean.parseBoolean(req.getParameter("done"));
        store.updateStatus(id, done);
    }
}
