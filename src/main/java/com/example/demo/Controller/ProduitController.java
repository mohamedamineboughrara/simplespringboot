package com.example.demo.Controller;


import com.example.demo.entity.Produit;
import com.example.demo.services.IProduitService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.*;

@CrossOrigin(origins = "http://localhost:4200/")

@RestController
@RequestMapping("/produit")
public class ProduitController {


    @Autowired
    IProduitService produitService;

    @PostMapping("/add-produit/{categorieId}")
    public Produit addProduit(@PathVariable Long categorieId, @RequestBody Produit produit) {

        return produitService.addProduit(categorieId, produit);
    }

    @PostMapping("/add-produit")
    @ResponseBody
    // http://localhost:8081/SpringApi/produit/add-produit
    public Produit ajouterProduit(@RequestBody Produit dp){

        return produitService.ajouterProduit(dp);
    }

    @PostMapping("/update-produit")
    @ResponseBody
    public Produit updateProduit(@RequestBody Produit produit) {
        Long id = produit.getId();
        String nom = produit.getNom();
        int quantite = produit.getQuantite();
        Long categorie_id = produit.getCategorie() != null ? produit.getCategorie().getId() : null;
        produit.setDateCreation(new Date());
        Date dateModification = produit.getDateModification();
        if(dateModification == null) {
            dateModification= new Date();
        }
        produit.setDateModification(dateModification);
        return produitService.modifierProduit(id, nom, quantite, categorie_id, dateModification);
    }

    @DeleteMapping("/delete-produit/{id}")
    @ResponseBody
    public HttpStatus deleteProduit(@PathVariable Long id){
        produitService.supprimerProduit(id);
        return HttpStatus.NO_CONTENT;
    }
    @GetMapping("/produit/{id}")
    public Optional<Produit> afficherProduit(@PathVariable long id) {
        return produitService.findByID(id);
    }
    @GetMapping("/all-produit")
    @ResponseBody

    public List<Produit> produits(){
        return produitService.retrieveAllProducts();
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<List<Produit>> getProductsByCategory(@PathVariable Long id) {
        List<Produit> produits = produitService.afficherProduitByCategorie(id);
        if (produits.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }







}
