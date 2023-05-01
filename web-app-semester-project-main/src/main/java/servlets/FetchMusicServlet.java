package servlets;

import models.MusicModel;
import services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FetchMusicServlet", value = "/FetchMusicServlet")
public class FetchMusicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        if(session != null) {

            try {
                List<MusicModel> musicModelList = db.fetchMusic();
                request.setAttribute("list_of_music", musicModelList);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);



        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("error", "It's broke help me!!!");
            requestDispatcher.forward(request, response);
        }

    }

}
