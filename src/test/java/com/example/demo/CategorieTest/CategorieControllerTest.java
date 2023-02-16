package com.example.demo.CategorieTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import com.example.demo.entity.Categorie;
import com.example.demo.repository.CategorieRepository;

import com.example.demo.services.IcategorieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorieControllerTest {


    @Autowired
    private IcategorieService categorieService;

    @Autowired
    private CategorieRepository categorieRepository;

    @Test
    public void testAddCategorie() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse("2020-02-02");


        Categorie categorie = new Categorie();
        categorie.setNom("Test Category Final");
        categorie.setDateCreation(parsedDate);
        Categorie savedCategorie = categorieService.ajouterCategorie(categorie);
        assertThat(savedCategorie).isNotNull();
        assertThat(savedCategorie.getNom()).isEqualTo("Test Category Final");
    }

    @Test
    public void testUpdateCategorie() {
        Categorie categorie = new Categorie();
        categorie.setNom("Test Category");
        Categorie savedCategorie = categorieService.ajouterCategorie(categorie);
        assertThat(savedCategorie).isNotNull();
        assertThat(savedCategorie.getNom()).isEqualTo("Test Category");

        Categorie updatedCategorie = new Categorie();
        updatedCategorie.setId(savedCategorie.getId());
        updatedCategorie.setNom("Test Category Updated");
        updatedCategorie.setQuantite(savedCategorie.getQuantite());
        updatedCategorie.setDateCreation(savedCategorie.getDateCreation());
        updatedCategorie.setDateModification(savedCategorie.getDateModification());

        Categorie modifiedCategorie = categorieService.modifierCategorie(updatedCategorie);
        assertThat(modifiedCategorie).isNotNull();
        assertThat(modifiedCategorie.getNom()).isEqualTo("Test Category Updated");
    }


    @Test
    public void testDeleteCategorie() throws ParseException {
        Categorie categorie = new Categorie();
        categorie.setNom("Test Category3");
        Categorie savedCategorie = categorieService.ajouterCategorie(categorie);
        assertThat(savedCategorie).isNotNull();
        assertThat(savedCategorie.getNom()).isEqualTo("Test Category3");

       boolean isDeleted = categorieService.supprimerCategorie(savedCategorie.getId());
        assertTrue(isDeleted);
        Optional<Categorie> optionalCategorie = categorieService.afficherCategorie(savedCategorie.getId());
        assertFalse(optionalCategorie.isPresent());
    }


}

