package aiss.controller.servlets;

import aiss.controller.Clarifai;
import aiss.controller.Firebase;
import aiss.controller.Imgur;
import aiss.model.Concept;
import aiss.model.Pepe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by FELIX on 21/05/2017.
 */
public class GalleryServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Pepe> pepes = Firebase.getAllPepes("");
        request.setAttribute("pepes",pepes);
        request.getRequestDispatcher("gallery.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}