package aiss.api.resources;

import aiss.api.model.PepeID;
import aiss.controller.Firebase;
import aiss.controller.Imgur;
import aiss.model.Pepe;
import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.InternalServerErrorException;
import org.jboss.resteasy.spi.NotFoundException;

import javax.ws.rs.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Path("/gallery")
public class GalleryResource {

    private static final String userID = "testAPI";

    public static GalleryResource _instance = null;

    private GalleryResource(){}

    public static GalleryResource getInstance() {
        if(_instance == null)
            _instance = new GalleryResource();
        return _instance;
    }

    @GET
    @Produces("application/json")
    public Collection<PepeID> getAll(@QueryParam("reverse") Boolean reverse, @QueryParam("upperlimit") Integer upperlimit, @QueryParam("lowerlimit") Integer lowerlimit){
        try {
            List<PepeID> res = new ArrayList<>();
            Map<String, Pepe> map = Firebase.getAllPepes(userID);
            if(map != null){
                for(String id : map.keySet()){
                    res.add(new PepeID(id, map.get(id)));
                }
            }
            if(reverse != null && reverse)
                Collections.sort(res, new Comparator<PepeID>() {
                @Override
                public int compare(PepeID o1, PepeID o2) {
                    return o1.getPepe().getRarity() > o2.getPepe().getRarity() ? -1 :
                            o1.getPepe().getRarity() < o2.getPepe().getRarity() ? 1 : 0;
                }
            });
            else
                Collections.sort(res, new Comparator<PepeID>() {
                    @Override
                    public int compare(PepeID o1, PepeID o2) {
                        return o1.getPepe().getRarity() > o2.getPepe().getRarity() ? 1 :
                                o1.getPepe().getRarity() < o2.getPepe().getRarity() ? -1 : 0;
                    }
                });
            if(upperlimit != null)
                for(PepeID pepeID : res){
                    if(pepeID.getPepe().getRarity() > upperlimit)
                        res.remove(pepeID);
                }
            if(lowerlimit != null)
                for(PepeID pepeID : res){
                    if(pepeID.getPepe().getRarity() < lowerlimit)
                        res.remove(pepeID);
                }
            return res;
        } catch (IOException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @POST
    @Consumes("text/plain")
    @Produces("application/json")
    public PepeID upload(String urlString){
        try {
            URL url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new BadRequestException("Malformed URL");
        }
        try {
            String url = Imgur.uploadImage(urlString);
            /*
            Collection<Double> rarities = Clarifai.reverseImageSearch(urlString).values();
            Double rarity = Clarifai.calculateRarity(rarities);
            */
            Double rarity = (Math.random()* 10); // Clarifai has an incompatibility with Appengine that we haven't managed to find a workaround to yet.
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String dateString = dateFormat.format(date);
            Pepe pepe = new Pepe(url, rarity, dateString);
            String id = Firebase.addPepe(userID, pepe);
            return new PepeID(id, pepe);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public PepeID get(@PathParam("id") String id){
        try {
            Pepe pepe = Firebase.getPepe(userID, id.trim());
            if(pepe == null)
                throw new NotFoundException("No Pepe with id: " + id.trim());
            return new PepeID(id.trim(), pepe);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes("text/plain")
    @Produces("application/json")
    public PepeID update(@PathParam("id") String id, String rarity) {
        try {
            Pepe pepe = Firebase.getPepe(userID, id.trim());
            if (pepe == null)
                throw new NotFoundException("No Pepe with id: " + id.trim());
            pepe.setRarity(Double.valueOf(rarity));
            pepe = Firebase.updatePepe(userID, id.trim(), pepe);
            return new PepeID(id, pepe);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public PepeID delete(@PathParam("id") String id){
        try {
            Pepe pepe = Firebase.getPepe(userID, id.trim());
            if (pepe == null)
                throw new NotFoundException("No Pepe with id: " + id.trim());
            Firebase.removePepe(userID, id.trim());
            return new PepeID(id, pepe);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}
