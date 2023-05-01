<%@ page import="models.MusicModel" %><%--
  Created by IntelliJ IDEA.
  Editor: z
  Date: 4/30/2023
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.15.1/js/all.js"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.15.1/js/v4-shims.js"></script>
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
            width: 100%;
            height: 350px;
            text-align: center;
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

            text-align: center;
        }
        h2 {
            padding: 0 1em;

            text-align: center;
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
            margin: 0 auto -ms-transform: translateY(-15%);
            transform: translateY(-15%);
        }

        .login {
            position: relative;
            float: right;
            margin: 0 auto -ms-transform: translateY(-60%);
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
            background-color: #04AA6D;
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
        if (session.getAttribute("currentMusic") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            //request.setAttribute("error", "Please login to continue!");
            requestDispatcher.forward(request, response);
        }
    }
    MusicModel currentMusic = (MusicModel) session.getAttribute("currentMusic");
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
    <div class="container" id="rcorners0">
        <div class="block"> <img class="float" id="left" src="<%=currentMusic.getPhoto_filename()%>" alt="Picture" width="250" height="200"></div>
        <h1><%=currentMusic.getReview_title()%></h1>
        <h1> <%=currentMusic.getAlbum_title()%> , <%=currentMusic.getAlbum_rating()%> </h1>
        <h2>By: <%=currentMusic.getArtist_name()%></h2>
        <table style="width:100%">
            <tbody>
                 <tr>
                     <td>
                         <% for(int i =0; i < 5; i++){%>
                         <% if(i < currentMusic.getNumber_of_stars()){ %>
                         <span class="fas fa-star"></span>
                         <%} else { %>
                         <span class="far fa-star"></span>
                         <% } %>
                         <% } %>
                     </td>
                </tr>
                <tr>
                </tr>
            </tbody>
        </table>
        <div class="container" id="rcorners3"> </div>
        <div class="container" id="rcorners2"> <%= currentMusic.getReview_body() %> </div>
    </div>
</section>
</body>
</html>
