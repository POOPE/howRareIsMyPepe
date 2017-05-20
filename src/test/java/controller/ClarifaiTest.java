package controller;

import static org.junit.Assert.*;

import aiss.controller.Clarifai;
import org.junit.Test;

public class ClarifaiTest {

    private final String url = "https://ichef.bbci.co.uk/news/660/cpsprodpb/16620/production/_91408619_55df76d5-2245-41c1-8031-07a4da3f313f.jpg";

    @Test
    public void testPredictImage(){
        assertTrue(Clarifai.predictImage(url).size()==10);  // The model consists of ten concepts
    }

    @Test
    public void testReverseImageSearch(){
        assertTrue(Clarifai.reverseImageSearch(url).size()>0);  // A call to this method returns the twenty most similar images
    }

}
