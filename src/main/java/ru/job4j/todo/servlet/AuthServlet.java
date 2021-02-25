package ru.job4j.todo.servlet;

import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.UserHibernate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    private final UserHibernate store = UserHibernate.instOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = store.findByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("error", "Wrong email or password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
