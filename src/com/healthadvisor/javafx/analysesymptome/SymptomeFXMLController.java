/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.analysesymptome;


import com.healthadvisor.entities.AccessToken;
import com.healthadvisor.entities.BodyParts;
import com.healthadvisor.entities.Maladie;
import com.healthadvisor.entities.SubBodyParts;
import com.healthadvisor.entities.Symptome;
import com.healthadvisor.service.impl.GestionBodyParts;
import com.healthadvisor.service.impl.GestionMaladie;
import com.healthadvisor.service.impl.GestionSubBodyPartSymptome;
import com.healthadvisor.service.impl.GestionSubBodyParts;
import com.jfoenix.controls.JFXSpinner;
import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firassov
 */
public class SymptomeFXMLController implements Initializable {

    @FXML
    private TextField anne;

    @FXML
    private ComboBox<String> sexe;

    @FXML
    private ComboBox<BodyParts> bodypart;
    
    @FXML
    private ComboBox<SubBodyParts> subbodypart;
    
    @FXML
    private ListView<Symptome> listsymptomes;
    
    @FXML
    private Button btnValider;
    
    @FXML
    private Label lb1;

    @FXML
    private Label lb2;

    @FXML
    private Label lb3;

    @FXML
    private Label lb4;

    @FXML
    private Label lb5;
    @FXML
    private Label lb6;
    
    @FXML
    private Button btnAjouter;
     
    @FXML
    private FlowPane PaneBox;
    public static ArrayList<Maladie> ResultatAnalyse;
      
      
    @FXML
    void bodypartselected(ActionEvent event) {
        int idbodypart = bodypart.getSelectionModel().getSelectedItem().getId();
System.out.println(idbodypart);
if(subbodypart.disableProperty().getValue()==true){
    subbodypart.setDisable(false);
}
        GestionSubBodyParts gsbp=new GestionSubBodyParts();
        ArrayList<SubBodyParts>lsbp=gsbp.AfficherSubBodyParts(idbodypart);
        ObservableList<SubBodyParts> olsbp = FXCollections.observableArrayList();
        for(SubBodyParts x : lsbp){
            SubBodyParts e=new SubBodyParts(x.getId(), x.getNom());
            olsbp.add(e);
        }
        subbodypart.setItems(olsbp);
    }
    
