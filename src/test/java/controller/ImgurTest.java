package controller;

import aiss.controller.Imgur;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ImgurTest {

    private final String url = "https://ichef.bbci.co.uk/news/660/cpsprodpb/16620/production/_91408619_55df76d5-2245-41c1-8031-07a4da3f313f.jpg";

    @Test
    public void testUploadImage(){
        try {
            assertTrue(Imgur.uploadImage(url) != null && !Imgur.uploadImage(url).isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
