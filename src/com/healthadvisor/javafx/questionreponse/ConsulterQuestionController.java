/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.questionreponse;

import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Reponse;
import com.healthadvisor.service.impl.GestionQuestion;
import com.healthadvisor.service.impl.GestionReponse;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class ConsulterQuestionController implements Initializable {

    @FXML
    private Label questionID;
    @FXML
    private Label labelID;
    @FXML
    private TableView<Reponse> tableID;
    @FXML
    private TableColumn<Reponse, String> medecinID;
    @FXML
    private TableColumn<Reponse, String> reponseID;
    @FXML
    private Button modifierID;
    @FXML
    private Button supprimerID;
    @FXML
    private AnchorPane paneID;
    @FXML
    private TextArea textAreaQuestion;
    @FXML
    private Button retourID;
    @FXML
    private Button btnRepondre;
    @FXML
    private TextField searchBarID;
    @FXML
    private Button btnSupprimerReponse;
    @FXML
    private TableColumn<Reponse, Timestamp> dateID;
    @FXML
    private Button modifierReponseBtn;
    
    public static Reponse reponse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        QuestionController qc = new QuestionController();
        questionID.setText(qc.question.toString());
        
        
        GestionReponse gr = new GestionReponse();
        ObservableList<Reponse> listr = FXCollections.observableArrayList();
            for (Reponse r : gr.ListReponse(qc.question.getId())){
                listr.add(r);
                medecinID.setCellValueFactory(new PropertyValueFactory<Reponse,String>("id_medecin"));
                reponseID.setCellValueFactory(new PropertyValueFactory<Reponse,String>("reponse"));
                dateID.setCellValueFactory(new PropertyValueFactory<Reponse,Timestamp>("date_publication"));
                
                //consulterID.setCellValueFactory(new PropertyValueFactory<Question,Button>("btnConsulter"));
            }
        tableID.setItems(listr);
        
        //SearhBar filters
        FilteredList<Reponse> filteredData = new FilteredList<>(listr, e -> true);
        searchBarID.setOnKeyReleased(e->{
            searchBarID.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Reponse>) r -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (r.getReponse().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(r.getId_medecin().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } 
                    return false;
                });
            });
            SortedList<Reponse> sortedQuestion = new SortedList<>(filteredData);
            sortedQuestion.comparatorProperty().bind(tableID.comparatorProperty());
            tableID.setItems(sortedQuestion);
        });
         
    }    

    @FXML
    private void btnModifier(ActionEvent event) {
        if (modifierID.getText().equals("Valider"))
        {
            GestionQuestion gq = new GestionQuestion();
            gq.updateQuestion(QuestionController.question.getId(),textAreaQuestion.getText());
            textAreaQuestion.setOpacity(0);
            questionID.setText(textAreaQuestion.getText());
        }else{
        modifierID.setText("Valider");
        textAreaQuestion.setOpacity(1);
        textAreaQuestion.setText(questionID.getText());
        }
        
        
    }

    @FXML
    private void btnSupprimer(ActionEvent event) throws IOException {
        
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Dialogue de confirmation");
        alerte.setHeaderText("Attetion ! ");
        alerte.setContentText("Voulez vous vraiment supprimer cette question ?");
        
        Optional<ButtonType> result = alerte.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            GestionQuestion gq = new GestionQuestion();
            gq.supprimerQuestion(QuestionController.question.getId());           
        }else{
            
        }
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("question.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void retourAction(ActionEvent event) throws IOException {
        questionMain qm = new questionMain();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("question.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void btnRepondre(ActionEvent event) throws IOException {
        questionMain qm = new questionMain();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("RepondreQuestion.fxml"));
        Parent root=loader.load();
        Scene s = tableID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void btnSupprimerReponse(ActionEvent event) throws IOException {
        Alert alerte = new Alert(Alert.AlertType.CONFIRMATION);
        alerte.setTitle("Dialogue de confirmation");
        alerte.setHeaderText("Attetion ! ");
        alerte.setContentText("Voulez vous vraiment supprimer cette réponse ?");
        
        Optional<ButtonType> result = alerte.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            GestionReponse gr = new GestionReponse();
            gr.supprimerReponse(tableID.getSelectionModel().getSelectedItem().getId());           
        }else{
            
        }
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterQuestion.fxml"));
        Parent root=loader.load();
        Scene s = paneID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void modifierReponseBtn(ActionEvent event) throws IOException {
        questionMain qm = new questionMain();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModifierReponse.fxml"));
        Parent root=loader.load();
        Scene s = tableID.getScene();
        s.setRoot(root);
    }

    @FXML
    private void tableViewEvent(MouseEvent event) {
        reponse = tableID.getSelectionModel().getSelectedItem();
    }
    
}