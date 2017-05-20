package aiss.api.resources;

import aiss.api.model.PepeID;
import aiss.controller.Firebase;
import aiss.model.Pepe;
import org.jboss.resteasy.spi.InternalServerErrorException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

@Path("/gallery")
public class GalleryResource {

    private static final String userID = "test";

    public static GalleryResource _instance = null;

    private GalleryResource(){}

    public static GalleryResource getInstance() {
        if(_instance == null)
            _instance = new GalleryResource();
        return _instance;
    }

    @GET
    public Collection<PepeID> getAll(@QueryParam("reverse") Boolean reverse, @QueryParam("upperlimit") Integer upperlimit, @QueryParam("lowerlimit") Integer lowerlimit){
        try {
            Collection<PepeID> res = new HashSet<>();
            Map<String, Pepe> map = Firebase.getAllPepes(userID);
            for(String id : map.keySet()){
                res.add(new PepeID(id, map.get(id)));
            } return res;
        } catch (IOException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

}
