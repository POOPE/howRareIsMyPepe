package aiss.api.resources;

import aiss.api.model.PepeID;
import aiss.controller.Clarifai;
import aiss.model.Pepe;
import org.jboss.resteasy.spi.BadRequestException;

import javax.ws.rs.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Path("/rarity")
public class RarityResource {

    public static RarityResource _instance = null;

    private RarityResource(){}

    public static RarityResource getInstance() {
        if(_instance == null)
            _instance = new RarityResource();
        return _instance;
    }

    @POST
    @Consumes("text/plain")
    @Produces("application/json")
    public PepeID rarity(String urlString) {
        try {
            URL url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new BadRequestException("Malformed URL");
        }
        Collection<Double> rarities = Clarifai.reverseImageSearch(urlString).values();
        Double rarity = Clarifai.calculateRarity(rarities);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        return new PepeID("12", new Pepe(urlString, rarity, dateString));
    }

}
