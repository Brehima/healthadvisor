/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package health_advisor;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.enumeration.StatutMedecinEnum;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.javafx.routes.Routes;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLHomeViewController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Label txtCurrentWindow;
    public  static AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private  JFXButton Signin;
    @FXML
    private  JFXButton Signout;
    @FXML
    private JFXButton profile;
    @FXML
    public static AnchorPane mainPane;
    GestionPatient gp=new GestionPatient();
    GestionMedecin gm=new GestionMedecin();
        Image img=new Image("/com/healthadvisor/ressources/cancel.png");
        Notifications notif=Notifications.create()
               .graphic(new ImageView(img))
                    .title("Accés Interdit ! ")
                    .text("Attendez Jusque à la Validation De Votre Compte ")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle();
    @FXML
    private AnchorPane holderPane1;
    @FXML
    private JFXButton rendez_vousP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     holderPane=holderPane1;
          HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            if (drawer.isHidden()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });
if(FXMLLoginController.admin){
       
        Signin.setOpacity(0);
        Signout.setOpacity(1);
        Signout.toFront();
        try {
            System.out.println("Initialisation Administrateur ...");
            VBox sidePane = FXMLLoader.load(getClass().getResource("/health_advisor/FXMLDrawer.fxml"));
            AnchorPane  acceuil= FXMLLoader.load(getClass().getResource(Routes.HOME));
            StackPane GererUtil = FXMLLoader.load(getClass().getResource(Routes.GererUtilisateur));
            AnchorPane Geolocalisation = FXMLLoader.load(getClass().getResource(Routes.GEOLOCALISATION));
            AnchorPane Symptome = FXMLLoader.load(getClass().getResource(Routes.SYMPTOME));
            AnchorPane Boutique = FXMLLoader.load(getClass().getResource(Routes.BOUTIQUEADMIN));
            AnchorPane QuestionReponse = FXMLLoader.load(getClass().getResource(Routes.QUESTIONREPONSE));
            AnchorPane Sondage = FXMLLoader.load(getClass().getResource(Routes.SONDAGEADMIN));
            AnchorPane Article = FXMLLoader.load(getClass().getResource(Routes.ARTICLE));
            AnchorPane Evenement = FXMLLoader.load(getClass().getResource(Routes.EVENEMENT));
       
            setNode(holderPane,acceuil);
            drawer.setSidePane(sidePane);
            ScrollPane ss=new ScrollPane();
            ss.setContent(sidePane);
            drawer.getChildren().add(ss);
            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            case "acceuil":
                                drawer.close();
                                setNode(holderPane,acceuil);
                                break;
                         
                            case "symptome":
                                drawer.close();                                
                                setNode(holderPane,Symptome);
                                break;    
                            case "geolocalisation":
                                drawer.close();                                
                                setNode(holderPane,Geolocalisation);
                                break; 
                            case "questionreponse":
                                drawer.close();                                
                                setNode(holderPane,QuestionReponse);
                                break;
                                case "gererutilisateur":
                                drawer.close();                                
                                setNode(holderPane,GererUtil);
                                break;
                                case "boutique":
                                drawer.close();                                
                                setNode(holderPane,Boutique);
                                break;
                                case "sondage":
                                drawer.close();                                
                                setNode(holderPane,Sondage);
                                break;
                            case "evenement":
                                drawer.close();                                
                                setNode(holderPane,Evenement);
                                break;
                            case "article":
                                drawer.close();                                
                                setNode(holderPane,Article);
                                break; 
                                                          
                        }
                    });
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
}else{
if(FXMLLoginController.patient){
            profile.setOpacity(1);
rendez_vousP.setOpacity(1);
        Signin.setOpacity(0);
        Signout.setOpacity(1);
        Signout.toFront();
     try {
            System.out.println("Initialisation Patient ...");
            VBox sidePane = FXMLLoader.load(getClass().getResource("/health_advisor/PatientDrawer.fxml"));
            AnchorPane  acceuil= FXMLLoader.load(getClass().getResource(Routes.HOME));
            AnchorPane recherche = FXMLLoader.load(getClass().getResource(Routes.RechercheMedecin));
            AnchorPane Geolocalisation = FXMLLoader.load(getClass().getResource(Routes.GEOLOCALISATION));
            AnchorPane Symptome = FXMLLoader.load(getClass().getResource(Routes.SYMPTOME));
            AnchorPane Boutique = FXMLLoader.load(getClass().getResource(Routes.BOUTIQUE));
            ScrollPane QuestionReponse = FXMLLoader.load(getClass().getResource(Routes.QUESTIONREPONSEPATIENT));
            ScrollPane Sondage = FXMLLoader.load(getClass().getResource(Routes.SONDAGEPATIENT));
            AnchorPane BienEtre = FXMLLoader.load(getClass().getResource(Routes.BIENETRE));
            AnchorPane Article = FXMLLoader.load(getClass().getResource(Routes.ARTICLE));
            AnchorPane Evenement = FXMLLoader.load(getClass().getResource(Routes.EVENEMENT));
       
            setNode(holderPane,recherche);
            drawer.setSidePane(sidePane);
            ScrollPane ss=new ScrollPane();
            ss.setContent(sidePane);
            drawer.getChildren().add(ss);
            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            case "acceuil":
                                drawer.close();
                                setNode(holderPane,acceuil);
                                break;
                            case "recherchemed":
                                drawer.close();                               
                                setNode(holderPane,recherche);
                                break;
                         
                            case "symptome":
                                drawer.close();                                
                                setNode(holderPane,Symptome);
                                break;    
                            case "geolocalisation":
                                drawer.close();                                
                                setNode(holderPane,Geolocalisation);
                                break;
                            case "boutique":
                                drawer.close();                                
                                setNode(holderPane,Boutique);
                                break; 
                            case "questionreponse":
                                drawer.close();                                
                                setNode(holderPane,QuestionReponse);
                                break;
                            case "sondage":
                                drawer.close();                                
                                setNode(holderPane,Sondage);
                                break;  
                            case "bienetre":
                                drawer.close();                                
                                setNode(holderPane,BienEtre);
                                break;
                            case "evenement":
                                drawer.close();                                
                                setNode(holderPane,Evenement);
                                break;
                            case "article":
                                drawer.close();                                
                                setNode(holderPane,Article);
                                break; 
                            
                                                            
                        }
                    });
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
}else {
    if(FXMLLoginController.docteur){
        Signin.setOpacity(0);
        Signout.setOpacity(1);
        Signout.toFront();
        profile.setOpacity(1);
     try {
            System.out.println("Initialisation Medecin ...");
            VBox sidePane = FXMLLoader.load(getClass().getResource("/health_advisor/MedecinDrawer.fxml"));
            AnchorPane  acceuil= FXMLLoader.load(getClass().getResource(Routes.HOME));
            AnchorPane recherche = FXMLLoader.load(getClass().getResource(Routes.RechercheMedecin));
            AnchorPane Geolocalisation = FXMLLoader.load(getClass().getResource(Routes.GEOLOCALISATION));
            AnchorPane Symptome = FXMLLoader.load(getClass().getResource(Routes.SYMPTOME));
            AnchorPane Boutique = FXMLLoader.load(getClass().getResource(Routes.BOUTIQUE));
            ScrollPane QuestionReponse = FXMLLoader.load(getClass().getResource(Routes.QUESTIONREPONSEPATIENT));
            ScrollPane Sondage = FXMLLoader.load(getClass().getResource(Routes.SONDAGEPATIENT));
            AnchorPane BienEtre = FXMLLoader.load(getClass().getResource(Routes.BIENETRE));
            AnchorPane Article = FXMLLoader.load(getClass().getResource(Routes.ARTICLE));
            AnchorPane Evenement = FXMLLoader.load(getClass().getResource(Routes.EVENEMENT));
            AnchorPane SuivieRDV = FXMLLoader.load(getClass().getResource(Routes.SuivieRDV_M));

       
            setNode(holderPane,recherche);
            drawer.setSidePane(sidePane);
            ScrollPane ss=new ScrollPane();
            ss.setContent(sidePane);
            drawer.getChildren().add(ss);
            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            case "acceuil":
                                drawer.close();
                                setNode(holderPane,acceuil);
                                break;
                            case "recherchemed":
                                drawer.close();                               
                                setNode(holderPane,recherche);
                                break;
                         
                            case "symptome":
                                drawer.close();                                
                                setNode(holderPane,Symptome);
                                break;    
                            case "geolocalisation":
                                drawer.close();                                
                                setNode(holderPane,Geolocalisation);
                                break;
                            case "boutique":
                                drawer.close();                                
                                setNode(holderPane,Boutique);
                                break; 
                            case "questionreponse":
                                drawer.close();                                
                                setNode(holderPane,QuestionReponse);
                                break;
                            case "sondage":
                                drawer.close();                                
                                setNode(holderPane,Sondage);
                                break;  
                            case "bienetre":
                                drawer.close();                                
                                setNode(holderPane,BienEtre);
                                break;
                            case "evenement":
                                drawer.close();                                
                                setNode(holderPane,Evenement);
                                break;
                            case "article":
                                drawer.close();                                
                                setNode(holderPane,Article);
                                break;
                            case "suivierdv":
                                Patient p=gp.AfficherPatientCin(FXMLLoginController.Identifiant);
                                Medecin m=gm.AfficherMedecinLogin(p.getLogin());
                                if(m.getStatut_compte().equalsIgnoreCase(StatutMedecinEnum.VALIDE.name())){
                                drawer.close();                                
                                setNode(holderPane,SuivieRDV);
                                }else{
                                    notif.show();
                                }
                                break; 
                                                                           
                        }
                    });
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    else {
        Signin.setOpacity(1);
        Signout.setOpacity(0);
        Signin.toFront();
        try {
            System.out.println("Initialisation Utilisateur ...");
            VBox sidePane = FXMLLoader.load(getClass().getResource("/health_advisor/UtilisateurDrawer.fxml"));
            AnchorPane acceuil= FXMLLoader.load(getClass().getResource(Routes.HOME));
            AnchorPane recherche = FXMLLoader.load(getClass().getResource(Routes.RechercheMedecin));
            AnchorPane Geolocalisation = FXMLLoader.load(getClass().getResource(Routes.GEOLOCALISATION));
            AnchorPane Symptome = FXMLLoader.load(getClass().getResource(Routes.SYMPTOME));
            AnchorPane QuestionReponse = FXMLLoader.load(getClass().getResource(Routes.QUESTIONREPONSE));
           AnchorPane Article = FXMLLoader.load(getClass().getResource(Routes.ARTICLE));
            AnchorPane Evenement = FXMLLoader.load(getClass().getResource(Routes.EVENEMENT));
       
            setNode(holderPane,acceuil);
            drawer.setSidePane(sidePane);
            
            ScrollPane ss=new ScrollPane();
            ss.setContent(sidePane);
            drawer.getChildren().add(ss);
            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            case "acceuil":
                                drawer.close();
                                setNode(holderPane,acceuil);
                                break;
                            case "recherchemed":
                                drawer.close();                               
                                setNode(holderPane,recherche);
                                break;
                          
                            case "symptome":
                                drawer.close();                                
                                setNode(holderPane,Symptome);
                                break;    
                            case "geolocalisation":
                                drawer.close();                                
                                setNode(holderPane,Geolocalisation);
                                break; 
                            case "questionreponse":
                                drawer.close();                                
                                setNode(holderPane,QuestionReponse);
                                break;
                            case "evenement":
                                drawer.close();                                
                                setNode(holderPane,Evenement);
                                break;
                            case "article":
                                drawer.close();                                
                                setNode(holderPane,Article);
                                break; 
                                                          
                        }
                    });
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    }
    }
    public static void setNode(AnchorPane holderPane,Node node) {
        holderPane.getChildren().clear();        
        holderPane.getChildren().add((Node) node);
    } 

    @FXML
    private void SigninAction(MouseEvent event) throws IOException {
    System.out.println("Se Connecter");
    AnchorPane login = FXMLLoader.load(getClass().getResource(Routes.LOGINVIEW));
    setNode(holderPane,login);
    }

    @FXML
    private void SignoutAction(MouseEvent event) throws IOException {
       
        FXMLLoginController.pseudo=null;
        FXMLLoginController.Identifiant=null;
        FXMLLoginController.docteur=false;
        FXMLLoginController.patient=false;
       
        FXMLLoader loader=new FXMLLoader(getClass().getResource(Routes.HOMEVIEW)); 
            Parent root=loader.load();
            Scene s = holderPane1.getScene(); 
            s.setRoot(root);
            
            }

    @FXML
    private void profileAction(MouseEvent event) throws IOException {
        if(FXMLLoginController.docteur){
                  Parent root= FXMLLoader.load(getClass().getResource(Routes.PROFILEMEDECIN));
                Scene scene = new Scene(root);
                Stage stage=new Stage();
                stage.getIcons().add(new Image("/health_advisor/profile.png"));
                stage.setScene(scene);
                stage.show();
        }else{
            if(FXMLLoginController.patient){
                Parent root= FXMLLoader.load(getClass().getResource(Routes.PROFILEPATIENT));
                Scene scene = new Scene(root);
                Stage stage=new Stage();
                stage.getIcons().add(new Image("/health_advisor/profile.png"));
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    private void rendezVousAction(ActionEvent event) throws IOException {
        System.out.println("Recuperation Modif");
    AnchorPane modifrdv = FXMLLoader.load(getClass().getResource(Routes.MODIFRDV));
    setNode(holderPane,modifrdv);
    }
    
}
