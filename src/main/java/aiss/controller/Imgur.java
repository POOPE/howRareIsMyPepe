package aiss.controller;

import aiss.model.Pepe;
import aiss.model.Response;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Imgur {

    private final static String URI = "https://api.imgur.com/3/image";
    private static final String CLIENT_ID = "357108b852fc34b";
    private static final String CLIENT_SECRET = "cad5c0afb17373fabb00992afdc41ee828dba11a";

    /**
     *
     * @param image Binary file, base64 data, or a URL for an image of up to 10MB
     * @return URL to the image in Imgur
     * @throws IOException
     */
    public static String uploadImage(String image) throws IOException {
        ClientResource clientResource = new ClientResource(URI);
        ChallengeResponse challengeResponse = new ChallengeResponse(new ChallengeScheme("Client-ID", "Client-ID"));
        challengeResponse.setRawValue(CLIENT_ID);
        clientResource.setChallengeResponse(challengeResponse);
        clientResource.setEntityBuffering(true);
        return clientResource.post(image, Response.class).getData().getLink();
    }

    public static String encodeFileToBase64(File file){
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            return new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }
}
