package com.example.demo.services;

import com.example.demo.entity.Categorie;

import java.util.List;
import java.util.Optional;

public interface IcategorieService {
    Categorie ajouterCategorie(Categorie categorie);
    Optional<Categorie> afficherCategorie(long id);


    

    boolean supprimerCategorie(Long id);
    Optional<Categorie> findByID(Long id);



    Categorie modifierCategorie(Categorie categories );

    List<Categorie> retrieveAllCategories();

    Categorie getCategorieById(int id);


}
