/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Medecin;
import java.util.List;

/**
 *
 * @author khattout
 */
public interface IGestionMedecin {
    public void AjouterMedecin(Medecin medecin);
    public void ModifierMedecin(Medecin medecin);
    public void SupprimerMedecinCin(String cin);
    public List<Medecin> ListMedecin();
    public Medecin AfficherMedecinCin(String cin);
    public List<Medecin> AfficherMedecinSpecialite(String specialite);
    public List<Medecin> AfficherMedecinAdresse(String adresse);

}
