package ru.job4j.todo.servlet;

import ru.job4j.todo.model.Item;
import ru.job4j.todo.repository.ItemHibernate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

public class CreateItemServlet extends HttpServlet {
    private final ItemHibernate store = new ItemHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String description = req.getParameter("description");
        Timestamp created = new Timestamp(new Date().getTime());
        store.add(new Item(description, created));
        resp.sendRedirect(req.getContextPath());
    }
}
