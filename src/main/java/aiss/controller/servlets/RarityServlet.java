package aiss.controller.servlets;

import aiss.controller.*;
import aiss.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Juan on 5/20/2017.
 *
 * RESPONDS WITH
 * boolean isPepe
 * double score
 *
 * per usual... request.getParameter("isPepe") ...
 */
public class RarityServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uRL = request.getParameter("URL");
        String userID = request.getParameter("userID");
        boolean isPepe = false;
        Double score = 0d;
        String pointer = null;
        StringBuilder verdict = new StringBuilder();
        //missing: multipart file = request.getParameter("file");
        if(!uRL.isEmpty())
        {
            pointer = Imgur.uploadImage(uRL);
        }
        /*
        else // uRL is empty therefore file must not be
        {
            do the same shit done above but convert the file to something that can be uploaded to imgur
        }
         */
        for(Concept c:Clarifai.predictImage(pointer)) {
            if (c.getId().equalsIgnoreCase("pepe") && c.getValue()>0.80d)
            {
                isPepe = true;

                break;
            }
        }
        if(isPepe) {
            score = Clarifai.calculateRarity(Clarifai.reverseImageSearch(pointer).values());
        }
        request.setAttribute("isPepe",isPepe);
        request.setAttribute("score",score);
        Firebase.addPepe(userID, new Pepe(pointer,score));
        String nextJSP;
        nextJSP = "/Result.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}