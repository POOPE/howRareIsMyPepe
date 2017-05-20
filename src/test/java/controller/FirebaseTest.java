package controller;

import static org.junit.Assert.*;

import aiss.controller.Firebase;
import aiss.model.Pepe;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class FirebaseTest {

    private static final String filePath = "src/main/webapp/WEB-INF/firebase.json";
    private static final String testID = "test";
    private static String pepeID;
    private static Pepe pepe;

    @BeforeClass
    public static void testAddPepe(){
        final String url = "https://ichef.bbci.co.uk/news/660/cpsprodpb/16620/production/_91408619_55df76d5-2245-41c1-8031-07a4da3f313f.jpg";
        final double rarity = 2.5;
        final String date = "2011-01-18 00:00:00.0";
        pepe = new Pepe(url, rarity, date);
        try {
            pepeID = Firebase.addPepe(filePath, testID, pepe);
            assertTrue(pepeID.length() == 20);  // All Firebase auto-generated IDs are 20 characters long
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPepe(){
        try {
            Pepe pepeFirebase = Firebase.getPepe(filePath, testID, pepeID);
            assertTrue(pepe.equals(pepeFirebase));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllPepe(){
        try {
            Map<String, Pepe> pepeMap = Firebase.getAllPepes(filePath, testID);
            if(pepeMap.keySet().size() > 1)
                throw new RuntimeException("There shouldn't be more than one Pepe stored for the test user, check the Firebase console");
            for(String pepeIDFirebase : pepeMap.keySet()){
                assertTrue(pepeID.equals(pepeIDFirebase));
                assertTrue(pepe.equals(pepeMap.get(pepeIDFirebase)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdatePepe(){
        try {
            pepe.setRarity(5);
            Pepe pepeFirebase = Firebase.updatePepe(filePath, testID, pepeID, pepe);
            assertTrue(pepe.equals(pepeFirebase));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void removePepe(){
        try {
            Firebase.removePepe(filePath, testID, pepeID);
            pepeID = null;
            pepe = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
