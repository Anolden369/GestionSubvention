package sio.gestionsubventions;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.gestionsubventions.Model.Structure;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SubventionsController implements Initializable
{
    HashMap<String,HashMap<String, TreeMap<Integer,ArrayList<Structure>>>> lesSubventions;
    TreeItem racine;
    TreeItem racine2;
    TreeItem noeudSelectionne;
    String finalNomStructure;
    String finalMontantStructure;
    @FXML
    private AnchorPane apAffecter;
    @FXML
    private ListView lvVilles;
    @FXML
    private AnchorPane apStatistiques;
    @FXML
    private ListView lvSecteurs;
    @FXML
    private ComboBox cboAnnees;
    @FXML
    private TextField txtNomStructure;
    @FXML
    private TextField txtMontant;
    @FXML
    private Button btnAffecterSubvention;
    @FXML
    private Button btnMenuAffecter;
    @FXML
    private Button btnMenuStatistiques;
    @FXML
    private ListView lvVillesStats;
    @FXML
    private TreeView tvMontantsParSecteurs;
    @FXML
    private TreeView tvMontantsParAnnees;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        apAffecter.toFront();

        lesSubventions = new HashMap<>();
        racine = new TreeItem("Par secteur");
        racine.setExpanded(true);
        tvMontantsParSecteurs.setRoot(racine);

        lesSubventions = new HashMap<>();
        racine2 = new TreeItem("Par année");
        racine2.setExpanded(true);
        tvMontantsParAnnees.setRoot(racine2);

        lvVilles.getItems().addAll("Bordeaux","Nantes","Paris");
        lvSecteurs.getItems().addAll("Culture","Education","Santé","Sport");
        cboAnnees.getItems().addAll(2020,2021,2022,2023,2024,2025);
        cboAnnees.getSelectionModel().selectFirst();

        // Jeu d'essais au cas où :)
//        Structure structure1 = new Structure("Structure 1",1000);
//        Structure structure2 = new Structure("Structure 2",2000);
//        Structure structure3 = new Structure("Structure 3",3000);
//        Structure structure4 = new Structure("Structure 4",4000);
//        Structure structure5 = new Structure("Structure 5",5000);
//        Structure structure6 = new Structure("Structure 6",6000);
//        Structure structure7 = new Structure("Structure 7",7000);
//        Structure structure8 = new Structure("Structure 8",8000);
//        Structure structure9 = new Structure("Structure 9",9000);
//
//        ArrayList<Structure> lesStructuresDeBordeaux = new ArrayList<>();
//        lesStructuresDeBordeaux.add(structure1);
//        lesStructuresDeBordeaux.add(structure2);
//        lesStructuresDeBordeaux.add(structure3);
//
//        ArrayList<Structure> lesStructuresDeNantes = new ArrayList<>();
//        lesStructuresDeNantes.add(structure4);
//        lesStructuresDeNantes.add(structure5);
//        lesStructuresDeNantes.add(structure6);
//
//        ArrayList<Structure> lesStructuresDeParis = new ArrayList<>();
//        lesStructuresDeParis.add(structure7);
//        lesStructuresDeParis.add(structure8);
//        lesStructuresDeParis.add(structure9);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeBordeaux = new TreeMap<>();
//        lesAnneesDeBordeaux.put(2020, lesStructuresDeBordeaux);
//        lesAnneesDeBordeaux.put(2021, lesStructuresDeBordeaux);
//        lesAnneesDeBordeaux.put(2022, lesStructuresDeBordeaux);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeNantes = new TreeMap<>();
//        lesAnneesDeNantes.put(2020, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2021, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2022, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2023, lesStructuresDeNantes);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeParis = new TreeMap<>();
//        lesAnneesDeParis.put(2022, lesStructuresDeParis);
//        lesAnneesDeParis.put(2023, lesStructuresDeParis);
//        lesAnneesDeParis.put(2024, lesStructuresDeParis);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeBordeaux = new HashMap<>();
//        lesSecteursDeBordeaux.put("Santé", lesAnneesDeBordeaux);
//        lesSecteursDeBordeaux.put("Sport", lesAnneesDeBordeaux);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeNantes = new HashMap<>();
//        lesSecteursDeNantes.put("Education", lesAnneesDeNantes);
//        lesSecteursDeNantes.put("Culture", lesAnneesDeNantes);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeParis = new HashMap<>();
//        lesSecteursDeParis.put("Culture", lesAnneesDeParis);
//
//        lesSubventions.put("Bordeaux",lesSecteursDeBordeaux);
//        lesSubventions.put("Nantes",lesSecteursDeNantes);
//        lesSubventions.put("Paris",lesSecteursDeParis);

    }

    @FXML
    public void btnMenuClicked(Event event)
    {
        if(event.getSource()==btnMenuAffecter)
        {
            apAffecter.toFront();
        }
        else if(event.getSource()==btnMenuStatistiques)
        {
            apStatistiques.toFront();
        }
    }

    @FXML
    public void btnAffecterSubventionClicked(Event event)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(lvVilles.getSelectionModel().getSelectedItem() == null)
        {
            alert.setTitle("Erreur de seletion");
            alert.setContentText("Veuillez selectionner une ville !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else if(lvSecteurs.getSelectionModel().getSelectedItem() == null)
        {
            alert.setTitle("Erreur de seletion");
            alert.setContentText("Veuillez selectionner un secteur !");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(txtNomStructure.getText().isEmpty())
        {  //getSelectionModel = récupère le template;   getSelectedItem = récupère l'élément;
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir un nom de Structure !");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(txtMontant.getText().equals(""))
        {
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir un montant s'il vous plait !");
            alert.setHeaderText("");
            alert.showAndWait();
        } else
        {
            HashMap<String, TreeMap<Integer,ArrayList<Structure>>> lesSecteurs = new HashMap<>();
            Structure uneStructure = new Structure(txtNomStructure.getText(), Integer.parseInt(txtMontant.getText()));
            TreeMap<Integer,ArrayList<Structure>> lesAnnees = new TreeMap<>();

            if(!lvVillesStats.getItems().contains(lvVilles.getSelectionModel().getSelectedItem().toString()))
            {
                lvVillesStats.getItems().add(lvVilles.getSelectionModel().getSelectedItem().toString());
                ArrayList<Structure> lesStrutures= new ArrayList<>();
                lesStrutures.add(uneStructure);
                lesAnnees.put(cboAnnees.getSelectionModel().getSelectedItem().hashCode(), lesStrutures);
                lesSecteurs.put(lvSecteurs.getSelectionModel().getSelectedItem().toString(),lesAnnees);
                lesSubventions.put(lvVilles.getSelectionModel().getSelectedItem().toString(), lesSecteurs);

            } else
            {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Affectation réussie");
                alert2.setContentText("Subvention enregistrés !");
                alert2.setHeaderText("");
                alert2.showAndWait();
            }
        }



    }

    public void RemplirTreeViewSecteur(){

        TreeItem noeudVille;
        TreeItem noeudSecteur;
        racine.getChildren().clear();
        for (String ville : lesSubventions.keySet())
        {
            noeudVille = new TreeItem(ville);
            noeudVille.setExpanded(true);
            for (String secteur : lesSubventions.get(ville).keySet())
            {
                for (int annee : lesSubventions.get(ville).get(secteur).keySet()) {
                    noeudSecteur = new TreeItem(secteur);
                    for (Structure uneStructure : lesSubventions.get(ville).get(secteur).get(annee)) {
                        int calcul = uneStructure.getMontant();
                        noeudSecteur = new TreeItem(secteur.toString() + " : " + calcul);
                        noeudSecteur.setExpanded(true);
                        calcul += uneStructure.getMontant();
                    }
                    racine.getChildren().add(noeudSecteur);
                }
            }
        }
        tvMontantsParSecteurs.setRoot(racine);
    }

    public void RemplirTreeViewAnnee(){
        TreeItem noeudVille;
        TreeItem noeudSecteur;
        racine2.getChildren().clear();
        for (String ville : lesSubventions.keySet())
        {
            noeudVille = new TreeItem(ville);
            noeudVille.setExpanded(true);
            for (String secteur : lesSubventions.get(ville).keySet())
            {
                for (int annee : lesSubventions.get(ville).get(secteur).keySet()) {
                    noeudSecteur = new TreeItem(secteur);
                    for (Structure uneStructure : lesSubventions.get(ville).get(secteur).get(annee)) {
                        noeudSecteur = new TreeItem(annee + " : " + uneStructure.getMontant());
                        noeudSecteur.setExpanded(true);
                    }
                    racine2.getChildren().add(noeudSecteur);
                }
            }
        }
        tvMontantsParAnnees.setRoot(racine2);
    }

    @FXML
    public void lvVillesStatsClicked(Event event) {
        RemplirTreeViewSecteur();
        RemplirTreeViewAnnee();
    }

}