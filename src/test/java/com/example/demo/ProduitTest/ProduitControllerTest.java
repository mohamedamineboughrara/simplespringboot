package com.example.demo.ProduitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.Categorie;
import com.example.demo.repository.CategorieRepository;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.services.IProduitService;
import com.example.demo.services.IcategorieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.Controller.ProduitController;
import com.example.demo.entity.Produit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitControllerTest {

    @Autowired
    private ProduitController produitController;
    @Autowired
    private IcategorieService categorieService;
    @Autowired
    private IProduitService produitService ;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @Test
    public void testAddProduit() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse("2020-02-02");

        Categorie categorie = new Categorie();
        categorie.setNom("Test Category");

        Categorie savedCategorie = categorieService.ajouterCategorie(categorie);

        Produit produit = new Produit();
        produit.setNom("produit test final");
        produit.setDisponible(true);
        produit.setCategorie(savedCategorie);
        produit.setQuantite(10);
        produit.setDateCreation(parsedDate);

        Produit produitSaved = produitController.ajouterProduit(produit);
        assertThat(produitSaved).isNotNull();
        assertThat(produitSaved.getNom()).isEqualTo("produit test final");
        assertEquals(produitSaved.getDisponible(), produit.getDisponible());
        assertThat(produitSaved.getQuantite()).isEqualTo(10);
    }
    @Test
    public void testUpdateProduct() {
        Produit produit = new Produit();
        produit.setNom("Test Product");
        produit.setQuantite(10);
        produit.setDisponible(true);
        produit.setDateCreation(new Date());
        produit.setDateModification(new Date());
        Categorie categorie = new Categorie();
        categorie.setNom("Test Category");

        Categorie savedCategorie = categorieService.ajouterCategorie(categorie);
        produit.setCategorie(savedCategorie);

        Produit produitSaved = produitService.ajouterProduit(produit);

        produitSaved.setNom("Updated Test Product");
        produitSaved.setQuantite(20);
        produitSaved.setDisponible(false);
        Date dateModification = new Date();
        Long categorieId = savedCategorie.getId();
        produitService.modifierProduit(produitSaved.getId(), produitSaved.getNom(), produitSaved.getQuantite(), categorieId, dateModification);

        Optional<Produit> produitRetrieved = produitService.findByID(produitSaved.getId());
        assertTrue(produitRetrieved.isPresent());
        Produit retrievedProduct = produitRetrieved.get();
        assertEquals("Updated Test Product", retrievedProduct.getNom());
        assertEquals(20, retrievedProduct.getQuantite());
        assertEquals(false, retrievedProduct.getDisponible());
    }


    @Test
    public void testDeleteProduit() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse("2020-02-02");
        Produit produit = new Produit();
        Categorie categorie = new Categorie();
        categorie.setNom("Test");

        Categorie savedCategorie = categorieService.ajouterCategorie(categorie);
        produit.setNom("produit test");
        produit.setDisponible(true);
        produit.setCategorie(savedCategorie);
        produit.setQuantite(10);
        produit.setDateCreation(parsedDate);
        produit = produitService.ajouterProduit(produit);
        boolean isDeleted = produitService.supprimerProduit(produit.getId());
        assertTrue(isDeleted);
        Optional<Produit> optionalProduit = produitRepository.findById(produit.getId());
        assertFalse(optionalProduit.isPresent());
    }


}

