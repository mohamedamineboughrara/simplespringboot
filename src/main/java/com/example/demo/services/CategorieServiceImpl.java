package com.example.demo.services;

import com.example.demo.entity.Categorie;

import com.example.demo.entity.Produit;
import com.example.demo.repository.CategorieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;



@Service
public class CategorieServiceImpl implements IcategorieService{
    @Autowired
    CategorieRepository categorieRepository;
    @Override
    public Categorie ajouterCategorie(Categorie categorie) {


        return categorieRepository.save(categorie);

    }




    @Override
    public boolean supprimerCategorie(Long id) {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);
        if (!existingCategorie.isPresent()) {
            return false;
        }
        List<Produit> produits = existingCategorie.get().getProduits();
        if (!produits.isEmpty()) {
            for (Produit produit : produits) {
                produit.setCategorie(null);
            }
        }
        categorieRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Categorie> findByID(Long id) {
        return categorieRepository.findById(id);
    }
    @Override
    public Categorie modifierCategorie(Categorie newCategorie  ){
        Categorie existingCategorie = categorieRepository.findById(newCategorie.getId()).orElse(null);
        if (existingCategorie != null) {
            existingCategorie.setNom(newCategorie.getNom());
            existingCategorie.getQuantite();
            existingCategorie.setQuantite(newCategorie.getQuantite());
           existingCategorie.setDateModification(new Date());
            return categorieRepository.saveAndFlush(existingCategorie);
        } else {
            return null;
        }
    }


    @Override
    public List<Categorie> retrieveAllCategories() {


        return categorieRepository.findAll();
    }



    @Override
    public Optional<Categorie> afficherCategorie(long id) {
        return categorieRepository.findById(id);
    }



    @Override
    public Categorie getCategorieById(int id) {
        return categorieRepository.findById(Long.valueOf(id)).orElse(null);
    }
}
