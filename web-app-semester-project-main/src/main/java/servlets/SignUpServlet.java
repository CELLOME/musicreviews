package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import services.MySQLdb;


@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("password-repeat");
        long i = -1;
        if (username == null || username.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.isEmpty() || !password.equals(passwordRepeat)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("error", "Enter a username and email, and make sure the passwords match.");
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }
        MySQLdb db = MySQLdb.getInstance();
        try {
            i = db.doSignUp(username, email, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if (i > 0) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("signup.jsp");
            request.setAttribute("error", "Error in sign up, please try again!!!");
            requestDispatcher.forward(request, response);
        }
    }
}
