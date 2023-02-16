package com.example.demo.Controller;

import com.example.demo.entity.Categorie;
import com.example.demo.services.IcategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    IcategorieService icategorieService;
    @PostMapping("/add-categorie")
    @ResponseBody
    public Categorie ajouterCategorie(@RequestBody Categorie ca){
        return icategorieService.ajouterCategorie(ca);
    }
    @PutMapping("/update-categorie")
    @ResponseBody
    public Categorie modifierCategorie(@RequestBody Categorie categorie) {
        return icategorieService.modifierCategorie(categorie);
    }


    @GetMapping("/all-categorie")
    @ResponseBody

    public List<Categorie> categories(){
        return icategorieService.retrieveAllCategories();
    }
    @GetMapping("/categorie/{id}")
    @ResponseBody
    public Categorie getCategorieById(@PathVariable("id") Long id) {
        return icategorieService.getCategorieById(Math.toIntExact(id));
    }
    @DeleteMapping("/delete-categorie/{id}")
    @ResponseBody
    public HttpStatus deleteCategorie(@PathVariable Long id){
        icategorieService.supprimerCategorie(id);
        return HttpStatus.NO_CONTENT;
    }
}
