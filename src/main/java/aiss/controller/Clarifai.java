package aiss.controller;

import aiss.model.Concept;
import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.request.input.SearchClause;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.SearchHit;
import clarifai2.dto.input.image.ClarifaiImage;

import java.util.*;

public class Clarifai {

    private static final String CLIENT_ID = "aMMqwNix7jNrVxWx76AJYqlQ9lJGew4sVYd3YakM";
    private static final String CLIENT_SECRET = "LXeuhjDp5iF9Ull2uwETYkEuskyFS1GvT7au9vvi";
    private static final String MODEL_ID = "pepe";

    /**
     *
     * @param rarities
     * @return Simple average of the rarities
     */
    public static Double calculateRarity(Collection<Double> rarities){
        Double res = 0.0;
        for(Double rarity : rarities){
            res += rarity;
        } return res/rarities.size();
    }

    /**
     *
     * @param url
     * @return List of Concepts for the image found in the given url
     */
    public static List<Concept> predictImage(String url){
        List<Concept> res = new ArrayList<>();
        ClarifaiClient client = new ClarifaiBuilder(CLIENT_ID, CLIENT_SECRET).buildSync();
        List list = client.getModelByID(MODEL_ID).executeSync().get().predict().withInputs(ClarifaiInput.forImage(ClarifaiImage.of(url))).executeSync().get().get(0).data();
        for(Object object : list){
            res.add(parseConcept(object.toString()));
        } return res;
    }

    /**
     *
     * @param url
     * @return Map sorted by similarity score, where the keys are URLs pointing to images and the values their corresponding similarity scores
     */
    public static Map<String, Double> reverseImageSearch(String url){
        return reverseImageSearch(url,1);
    }

    public static Map<String, Double> reverseImageSearch(String url, int page){
        if(page < 1)
            throw new RuntimeException("Page index starts at 1");
        Map<String, Double> res = new LinkedHashMap<>();
        ClarifaiClient client = new ClarifaiBuilder(CLIENT_ID, CLIENT_SECRET).buildSync();
        List<SearchHit> list = client.searchInputs(SearchClause.matchImageVisually(ClarifaiImage.of(url))).getPage(page).executeSync().get();
        for(SearchHit searchHit : list){
            Double score = Double.valueOf(searchHit.score());
            String inputImage = searchHit.input().image().toString();
            String imageUrl = inputImage.substring(inputImage.indexOf("url=") + 4, inputImage.length() - 1);
            res.put(imageUrl, score);
        } return res;
    }

    private static Concept parseConcept(String string){
        String[] strings = string.split(",");
        String id = strings[0].substring(strings[0].indexOf("=") + 1, strings[0].length());
        String name = strings[1].substring(strings[1].indexOf("=") + 1, strings[1].length());
        String createdAt = strings[2].substring(strings[2].indexOf("=") + 1, strings[2].length());
        if(createdAt.equals("null"))
            createdAt = null;
        String updateddAt = strings[3].substring(strings[3].indexOf("=") + 1, strings[3].length());
        if(updateddAt.equals("null"))
            updateddAt = null;
        String appID = strings[4].substring(strings[4].indexOf("=") + 1, strings[4].length());
        double value = Double.valueOf(strings[5].substring(strings[5].indexOf("=") + 1, strings[5].length() - 1));
        return new Concept(id,name,createdAt,updateddAt,appID,value);
    }

}
