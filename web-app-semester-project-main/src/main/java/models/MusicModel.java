package models;
import java.util.Date;

public class MusicModel {
     int revID;
     String review_title;
     String album_title;
     String artist_name;
     String genres;
     String photo_filename;
     Date release_date;
     float version_information;
     String review_body;
     String album_rating;
     float number_of_stars;

    public MusicModel(int revID, String review_title, String Album_title, String artist_name, String genres, String photo_filename, Date release_date, float version_information, String review_body, String album_rating, float number_of_stars){
        this.revID = revID;
        this.review_title = review_title;
        this.album_title = Album_title;
        this.artist_name = artist_name;
        this.genres = genres;
        this.photo_filename = photo_filename;
        this.release_date = release_date;
        this.version_information = version_information;
        this.review_body = review_body;
        this.album_rating = album_rating;
        this.number_of_stars = number_of_stars;
    }

    public int getRevID(){
        return revID;
    }

    public void setRevID(int revID){
        this.revID = revID;
    }

    public String getReview_title() {
        return review_title;
    }

    public void setReview_title(String review_title) {
        this.review_title = review_title;
    }

    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }


    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPhoto_filename() {
        return photo_filename;
    }

    public void setPhoto_filename(String photo_filename) {
        this.photo_filename = photo_filename;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public float getVersion_information() {
        return version_information;
    }

    public void setVersion_information(float version_information) {
        this.version_information = version_information;
    }

    public String getReview_body() {
        return review_body;
    }

    public void setReview_body(String review_body) {
        this.review_body = review_body;
    }

    public String getAlbum_rating() {
        return album_rating;
    }

    public void setAlbum_rating(String album_rating) {
        this.album_rating = album_rating;
    }

    public float getNumber_of_stars() {
        return number_of_stars;
    }

    public void setNumber_of_stars(float number_of_stars) {
        this.number_of_stars = number_of_stars;
    }

}
