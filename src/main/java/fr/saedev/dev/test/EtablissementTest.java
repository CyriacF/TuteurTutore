package fr.saedev.dev.test;

import fr.saedev.dev.objects.Etablissement;
import fr.saedev.dev.objects.Personne;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class EtablissementTest {

    private Etablissement testEta = new Etablissement("Test_Lille");


    @Test
    public void testEtablissementCreation(){
        Etablissement testEta = new Etablissement("Test_Lille");
        assertNotNull(testEta);
    }

    @Test
    public  void testGetEtablissement(){
        Etablissement testEta = new Etablissement("Test_Lille");
        assertEquals("Test_Lille",testEta.getNom());
    }

    @Test
    public  void testAddPersonne(){

        ArrayList<Personne> testpersonne = new ArrayList<>();
        Personne testP = new Personne("Bloupi","Bloupu",20.0,0,3,0, false, false);
        testpersonne.add(testP);
        assertEquals(testpersonne.get(0),testP);
    }

    @Test
    public  void testRemovePersonne(){

        ArrayList<Personne> testpersonne = new ArrayList<>();
        Personne testP = new Personne("Bloupi","Bloupu",20.0,0,3,0, false, false);
        testpersonne.add(testP);
        testpersonne.remove(testP);
        assertTrue(testpersonne.isEmpty());
    }

    @Test
    public void testAddTutore(){
        Personne testP = new Personne("Bloupi","Bloupu",20.0,0,3,0, false, true);
        ArrayList<Personne> testtutore = new ArrayList<>();
        testtutore.add(testP);
        assertEquals(testtutore.get(0),testP);
    }

    @Test
    public  void testRemoveTutore(){
        Personne testP = new Personne("Bloupi","Bloupu",20.0,0,3,0, false, true);
        ArrayList<Personne> testtutore = new ArrayList<>();
        testtutore.add(testP);
        testtutore.remove(testP);
        assertTrue(testtutore.isEmpty());
    }

    @Test
    public void TestAddTuteur(){
        Personne testP = new Personne("Bloupi","Bloupu",20.0,0,3,0, true, false);
        ArrayList<Personne> testtuteur = new ArrayList<>();
        testtuteur.add(testP);
        assertEquals(testtuteur.get(0),testP);
    }

    @Test
    public void testRemoveTuteur(){
        Personne testP = new Personne("Bloupi","Bloupu",20.0,0,3,0, true, false);
        ArrayList<Personne> testtuteur = new ArrayList<>();
        testtuteur.add(testP);
        testtuteur.remove(testP);
        assertTrue(testtuteur.isEmpty());
    }

}
