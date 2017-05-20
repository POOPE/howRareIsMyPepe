package aiss.controller;

import aiss.model.Pepe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import org.restlet.resource.ClientResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Firebase {

    private final static String URI = "https://randomrecom.firebaseio.com/";
    private final static String appEnginePath = "WEB-INF/firebase.json";

    /**
     *
     * @param userID
     * @return Map where the keys are the pepeIDs and the values the corresponding Pepe
     * @throws IOException
     */
    public static Map<String,Pepe> getAllPepes(String userID) throws IOException {
        return getAllPepes(appEnginePath, userID);
    }

    public static Map<String,Pepe> getAllPepes(String filePath, String userID) throws IOException {
        String token = getToken(filePath);
        ClientResource clientResource = new ClientResource(URI + userID + ".json");
        clientResource.addQueryParameter("access_token", token);
        clientResource.setEntityBuffering(true);
        String jsonString = clientResource.get().getText();
        return new ObjectMapper().readValue(jsonString, new TypeReference<Map<String, Pepe>>(){});
    }

    /**
     *
     * @param userID
     * @param pepeID
     * @return
     * @throws IOException
     */
    public static Pepe getPepe(String userID, String pepeID) throws IOException {
        return getPepe(appEnginePath, userID, pepeID);
    }

    public static Pepe getPepe(String filePath, String userID, String pepeID) throws IOException {
        String token = getToken(filePath);
        ClientResource clientResource = new ClientResource(URI + userID + "/" + pepeID + ".json");
        clientResource.addQueryParameter("access_token", token);
        clientResource.setEntityBuffering(true);
        return clientResource.get(Pepe.class);
    }

    /**
     *
     * @param userID
     * @param pepe
     * @return The auto-generated pepeID
     * @throws IOException
     */
    public static String addPepe(String userID, Pepe pepe) throws IOException {
        return addPepe(appEnginePath, userID, pepe);
    }

    public static String addPepe(String filePath, String userID, Pepe pepe) throws IOException {
        String token = getToken(filePath);
        ClientResource clientResource = new ClientResource(URI + userID + ".json");
        clientResource.addQueryParameter("access_token", token);
        clientResource.setEntityBuffering(true);
        String answer = clientResource.post(pepe).getText();
        return answer.substring(answer.indexOf(":") + 2, answer.lastIndexOf('"'));
    }

    /**
     *
     * @param userID
     * @param pepeID
     * @throws IOException
     */
    public static void removePepe(String userID, String pepeID) throws IOException {
        removePepe(appEnginePath, userID, pepeID);
    }

    public static void removePepe(String filePath, String userID, String pepeID) throws IOException {
        String token = getToken(filePath);
        ClientResource clientResource = new ClientResource(URI + userID + "/" + pepeID + ".json");
        clientResource.addQueryParameter("access_token", token);
        clientResource.setEntityBuffering(true);
        clientResource.delete();
    }

    /**
     *
     * @param userID
     * @param pepeID
     * @param pepe
     * @return Updated Pepe
     * @throws IOException
     */
    public static Pepe updatePepe(String userID, String pepeID, Pepe pepe) throws IOException {
        return updatePepe(appEnginePath, userID, pepeID, pepe);
    }

    public static Pepe updatePepe(String filePath, String userID, String pepeID, Pepe pepe) throws IOException {
        String token = getToken(filePath);
        ClientResource clientResource = new ClientResource(URI + userID + "/" + pepeID + ".json");
        clientResource.addQueryParameter("access_token", token);
        clientResource.setEntityBuffering(true);
        return new ObjectMapper().readValue(clientResource.put(pepe).getText(), Pepe.class);
    }

    private static String getToken(String filePath) throws IOException {
        GoogleCredential googleCred = GoogleCredential.fromStream(new FileInputStream(filePath));
        GoogleCredential scoped = googleCred.createScoped(
                Arrays.asList(
                        "https://www.googleapis.com/auth/firebase.database",
                        "https://www.googleapis.com/auth/userinfo.email"
                )
        );
        scoped.refreshToken();
        return scoped.getAccessToken();
    }

}
