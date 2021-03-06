/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.service.impl;

import com.healthadvisor.database.MyDB;
import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.heathadvisor.service.IGestionMedecin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khattout
 */
public class GestionMedecin implements IGestionMedecin{
    MyDB database;

    public GestionMedecin() {
        database = MyDB.getInstance();
    }

    @Override
    public void AjouterMedecin(Medecin medecin) {
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Insert into medecin (login,specialite,adresse,diplome,rating,lat_p,long_p,statut_compte) "+ " values (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = database.getConnexion().prepareStatement(sql);
            preparedStmt.setString(1,medecin.getLogin());
            preparedStmt.setString(2,medecin.getSpecialite());
            preparedStmt.setString(3,medecin.getAdresse());
            preparedStmt.setString(4,medecin.getDiplome());
            preparedStmt.setInt(5,medecin.getRating());
            preparedStmt.setDouble(6,medecin.getLat_p());
            preparedStmt.setDouble(7,medecin.getLong_p());
            preparedStmt.setString(8,medecin.getStatut_compte());

              preparedStmt.executeUpdate();

            System.out.println("Insertion avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void ModifierMedecin(Medecin medecin) {
        System.out.println("Modification Medecin...");
        try{
           Statement stm =database.getConnexion().createStatement();
            System.out.println("Recuperation Latitude"+medecin.getLat_p()+"Longitude"+medecin.getLong_p());
           String sql="UPDATE medecin SET adresse='"+medecin.getAdresse()+"',lat_p='"+medecin.getLat_p()+"',long_p='"+medecin.getLong_p()+"' WHERE login='"+medecin.getLogin_med()+"'";
           stm.executeUpdate(sql);
           System.out.println("Medecin bien modifiÃ©");
        }catch(SQLException e){
           System.out.println(e.getMessage());
       }    
    }

    @Override
    public boolean SupprimerMedecinLogin(String login) {
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="Delete from medecin where login='"+login+"'" ;
            stm.executeUpdate(sql);
            System.out.println("suppression avec succes");
            return true;
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

    @Override
    public List<Medecin> ListMedecin() {
    ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN=patient.LOGIN AND patient.CIN_USER=utilisateur.CIN" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString("login"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getDouble("lat_p"),rs.getDouble("long_p"),rs.getString("statut_compte"),rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;    }

    @Override
    public Medecin AfficherMedecinLogin(String login) {
        Medecin med =null;
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN=patient.LOGIN AND patient.CIN_USER=utilisateur.CIN AND medecin.LOGIN='"+login+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                 med= new Medecin(rs.getString("login"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getDouble("lat_p"),rs.getDouble("long_p"),rs.getString("statut_compte"),rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return med;    
    }    

    @Override
    public List<Medecin> AfficherMedecinSpecialite(String specialite) {
    ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN=patient.LOGIN AND patient.CIN_USER=utilisateur.CIN AND medecin.SPECIALITE like '%"+specialite+"%'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString("login"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getDouble("lat_p"),rs.getDouble("long_p"),rs.getString("statut_compte"),rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;  }

    @Override
    public List<Medecin> AfficherMedecinAdresse(String adresse) {
    ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN=patient.LOGIN AND patient.CIN_USER=utilisateur.CIN AND medecin.ADRESSE='"+adresse+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString("login"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getDouble("lat_p"),rs.getDouble("long_p"),rs.getString("statut_compte"),rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;     }

    @Override
    public Medecin AficherMedecinNomPrenom(String nomprenom) {
  Medecin med =null;
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN=patient.LOGIN AND patient.CIN_USER=utilisateur.CIN AND concat(utilisateur.NOM,utilisateur.PRENOM)='"+nomprenom+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                 med= new Medecin(rs.getString("login"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getDouble("lat_p"),rs.getDouble("long_p"),rs.getString("statut_compte"),rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return med;     }

    @Override
    public List<Medecin> AfficherMedecinSnomprenom(String nomprenom) {
        ArrayList<Medecin> listmed= new ArrayList<>();
        try {
            Statement stm =database.getConnexion().createStatement();
            String sql="SELECT * FROM medecin,patient,utilisateur WHERE medecin.LOGIN=patient.LOGIN AND patient.CIN_USER=utilisateur.CIN AND concat(utilisateur.NOM,utilisateur.PRENOM)='"+nomprenom+"'" ;
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                Medecin med= new Medecin(rs.getString("login"),rs.getString("specialite"),rs.getString("adresse"),rs.getString("diplome"),rs.getInt("rating"),rs.getDouble("lat_p"),rs.getDouble("long_p"),rs.getString("statut_compte"),rs.getString("login"),rs.getString("password"),rs.getString("cin_user"),rs.getString("photo_profile"));
                listmed.add(med);
            }
            
            System.out.println("Recuperation avec succes");
           // stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listmed;     }

    @Override
    public boolean ModifierStatutMedecin(String cin, String statut) {     
        GestionPatient gp=new GestionPatient();
        Patient p=gp.AfficherPatientCin(cin);
        Medecin med =AfficherMedecinLogin(p.getLogin());
        try {
            System.out.println("Modification RDV...");
            Statement stm =database.getConnexion().createStatement();
            String sql="UPDATE medecin SET statut_compte='"+statut+"' WHERE login='"+med.getLogin()+"'";
            stm.executeUpdate(sql);
            System.out.println("Rendez_vous bien modifiÃ©");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GestionMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
 }


 
    
}
