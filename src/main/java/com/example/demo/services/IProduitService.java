package com.example.demo.services;

import com.example.demo.entity.Produit;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IProduitService  {
    Produit addProduit(Long categorieId, Produit produit);


    Produit ajouterProduit(Produit produit);



    Produit modifierProduit(Long id, String nom, int quantite, Long categorie_id , Date date_modification);
    boolean supprimerProduit(Long id);
    List<Produit> retrieveAllProducts();


    Optional<Produit> findByID(Long id);

    List<Produit> afficherProduitByCategorie(Long idCategorie);


}