    @FXML
    void subbodypartselected(ActionEvent event) {
        String gender="";
        if(sexe.getValue().equals("Homme"))
            gender="man";
        else if(sexe.getValue().equals("Femme"))
            gender="woman";
        else if(sexe.getValue().equals("Garçon"))
            gender="boy";
        else
            gender="girl";
        if(subbodypart.getSelectionModel().getSelectedItem()!=null){
        int output = subbodypart.getSelectionModel().getSelectedItem().getId();
        System.out.println(output);
        if(listsymptomes.isDisabled()){
            listsymptomes.setDisable(false);
        }
                GestionSubBodyPartSymptome gsbps=new GestionSubBodyPartSymptome();
                ArrayList<Symptome>ls=gsbps.AfficherSubBodySymptome(output,gender);
                ObservableList<Symptome> ols = FXCollections.observableArrayList();
                for(Symptome x : ls){
                    Symptome e=new Symptome(x.getId(), x.getNom());
                    ols.add(e);
                }
                listsymptomes.setItems(ols);
                 }
        else{
            listsymptomes.getItems().clear();
        }
    }
    @FXML
    void btnSuivantAction(ActionEvent event) {
        if(!anne.getText().equals("") && sexe.getValue()!=null){
        if(anne.disableProperty().getValue()==true && sexe.disableProperty().getValue()==true){
        anne.setDisable(false);
        sexe.setDisable(false);
        lb1.setOpacity(0);
        lb2.setOpacity(0);
        lb3.setOpacity(0);
        lb4.setOpacity(0);
        lb5.setOpacity(0);
        lb6.setOpacity(0);
        bodypart.setOpacity(0);
        subbodypart.setOpacity(0);
        listsymptomes.setOpacity(0);
        btnAjouter.setOpacity(0);
        PaneBox.setOpacity(0);
        btnValider.setText("Suivant");
        }
        else{
        anne.setDisable(true);
        sexe.setDisable(true); 
        lb1.setOpacity(1.0);
        lb2.setOpacity(1.0);
        lb3.setOpacity(1.0);
        lb4.setOpacity(1.0);
        lb5.setOpacity(1.0);
        lb6.setOpacity(1.0);
        bodypart.setOpacity(1.0);
        subbodypart.setOpacity(1.0);
        listsymptomes.setOpacity(1.0);
        btnAjouter.setOpacity(1.0);
        PaneBox.setOpacity(1.0);
        btnValider.setText("Précédent");
        }
        }
        else{
            System.out.println("Erreur");
            Alert a =new Alert(Alert.AlertType.ERROR, "Remlir les champs Année de naissance et Sexe", ButtonType.OK);
            a.show();
        }
        
    }
    @FXML
    void btnAjouterAction(ActionEvent event) {
        ObservableList ol=listsymptomes.getSelectionModel().getSelectedItems();
        for(Object x : ol){
            Tag t=new Tag((Symptome)x);
            t.setUserData(x);
            /*Button b =new Button();
            b.setText(x.toString()+" X");*/
            t.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    PaneBox.getChildren().remove(t);
                }
                
            });
            PaneBox.getChildren().add(t);
            
        }
    }
      @FXML
    void listviewelementselected(MouseEvent event) {
        btnAjouter.setDisable(false);
    }
    @FXML
    void btnEnvoyerAction(ActionEvent event) throws IOException{
        String Symptomes="";
        ObservableList<Node>ols=PaneBox.getChildren();
        for(Node x : ols){
            Symptome s=(Symptome)x.getUserData();
           Symptomes=Symptomes+"\""+s.getId()+"\",";
        }
         StringBuilder sb = new StringBuilder(Symptomes);
         sb.delete(Symptomes.length()-1,Symptomes.length());
         Symptomes=sb.toString();
        System.out.println(Symptomes);
        GestionMaladie gm =new GestionMaladie();
        AccessToken token=new AccessToken();
        ArrayList<Maladie> l=gm.Diagnostique(token.getToken(), Integer.parseInt(anne.getText()), sexe.getValue(), Symptomes);
        ResultatAnalyse=l;
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ResultatAnalyseFXML.fxml"));
        Parent root=loader.load();
        Scene s = btnValider.getScene();
        s.setRoot(root);
       
       /* String Result="";
        for(Maladie x : l){
            Result=Result+"Maladie : "+x.getNom()+" Precision : "+x.getPrecision()+"%\n";
            Result=Result+"Spéciaite de la maladie : \n";
            for(String y : x.getListSpécialite()){
                Result=Result+y+"\n";
            }
        }
        Alert a =new Alert(Alert.AlertType.INFORMATION, Result, ButtonType.OK);
        a.show();*/
    }
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        ResultatAnalyse=new ArrayList<>();
        /*ArrayList<String> list=new ArrayList<>();
        list.add("Hello");
        list.add("hfdcjghbcns");
        list.add("lsnvjshnvljsdkv");
        list.add("lkhndvlhnbsdkjvbsjk");
        Maladie m =new Maladie("Maladie jsihdlihlivh lqjfjliqjfqij ljhsdifjoqjsohqolvchoi olqholfholqhhohod",75,list);
        ResultatAnalyse.add(m);
        ResultatAnalyse.add(m);
        ResultatAnalyse.add(m);
        ResultatAnalyse.add(m);
        ResultatAnalyse.add(m);*/
        listsymptomes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        String[] sexelist={"Homme","Femme","Garçon","Fille"};
        ObservableList<String> ObservListsexe = FXCollections.observableArrayList(sexelist);
        sexe.setItems(ObservListsexe);
        ObservableList<BodyParts> ObservList = FXCollections.observableArrayList();
        GestionBodyParts gbp =new GestionBodyParts();
        ArrayList<BodyParts> l=gbp.AfficherBodyParts();
        for(BodyParts x : l){
            BodyParts e=new BodyParts(x.getId(), x.getNom());
            ObservList.add(e);
        }
        bodypart.setItems(ObservList);
        
        
    }  
    
}
