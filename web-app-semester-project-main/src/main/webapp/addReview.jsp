<%@ page import="models.UserModel" %><%--
  Created by IntelliJ IDEA.
  Editor: z
  Date: 4/30/2023
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252">
    <style>
        #rcorners0 {
            margin: auto;
            border-radius: 25px;
            border: 2px solid #73AD21;
            padding: 20px;
            width: 50%;
            height: 750px;
        }

        #rcorners3 {
            border: none;
            width: 650px;
            height: 10px;
            padding:-5px;
        }
        #rcorners2 {
            border: none;
            border-radius: 25px;
            padding: 20px;
            width: 650px;
            height: 350px;
        }

        img {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            width: 150px;
        }

        #left {
            width: 180px;
            height: 150px;
            object-fit: none;
            border: 0px;
            padding: 0px;
        }

        #center {
            text-align: center;
            border-bottom: 3px solid green;
        }

        #center1 {
            text-align: center;
        }

        table, th, td {
            border:none;
            text-align: center;
        }

        .outer {
            border: 5px dotted rgb(214, 129, 137);
            border-radius: 5px;
            width: 450px;
            padding: 10px;
            margin-bottom: 40px;
        }

        .float {
            padding: 10px;
            border: 5px solid rgba(214, 129, 137, .4);
            border-radius: 5px;
            background-color: rgba(233, 78, 119, .4);
            color: #fff;
            float: left;
            width: 200px;
            margin: 0 20px 0 0;
        }

        section,
        h1 {
            padding: 0 1em;
        }


        img {
            width: 100%;
            height: 80%;
        }

        #rcorners1 {
            border: green solid #73AD21;
            padding: 20px;
            width: 100%;
            height: 45px;
        }

        #rcorners10 {
            border: green solid #73AD21;
            width: 660px;
            height: 805px;
        }

        #center1 {
            text-align: center;
        }

        nav {
            text-align: right;
            list-style: none;
            border: none;
            border-color: white;
            background-color: none;
            padding: none;
            margin: none;
        }

        li {
            color: green;
        }

        .search {
            position: relative;
            top: -10%;
            float: left;
            margin: 0 auto;
            -ms-transform: translateY(-15%);
            transform: translateY(-15%);
        }

        .login {
            position: relative;
            float: right;
            margin: 0 auto;
            -ms-transform: translateY(-60%);
            transform: translateY(-60%);
        }

        .wrapper {
            display: grid;
            grid-gap: 5px 10px;
            grid-template-columns: 75% 25%;
            grid-template-rows: 65px 20px;
            background-color: #fff;
            color: #444;
        }

        .box {
            background-color: #444;
            color: #fff;
            border-radius: 5px;
            padding: 20px;
            font-size: 150%;
        }

        .a {
            grid-column: 1 / 2;
            grid-row: 1 /2;
            text-align: center;
        }

        .b {
            grid-column: 2;
            grid-row: 1/2;
        }

        .c {
            grid-column: 1 /3;
            grid-row: 2;
            text-align: right;
        }


        img {
            width: 90%;
            max-height: 100%;
        }

        hr {
            display: inline-block;
        }
        .twentysixpoint {line-height: 3pt;}

        .scrollabletextbox {
            height:300px;
            width:600px;
            font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif;
            font-size: 82%;
            overflow:scroll;
        }

        /* Set a style for the submit button */
        .submitbtn {
            background-color: #ffa500;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        .submitbtn:hover {
            opacity: 1;
        }
    </style>
</head>
<body>
<%
    if (request.getSession() != null) {
        if (session.getAttribute("user") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            //request.setAttribute("error", "Please login to continue!");
            requestDispatcher.forward(request, response);
        } else{
            UserModel user = (UserModel) session.getAttribute("user");
            if (user.getPrivilege()!=1){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/FetchMusicServlet");
                request.setAttribute("error", "You do not have admin access");
                requestDispatcher.forward(request, response);
            }
        }
    }
%>
<section id="center1">
    <div class="container" id="rcorners1">
        <div class="wrapper">
            <div class="box a">Reviews from the Stars</div>
            <div class="box b">
                <nav>
                    <li>
                        <div class="search">
                            <div class="row">
                                <a href="LogoutServlet" style="text-align:center;color:white" class="btn">Logout</a>
                            </div>
                        </div>
                    </li>
                </nav>
            </div>
        </div>
    </div>
    <div class="container" id="rcorners_hr">
        <hr style="width:92%"> </div>
    <form action="AddReviewServlet" method="post">
        <div class="container" id="rcorners0">
            <h1>Review An Album</h1>
            <p> <input id="review_title" name="review_title" style="width:70%; height:25px" placeholder="Review title" type="text" required="true"></p>
            <p> <input id="album_title" name="album_title" style="width:70%; height:25px" placeholder="Album title" type="text" required="true"></p>
            <p> <input id="artist_name" name="artist_name" style="width:70%; height:25px" placeholder="Artist Name" type="text" required="true"></p>
            <p> <input id="genres" name="genres" style="width:70%; height:25px" placeholder="Genres" type="text"></p>
            <table>
                <tbody>
                <tr>
                    <td style="text-align:right; width:65%">Star rating:</td>
                    <td>
                        <select name="number_of_stars" id="number_of_stars" style="width:150px">
                            <option value="1">1 star</option>
                            <option value="2">2 stars</option>
                            <option value="3">3 stars</option>
                            <option value="4">4 stars</option>
                            <option value="5">5 stars</option>
                        </select>
                    </td>
                    <td><br>
                    </td>
                </tr>
                <tr>
                </tr>
                </tbody>
            </table>
            <table>
                <tbody>
                <tr>
                    <td style="text-align:right; width:65%">Star rating:</td>
                    <td>
                        <select name="album_rating" id="album_rating" style="width:150px">
                            <option value="EXCLIPIT">EXICLIPIT</option>
                            <option value="CLEAN">CLEAN</option>
                        </select>
                    </td>
                    <td><br>
                    </td>
                </tr>
                <tr>
                </tr>
                </tbody>
            </table>
            <!--<label for="picture">Select a review picture:</label> <input id="picture" name="myfile" type="file">-->
            <p> <input id="photo_filename" name="photo_filename" style="width:70%; height:25px" placeholder="Photo file location" type="text" required="true"></p>
            <textarea class="scrollabletextbox" id="review_body" name="review_body" required="true">Type the review here!</textarea>
            <button type="submit" class="submitbtn">Submit</button>
            <span id="error_msg"></span>
        </div>
    </form>
    <p>${error}</p>
</section>
</body>
</html>

