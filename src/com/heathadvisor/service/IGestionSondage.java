/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Reponse;
import com.healthadvisor.entities.Sondage;
import java.util.ArrayList;

/**
 *
 * @author Tarek
 */
public interface IGestionSondage {
    public void ajouterSondage(Sondage s);
    public void supprimerSondage(Sondage s);
    public void updateSondage (int id, String nom);
    public int afficherIdSondage (String nom); 
    public ArrayList <Sondage> ListSondage();
}
