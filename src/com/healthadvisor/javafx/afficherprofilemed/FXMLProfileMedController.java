/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.javafx.afficherprofilemed;

import com.healthadvisor.entities.Medecin;
import com.healthadvisor.entities.Patient;
import com.healthadvisor.entities.Utilisateur;
import com.healthadvisor.enumeration.StatutMedecinEnum;
import com.healthadvisor.javafx.inscrimedecin.ComboBoxAutoComplete;
import com.healthadvisor.javafx.inscrimedecin.FXMLInscriMedecinController;
import com.healthadvisor.javafx.login_fx.FXMLLoginController;
import com.healthadvisor.service.impl.GestionMedecin;
import com.healthadvisor.service.impl.GestionPatient;
import com.healthadvisor.service.impl.GestionUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author khattout
 */
public class FXMLProfileMedController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField pays;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXComboBox<String> sexe;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField diplome;
    @FXML
    private JFXComboBox<String> specialite;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton confirmer;
    private Desktop desktop=Desktop.getDesktop();
    private String image_url;
    String[] sexelist={"Homme","Femme"};
          String[] specialitelist={
"Anesthésiologie",
"Biochimie médicale",
"Cardiologie",
"Chirurgie cardiaque",
"Chirurgie colorectale",
"Chirurgie générale",
"Chirurgie générale oncologique",
"Chirurgie générale pédiatrique",
"Chirurgie orthopédique",
"Chirurgie plastique",
"Chirurgie thoracique",
"Chirurgie vasculaire",
"Dermatologie",
"Endocrinologie et métabolisme",
"Gastro-entérologie",
"Génétique médicale",
"Gériatrie",
"Gérontopsychiatrie",
"Hématologie",
"Hématologie/oncologie pédiatrique",
"Immunologie clinique et allergie",
"Maladies infectieuses",
"Médecine d'urgence",
"Médecine d'urgence pédiatrique",
"Médecine de l'adolescence",
"Médecine de soins intensifs",
"Médecine du travail",
"Médecine interne",
"Médecine interne générale",
"Médecine maternelle et fœtale",
"Médecine néonatale et périnatale",
"Médecine nucléaire",
"Médecine physique et réadaptation",
"Microbiologie médicale",
"Néphrologie",
"Neurochirurgie",
"Neurologie",
"Neuropathologie",
"Obstétrique et gynécologie",
"Oncologie gynécologique",
"Oncologie médicale",
"Ophtalmologie",
"Pathologie générale",
"Pathologie hématologique",
"Pathologie judiciaire",
"Pédiatrie",
"Pédiatrie du développement",
"Pneumologie",
"Psychiatrie",
"Psychiatrie légale",
"Radio-oncologie",
"Radiologie diagnostique",
"Rhumatologie",
"Santé publique et médecine préventive",
"Urologie"};
    @FXML
    private JFXTextField numtel;
    @FXML
    private Label strenghtP;
    @FXML
    private JFXButton modifPos;
    @FXML
    private Label statutCompteLabel;
    @FXML
    private ImageView statutImage;
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXButton profileB;
    @FXML
    private Label imageLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            confirmer.setOpacity(0);
            ObservableList<String> sl=FXCollections.observableArrayList(sexelist);
            sexe.setItems(sl);
            ObservableList<String> sll=FXCollections.observableArrayList(specialitelist);
            specialite.setItems(sll);
            new ComboBoxAutoComplete<String>(specialite);
            
            GestionUtilisateur gu=new GestionUtilisateur();
            Utilisateur u=gu.AfficherUtilisateurCin(FXMLLoginController.Identifiant);
            System.out.println("Utilisateur"+u);
            GestionPatient gp=new GestionPatient();
            Patient p=gp.AfficherPatientCin(u.getCin());
            GestionMedecin gm=new GestionMedecin();
            Medecin m= gm.AfficherMedecinLogin(p.getLogin());
            
            sexe.setValue(u.getSexe());
            specialite.setValue(m.getSpecialite());
            this.nom.setText(u.getNom());
            this.prenom.setText(u.getPrenom());
            this.email.setText(u.getEmail());
            
            Date date = u.getDate_naiss();
            Instant instant = Instant.ofEpochMilli(date.getTime());
            LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
            this.date.setValue(res);
            FileInputStream input;          
            input = new FileInputStream(p.getPhoto_profile());
            Image img_profile = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            image_url=p.getPhoto_profile();
            this.imageView.setImage(img_profile);
            this.pays.setText(u.getPays());
            this.ville.setText(u.getVille());
            this.adresse.setText(m.getAdresse());
            this.login.setText(p.getLogin());
            this.password.setText(p.getPassword());
            this.diplome.setText(m.getDiplome());
            this.statutCompteLabel.setText(m.getStatut_compte());
            if(m.getStatut_compte().equalsIgnoreCase(StatutMedecinEnum.NON_VALIDE.name())){
                statutImage.setImage(new Image("/com/healthadvisor/javafx/afficherprofilemed/notverified.png"));
            }else {
                if(m.getStatut_compte().equalsIgnoreCase(StatutMedecinEnum.VALIDE.name())){
                    statutImage.setImage(new Image("/com/healthadvisor/javafx/afficherprofilemed/checkmark.png"));
                    
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(u.getNum_tel());
            String str = sb.toString();
            
            this.numtel.setText(str);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLProfileMedController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLProfileMedController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void modifierMedecin(MouseEvent event) {
        email.setEditable(true);
        adresse.setEditable(true);
        login.setEditable(true);
        password.setEditable(true);
        numtel.setEditable(true);
        ville.setEditable(true);
        pays.setEditable(true);
        this.modifPos.setDisable(false);
        this.profileB.setDisable(false);

        confirmer.setOpacity(1);


        
    }

    @FXML
    private void validerModifMedecin(MouseEvent event) {
           Image img=new Image("/com/healthadvisor/ressources/cancel.png");
        Notifications notif=Notifications.create()
               .graphic(new ImageView(img))
                    .title("Champs Invalide")
                    .text("Il faut remplir tous les champs")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle(); 
              Image img2=new Image("/com/healthadvisor/ressources/checked.png");
        Notifications notif2=Notifications.create()
               .graphic(new ImageView(img2))
                    .title("Modification Profile")
                    .text("Profile Modifié avec succés")
                    .hideAfter(Duration.seconds(4))
                    .position(Pos.TOP_RIGHT)
                    .darkStyle(); 
        try{
 String nom=this.nom.getText();
 String prenom=this.prenom.getText();
 String email=this.email.getText();
 LocalDate localDate =date.getValue();
 Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
 Date datefinal = Date.from(instant);
            System.out.println("Date de naissance"+date);
 String sexe=this.sexe.getValue();
 String specialite=this.specialite.getValue();
 String pays=this.pays.getText();
 String ville=this.ville.getText();
 String login=this.login.getText();
 String password=this.password.getText();
 String diplome=this.diplome.getText();
 String adresse=this.adresse.getText();
 String num_tel=this.numtel.getText();
        int num=Integer.parseInt(num_tel);
        GestionUtilisateur gu=new GestionUtilisateur();
        
        Utilisateur u=new Utilisateur(FXMLLoginController.Identifiant, nom, prenom, email, datefinal, sexe, pays, ville,num);
        gu.ModifierUtilisateur(u);
        GestionPatient gp=new GestionPatient();
            System.out.println("L'URL DE L'IMAGE"+image_url);
        Patient p=new Patient(login, password, u.getCin(),image_url);
        gp.ModifierPatient(p);
        GestionMedecin gm=new GestionMedecin();
            System.out.println("LAtitude"+FXMLLoginController.LAT_P_Co);
        Medecin m=new Medecin(login, specialite, adresse, diplome, 0,FXMLLoginController.LAT_P_Co,FXMLLoginController.LONG_P_Co,this.statutCompteLabel.getText(), login, password, u.getCin(),image_url);
        gm.ModifierMedecin(m);

         
      
        this.email.setEditable(false);
        this.pays.setEditable(false);
        this.ville.setEditable(false);
        this.adresse.setEditable(false);
        this.login.setEditable(false);
        this.password.setEditable(false);
        this.modifPos.setDisable(true);
        this.profileB.setDisable(true);
        confirmer.setOpacity(0);
        notif2.show();
        }catch(Exception e){
        notif.show();
 
        }
    }

    @FXML
    private void mdpControl(KeyEvent event) {
        String password;
        password = this.password.getText();

        boolean hasLetter = false;
        boolean hasDigit = false;

        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                char x = password.charAt(i);
                if (Character.isLetter(x)) {

                    hasLetter = true;
                }

                else if (Character.isDigit(x)) {

                    hasDigit = true;
                }

                // no need to check further, break the loop
                if(hasLetter && hasDigit){

                    break;
                }

            }
            if (hasLetter && hasDigit) {
                strenghtP.setStyle("-fx-font-size: 15;\n" +
" -fx-text-fill: #ADFF2F;");
                strenghtP.setText("Fort");
            } else {
                strenghtP.setStyle(" -fx-font-size: 15;\n" +
" -fx-text-fill: #F39C12;");
                strenghtP.setText("Faible");
            }
        } else {
            strenghtP.setStyle(" -fx-font-size: 15;\n" +
" -fx-text-fill: #C70039;");
                strenghtP.setText("il faut au moins 8 caractères");
        }
    }

    @FXML
    private void telControl(KeyEvent event) {
          String num=numtel.getText();
       try{
       numtel.setFocusColor(Color.BLUE);
       int numtel=Integer.parseInt(num);
       }catch(NumberFormatException ex)  {
       numtel.setFocusColor(Color.RED);

       }
    }

    @FXML
    private void emailControl(KeyEvent event) {
          String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email.getText());
        if (controler.matches()){
        //Ok : la saisie est bonne
        email.setFocusColor(Color.BLUE);
        }else{email.setFocusColor(Color.RED);
        //La c'est pas bon
        }
    }

    @FXML
    private void modifPositionAction(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/healthadvisor/javafx/gmap/FXMLDocument.fxml"));
            Parent parent = loader.load();        
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Recuperer Ma Position");
            stage.getIcons().add(new Image("/com/healthadvisor/javafx/inscrimedecin/location.png"));
            stage.setScene(new Scene(parent));
            stage.show();
    }

    
    @FXML
    private void ParcourirImageP(ActionEvent event) {
             final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(anchor.getScene().getWindow());
        if (file != null) {
            openFile(file);
        }
    }
 

    private void openFile(File file) {
        FileInputStream input;
        try {
            desktop.open(file);
            File dest = new File("C:\\wamp64\\www\\HealthAdvisorImages\\" + file.getName());
            Files.copy(file.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            image_url = dest.toPath().toString();
            //imageLabel.setText(dest.toPath().toString());
            System.out.println("URL Image "+image_url);
            System.out.println("Image enregistrée avec succés");
            input = new FileInputStream(dest.toPath().toString());
            javafx.scene.image.Image img_profile = SwingFXUtils.toFXImage(ImageIO.read(input), null);
            imageView.setImage(img_profile);
        } catch (IOException ex) {
            System.err.println("Erreur d'enregistrement d'image");
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Choisir une image");
        fileChooser.setInitialDirectory(
                new File("C:\\Users\\aaa\\Desktop\\")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    
}
