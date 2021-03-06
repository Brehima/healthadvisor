/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Reponse;
import com.heathadvisor.service.IGestionQuestion;
import com.heathadvisor.service.IGestionReponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tarek
 */
public class GestionReponse implements IGestionReponse{
    
    
    MyDB myDB;
    
    public GestionReponse() {
    myDB=  MyDB.getInstance();
    }
    
    
    @Override
    public void ajouterReponse(Reponse r) {
        try {
            String query = "insert into reponse (REPONSE,ID_MEDECIN,ID_QUESTION,date_publication,modification_etat) values (?,?,?,?,false)";
            PreparedStatement prep= myDB.getConnexion().prepareStatement(query);
            
            
            prep.setString(1 , r.getReponse());
            prep.setString(2, r.getId_medecin());
            prep.setInt(3, r.getQuestion().getId());
            //prep.setDate(4, );
            prep.setTimestamp(4, r.getDate_publication());
            prep.executeUpdate();
       
            System.out.println("Réponse ajoutée.");
        } catch (SQLException ex) {
            System.out.println(ex);
         }
    }

    @Override
    public void supprimerReponse(Reponse r) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("delete from reponse where ID_REPONSE=?");
            prep.setInt(1 , r.getId());
            prep.executeUpdate();
            System.out.println("Réponse supprimée.");
        } catch (SQLException ex) {
            System.out.println("Réponse non supprimée !");
        }
    }
    
    @Override
    public void supprimerReponse(int id_reponse) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("delete from reponse where ID_REPONSE=?");
            prep.setInt(1 , id_reponse);
            prep.executeUpdate();
            System.out.println("Réponse supprimée.");
        } catch (SQLException ex) {
            System.out.println("Réponse non supprimée !");
        }
    }
    
    @Override
    public void updateReponse(int id, String reponse) {
        try {
            PreparedStatement prep = myDB.getConnexion().prepareStatement("update reponse set REPONSE=?, date_publication=?, modification_etat=? where ID_REPONSE="+id);
            prep.setString(1 , reponse);
            prep.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
            prep.setBoolean(3, true);
            prep.executeUpdate();
            System.out.println("Réponse mise à jour.");
        } catch (SQLException ex) {
            System.out.println("Erreur de mise à jour !");
        }
    }

    @Override
    public void afficherReponse() {
        List<Reponse> reponses = new ArrayList<>();
          
        try {
            Statement stm = myDB.getConnexion().createStatement();
            ResultSet r= stm.executeQuery("select * from reponse");  
            while(r.next()){
                System.out.println("ID_REPONSE = "+r.getInt(1)+" | REPONSE = "+r.getString(2)+" | ID_MEDECIN = "+r.getString(3)+" | ID_QUESTION = "+r.getInt(4));     
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(GestionQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Reponse> ListReponse() {
        ArrayList<Reponse> listr= new ArrayList<>();
        try {
            Statement stm =myDB.getConnexion().createStatement();
            String sql="select * from reponse" ;
            ResultSet r = stm.executeQuery(sql);

            while(r.next()){
                System.out.println("recuperation ...");
                Reponse reponse = new Reponse();
                
                int idReponse = r.getInt("ID_REPONSE");
                String textReponse =r.getString("REPONSE");
                String loginMedecin = r.getString("ID_MEDECIN");
                Timestamp date = r.getTimestamp("date_publication");
                reponse.setId(idReponse);
                reponse.setReponse(textReponse);
                GestionMedecin gm=new GestionMedecin();
                reponse.setId_medecin(loginMedecin);
                reponse.setId_medecin(loginMedecin);
                GestionQuestion gq = new GestionQuestion();
                Question question = gq.afficherQuestion(r.getInt("ID_QUESTION"));
                reponse.setQuestion(question);
                reponse.setDate_publication(date);
                listr.add(reponse);
            }
            
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listr;
    }

    @Override
    public List<Reponse> ListReponse(int id_question) {
        ArrayList<Reponse> listr= new ArrayList<>();
        try {
            Statement stm =myDB.getConnexion().createStatement();
            String sql="select * from reponse where ID_QUESTION="+id_question ;
            ResultSet r = stm.executeQuery(sql);

            while(r.next()){
                System.out.println("recuperation ...");
                Reponse reponse = new Reponse();
                
                int idReponse = r.getInt("ID_REPONSE");
                String textReponse =r.getString("REPONSE");
                String loginMedecin = r.getString("ID_MEDECIN");
                Timestamp date = r.getTimestamp("date_publication");
                reponse.setId(idReponse);
                reponse.setReponse(textReponse);
                GestionMedecin gm=new GestionMedecin();
                reponse.setId_medecin(loginMedecin);
                reponse.setDate_publication(date);
                GestionQuestion gq = new GestionQuestion();
                Question question = gq.afficherQuestion(r.getInt("ID_QUESTION"));
                reponse.setQuestion(question);
                
                listr.add(reponse);
            }
            
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(IGestionReponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listr;
    }

    
    
}
