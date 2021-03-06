/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.sondage;

import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Question;
import com.healthadvisor.entities.Sondage;
import com.healthadvisor.javafx.questionreponse.questionMain;
import com.healthadvisor.service.impl.GestionQuestion;
import com.healthadvisor.service.impl.GestionSondage;
import health_advisor.FXMLHomeViewController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Tarek
 */


public class SondageController implements Initializable {

    @FXML
    private TableView<Sondage> tableID;
    @FXML
    private TableColumn<Sondage, String> columnID;
    
    public static Sondage sondage;
    //public static Patient patient = new Patient("tarek", "pass", "1","");
    @FXML
    private Button repondreBtnID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        GestionSondage gs = new GestionSondage();
        ObservableList<Sondage> lists = FXCollections.observableArrayList(gs.ListSondage());
           /* for (Sondage s : gs.ListSondage() ){
                lists.add(s);
            }*/
        columnID.setCellValueFactory(new PropertyValueFactory<Sondage,String>("nom"));
        tableID.setItems(lists);
    }    
    
    @FXML
    private void repondreBtnAction(ActionEvent event) throws IOException {
        //SondageMain sm = new SondageMain();
        
        AnchorPane a = FXMLLoader.load(getClass().getResource("ConsulterSondage.fxml"));
        FXMLHomeViewController.setNode(FXMLHomeViewController.holderPane, a);
        
        
    }

    @FXML
    private void tableViewEvent(MouseEvent event) {
        sondage = tableID.getSelectionModel().getSelectedItem();
    }

    
}
