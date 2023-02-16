package com.example.demo.repository;

import com.example.demo.entity.Categorie;
import com.example.demo.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long>{

    List<Produit> findByCategorie(Categorie categorie);
}
