/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.ReponsesPossibles;
import com.healthadvisor.entities.UserReponse;

/**
 *
 * @author Tarek
 */
public interface IGestionUserReponse {
    
    public void ajouterUserReponse(UserReponse ur);
    public void afficherUserReponse(); 
    
}