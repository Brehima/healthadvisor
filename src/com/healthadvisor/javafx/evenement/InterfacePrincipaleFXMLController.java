package com.healthadvisor.javafx.evenement;

import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class InterfacePrincipaleFXMLController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Hyperlink list;
    @FXML
    private FontAwesomeIconView gestion;
    @FXML
    private Hyperlink myEvt;
    @FXML
    private Rectangle rect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoginController.admin = true;
        if(!FXMLLoginController.admin){
            rect.setOpacity(0);
            gestion.setOpacity(0);
        }else{
            rect.setOpacity(1);
            gestion.setOpacity(1);
        }
    }

    @FXML
    private void redirectList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NaviguerEvenementsFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            NaviguerEvenementsFXMLController nav = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectMy(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesEvenementsFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            MesEvenementsFXMLController list = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MesEvenementsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectGestion(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeEvenementFXML.fxml"));
        try {
            Parent root;
            root = loader.load();
            ListeEvenementFXMLController list = loader.getController();
            Scene scene = anchor.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(InterfacePrincipaleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
