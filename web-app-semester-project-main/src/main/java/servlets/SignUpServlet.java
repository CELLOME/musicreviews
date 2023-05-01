package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import models.MusicModel;
import services.MySQLdb;
import models.UserModel;


@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("password-repeat");
        long i = -1;
/*        if(passwordRepeat != password) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("signup.jsp");
            request.setAttribute("error", "Passwords must be the same!");
            requestDispatcher.forward(request, response);
            return;
        }
*/
        MySQLdb db = MySQLdb.getInstance();
        try {
            i = db.doSignUp(username, email, password);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(i != -1) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("signup.jsp");
            request.setAttribute("error", "Error in sign up, please try again!!!");
            requestDispatcher.forward(request, response);
        }
    }
}
