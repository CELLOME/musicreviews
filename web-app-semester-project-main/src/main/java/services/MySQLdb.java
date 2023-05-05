package services;

import models.MusicModel;
import models.UserModel;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLdb {
    String url = "jdbc:mysql://localhost:3306/mydb";
    String username = "root";
    String password = "1111";
    Connection connection = null;
    static MySQLdb instance = null;


    public MySQLdb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static synchronized MySQLdb getInstance() {
        if(instance == null) {
            instance = new MySQLdb();
        }
        return instance;
    }

    public long doSignUp(String username, String email, String password)throws SQLException{
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Chicago" ) );
        String SQL = "INSERT INTO users(name,email, password, creation_date, privilege) "
                + "VALUES(?,?,?,?,?)";

        long id = 0;
        PreparedStatement pstmt = connection.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, username);
        pstmt.setString(2, email);
        pstmt.setString(3, password);
        pstmt.setDate(4, java.sql.Date.valueOf(todayLocalDate));
        pstmt.setInt(5, 0);

        int affectedRows = pstmt.executeUpdate();
        // check the affected rows
        if (affectedRows > 0) {
            // get the ID back
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        pstmt.close();

        return id;

    }

    public long doAddReview(String review_title, String album_title, String artist_name, String genres, String photo_filename, String review_body, String album_rating,String number_of_stars)throws SQLException{
        LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "America/Chicago" ) );
        int revID = (int) ((Math.random() * (1000000 - 10)) + 10);
        double version_number = 1.0;
        String SQL = "INSERT INTO reviews(revID, review_title, album_title, single_name, genres, photo_filename, release_date, version_information, review_body, album_rating, number_of_stars) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        long id = 0;
        PreparedStatement preparedStatement = connection.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, revID);
        preparedStatement.setString(2, review_title);
        preparedStatement.setString(3, album_title);
        preparedStatement.setString(4, artist_name);
        preparedStatement.setString(5, genres);
        preparedStatement.setString(6, photo_filename);
        preparedStatement.setDate(7, java.sql.Date.valueOf(todayLocalDate));
        preparedStatement.setDouble(8, version_number);
        preparedStatement.setString(9, review_body);
        preparedStatement.setString(10, album_rating);
        preparedStatement.setDouble(11, Double.parseDouble(number_of_stars));

        int affectedRows = preparedStatement.executeUpdate();
        // check the affected rows
        if (affectedRows > 0) {
            // get the ID back
            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        preparedStatement.close();

        return id;

    }


    public UserModel doLogin(String username, String password) throws SQLException {
        UserModel userModel = null;

        // Statement
        String qLogin = "SELECT * FROM users WHERE name = '"+ username +"' AND password = '"+ password +"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qLogin);

        if(resultSet.next()) {
            String email = resultSet.getString("email");
            int privilege = resultSet.getInt("privilege");

            userModel = new UserModel(email, username, password, privilege);
        }
        resultSet.close();
        statement.close();
//        preparedStatement.close();
        return userModel;

    }

    public List<MusicModel> fetchMusic() throws SQLException {
        String qGetMusic = null;
        List<MusicModel> list = new ArrayList<>();

        qGetMusic = "SELECT R.revID, R.review_title, R.album_title, R.artist_name, R.genres, R.photo_filename, R.release_date, R.version_information, R.review_body, R.album_rating, R.number_of_stars FROM reviews as R Order by rand()";

        PreparedStatement preparedStatement = connection.prepareStatement(qGetMusic);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int revID = resultSet.getInt("revID");
            String review_title = resultSet.getString("review_title");
            String album_title = resultSet.getString("album_title");
            String artist_name = resultSet.getString("artist_name");
            String genres = resultSet.getString("genres");
            String photo_filename = resultSet.getString("photo_filename");
            Date release_date = resultSet.getDate("release_date");
            float version_information = resultSet.getFloat("version_information");
            String review_body = resultSet.getString("review_body");
            String album_rating = resultSet.getString("album_rating");
            float number_of_stars = resultSet.getFloat("number_of_stars");
            MusicModel musicModel = new MusicModel(revID, review_title, album_title, artist_name, genres, photo_filename, release_date, version_information, review_body, album_rating, number_of_stars);
            list.add(musicModel);
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }

    public MusicModel fetchSingleMusic(int revID) throws SQLException {
        String qGetMusic = null;
        MusicModel musicModel = null;

        qGetMusic = "SELECT R.revID, R.review_title, R.album_title, R.artist_name, R.genres, R.photo_filename, R.release_date, R.version_information, R.review_body, R.album_rating, R.number_of_stars FROM reviews as R WHERE R.revID = '"+ revID +"'";

        PreparedStatement preparedStatement = connection.prepareStatement(qGetMusic);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            String review_title = resultSet.getString("review_title");
            String album_title = resultSet.getString("album_title");
            String artist_name = resultSet.getString("artist_name");
            String genres = resultSet.getString("genres");
            String photo_filename = resultSet.getString("photo_filename");
            Date release_date = resultSet.getDate("release_date");
            float version_information = resultSet.getFloat("version_information");
            String review_body = resultSet.getString("review_body");
            String album_rating = resultSet.getString("album_rating");
            float number_of_stars = resultSet.getFloat("number_of_stars");
            musicModel = new MusicModel(revID, review_title, album_title, artist_name, genres, photo_filename, release_date, version_information, review_body, album_rating, number_of_stars);
        }
        resultSet.close();
        preparedStatement.close();

        return musicModel;
    }


}
