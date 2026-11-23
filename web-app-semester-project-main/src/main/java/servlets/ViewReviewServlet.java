package servlets;

import models.MusicModel;
import services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ViewReviewServlet", value = "/ViewReviewServlet")
public class ViewReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int reviewId;
        try {
            reviewId = Integer.parseInt(request.getParameter("revID"));
            if (reviewId <= 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        MusicModel music;
        try {
            music = MySQLdb.getInstance().fetchSingleMusic(reviewId);
        } catch (SQLException e) {
            throw new ServletException("Could not load review", e);
        }
        if (music == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.setAttribute("currentMusic", music);
        request.getRequestDispatcher("/WEB-INF/viewReview.jsp").forward(request, response);
    }
}
