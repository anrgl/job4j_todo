package ru.job4j.todo.servlet;

import com.google.gson.Gson;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.repository.ItemHibernate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllItemsServlet extends HttpServlet {
    private final ItemHibernate store = new ItemHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        boolean isDone = Boolean.parseBoolean(req.getParameter("checked"));
        List<Item> items = isDone
                ? store.findCompleteTasks()
                : store.findAll();
        new Gson().toJson(items, resp.getWriter());
        System.out.println("Get All Items Servlet");
        System.out.println(isDone);
    }
}
