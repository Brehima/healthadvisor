/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.Rendez_Vous;
import java.util.List;

/**
 *
 * @author khattout
 */
public interface IGestionRendezVous {
    
    public void AjouterRendezVous(Rendez_Vous rendezvous);
    public List<Rendez_Vous> ListRendez_Vous();
    public void supprimerRendezVous(int id_rendezvous);
    
    
}