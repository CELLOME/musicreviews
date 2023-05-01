package servlets;

import services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddReviewServlet", value = "/AddReviewServlet")
public class AddReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String review_title = request.getParameter("review_title");
        String album_title = request.getParameter("album_title");
        String artist_name = request.getParameter("artist_name");
        String genres = request.getParameter("genres");
        String photo_filename = request.getParameter("photo_filename");
        String review_body = request.getParameter("review_body");
        String album_rating = request.getParameter("album_rating");
        String number_of_stars = request.getParameter("number_of_stars");
        long i = -1;
        MySQLdb db = MySQLdb.getInstance();
        try {
            i = db.doAddReview(review_title, album_title, artist_name, genres, photo_filename, review_body, album_rating, number_of_stars);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        if(i != -1) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/FetchMusicServlet");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("addReview.jsp");
            request.setAttribute("error", "Error adding a review, please try again.");
            requestDispatcher.forward(request, response);
        }
    }
}
