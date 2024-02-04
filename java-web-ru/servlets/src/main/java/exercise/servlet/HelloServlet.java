package exercise.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/plain");
        var name = req.getParameter("name");
        req.setAttribute("name", name);
        if (name == null) {
            name = "Guest";
        }
        var message = "Hello, " + name + "!";
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, res);
    }
    // END
}
