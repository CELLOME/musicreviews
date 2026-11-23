package servlets;

import models.UserModel;
import services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddReviewServlet", value = "/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        UserModel user = session == null ? null : (UserModel) session.getAttribute("user");
        if (user == null || user.getPrivilege() != 1) {
            response.sendError(user == null ? HttpServletResponse.SC_UNAUTHORIZED : HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        request.setCharacterEncoding("UTF-8");
        String review_title = request.getParameter("review_title");
        String album_title = request.getParameter("album_title");
        String artist_name = request.getParameter("artist_name");
        String genres = request.getParameter("genres");
        String photo_filename = request.getParameter("photo_filename");
        String review_body = request.getParameter("review_body");
        String album_rating = request.getParameter("album_rating");
        String number_of_stars = request.getParameter("number_of_stars");
        if (isBlank(review_title) || isBlank(album_title) || isBlank(artist_name)
                || isBlank(photo_filename) || isBlank(review_body)
                || number_of_stars == null || !number_of_stars.matches("[1-5]")
                || !("CLEAN".equals(album_rating) || "EXPLICIT".equals(album_rating))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("error", "Complete all required review fields and select valid ratings.");
            request.getRequestDispatcher("/addReview.jsp").forward(request, response);
            return;
        }

        long i = -1;
        MySQLdb db = MySQLdb.getInstance();
        try {
            i = db.doAddReview(review_title, album_title, artist_name, genres, photo_filename, review_body, album_rating, number_of_stars);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if (i > 0) {
            response.sendRedirect(request.getContextPath() + "/FetchMusicServlet");
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("addReview.jsp");
            request.setAttribute("error", "Error adding a review, please try again.");
            requestDispatcher.forward(request, response);
        }
    }
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
