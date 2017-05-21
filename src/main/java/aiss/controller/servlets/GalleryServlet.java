package aiss.controller.servlets;

import aiss.controller.Firebase;

import aiss.model.Pepe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GalleryServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Pepe> pepes = Firebase.getAllPepes(request.getParameter("userID"));
        if(pepes == null)
            pepes = new HashMap<>();
        request.setAttribute("pepes",pepes);
        request.getRequestDispatcher("/gallery.jsp").forward(request,response);
    }
}