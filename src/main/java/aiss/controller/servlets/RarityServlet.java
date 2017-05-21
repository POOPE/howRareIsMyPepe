package aiss.controller.servlets;

import aiss.controller.*;
import aiss.model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.jboss.resteasy.spi.BadRequestException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 *
 * RESPONDS WITH
 * boolean isPepe
 * double score
 *
 * per usual... request.getParameter("isPepe") ...
 */

public class RarityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> formFields = new HashMap<>();
        File uploadedFile = null;
        if(ServletFileUpload.isMultipartContent(request)){
            List<FileItem> formItems = new ArrayList<>();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                formItems = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    formFields.put(item.getFieldName(), item.getString());
                } else {
                    uploadedFile = File.createTempFile(item.getFieldName(), item.getContentType(), repository);
                    try {
                        item.write(uploadedFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        String userID = formFields.get("userID");
        String imgurUrl;
        String originalUrl = formFields.get("url");
        if(uploadedFile != null) // An uploaded file has preference over an uploaded URL
            imgurUrl = Imgur.uploadImage(Imgur.encodeFileToBase64(uploadedFile));
        else if(originalUrl != null && !originalUrl.isEmpty()){
            try {
                URL url = new URL(originalUrl);
            } catch (MalformedURLException e) {
                return; // This is not a valid URL
            }
            imgurUrl = Imgur.uploadImage(originalUrl);
        } else
            return; // The user has not uploaded a File, nor a URL

        /*******************************************************************************************/

        boolean isPepe = false;
        Double score = 0.0;
        StringBuilder verdict = new StringBuilder();
        for(Concept c:Clarifai.predictImage(imgurUrl)) {
            if (c.getId().equalsIgnoreCase("pepe") && c.getValue()>0.80d) {
                isPepe = true;
                break;
            }
        }
        if(isPepe)
            score = Clarifai.calculateRarity(Clarifai.reverseImageSearch(imgurUrl).values());
        appendVerdict(isPepe, verdict, score);
        request.setAttribute("isPepe",isPepe);
        request.setAttribute("score",score);
        if(userID != null && !userID.isEmpty()) // If the user has not logged in yet, show the results, but don't upload to Firebase
            Firebase.addPepe(userID, new Pepe(imgurUrl,score));
        String nextJSP = "/Result.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private void appendVerdict(boolean isPepe, StringBuilder verdict, Double score){
        Double n = (Math.random()* 10);
        if(!isPepe) {
            if(n < 5) {
                verdict.append("That's not a Pepe, bud.");
            } else {
                verdict.append("This isn't even a Pepe.");
            }
        } else {
            if(n < 5) {
                verdict.append("This Pepe is");
            } else {
                verdict.append("Well, ");
            } if(score < 5) {
                if(n < 5) {
                    verdict.append(" is not rare at all.");
                } else {
                    verdict.append(" at least its a Pepe.");
                }
            } else if(score > 5 && score < 9) {
                if(n < 5) {
                    verdict.append("ok, I guess.");
                } else {
                    verdict.append(" it's something.");
                }
            } else {
                if(n < 5) {
                    verdict.append("a good find; it's rare.");
                } else {
                    verdict.append(" it's rare for sure.");
                }
            }
        }
    }
}