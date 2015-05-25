package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import forme.Carre;
import forme.Cercle;
import forme.Forme;
import forme.Losange;
import forme.Rectanglee;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GestionFormes extends Application 
{
	/**
	 * Pointeur sur un object "File". Garde l'object "File" des formes qui sont présentement affichés
	 */
	private File fileCourant = null;
	/**
	 * Structure de données en mémoire pour contenir des objects "Formes"
	 */
	private ArrayList <Forme> vecFormes = null;
	/**
	 * Un pointeur pour interface
	 */
	private Interface objectInterface;
	/**
	 * Un pointeur stage
	 */
	private Stage stage;
	
	public void start(Stage primaryStage) {
		try {
			//initialisation du vecteur des formes
			vecFormes = new ArrayList<Forme> ();
			stage=primaryStage;
			objectInterface = new Interface();
			ajouterEcouteurs();
			
			stage.setScene(objectInterface.scene);
			stage.setTitle("Gestion de formes");
			
			//restaurerFichier();
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Méthode qui permet d'ajouter un écouteur d'évenement à chacun des sous menu de la zone de menus
	 */
	private void ajouterEcouteurs() 
	{
		objectInterface.btnCercle.setOnAction(new ActionBoutons());
		objectInterface.btnRectangle.setOnAction(new ActionBoutons ());
		objectInterface.btnCarre.setOnAction(new ActionBoutons());
		objectInterface.btnLosange.setOnAction(new ActionBoutons());
		
		objectInterface.menuEnregistrer.setOnAction(new ActionMenu ());
		objectInterface.menuEnregistrerSous.setOnAction(new ActionMenu());
		objectInterface.menuFermer.setOnAction(new ActionMenu());
		objectInterface.menuQuitter.setOnAction(new ActionMenu());
		objectInterface.menuOuvrirFichier.setOnAction(new ActionMenu());
	}
	/**
	 * Classe permettant d'implémenter la méthode pour l'écoute des clics sur les boutons du bas
	 */
	private class ActionBoutons implements EventHandler <ActionEvent>
	{
		public void handle (ActionEvent evenement)
		{
			if (objectInterface.btnCercle == evenement.getSource())
			{
				gestionCreerCercle();
			}
			else if(objectInterface.btnRectangle == evenement.getSource())
			{
				gestionCreerRectangle();
			}
			else if(objectInterface.btnCarre == evenement.getSource())
			{
				gestionCreerCarre();
			}
			else if(objectInterface.btnLosange == evenement.getSource())
			{
				gestionCreerLosange();
			}
			mettreAJourListe();
		}
	}
	private class ActionMenu implements EventHandler <ActionEvent>
	{
		public void handle (ActionEvent event)
		{
			Object optionMenu = event.getSource();
			
			ObservableList<MenuItem> options = objectInterface.menuFichier.getItems();
			
			ObservableList<MenuItem> options1 = objectInterface.menuTratement.getItems();
			
			if(optionMenu == options.get(0))
			{
				gestionEnregistrer();
			}
			else if(optionMenu == options.get(1))
			{
				gestionEnregistrerSous();
			}
			else if(optionMenu == options.get(2))
			{
				gestionFermer();
			}
			else if (optionMenu == options.get(3))
			{
				gestionQuiter();
			}
			
			else if(optionMenu == options1.get(0))
			{
				try {
					gestionOuvertureFichier();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//dans tous les cas mettre la liste graphique à jour
			mettreAJourListe();
		}
	}
	private void gestionCreerLosange() 
	{
		Forme unLosange = saisirEtCreerLosange();
		if(unLosange != null)
		{
			ajouterForme(unLosange);
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Aucun Losange n'a pas été ajouté à la liste.");
			alert.show();
		}
	}
	private Forme saisirEtCreerLosange() {
		Losange unLosange = null;
		int pointX, pointY; 
		double base, hauteur;
		try
		{
			do
			{
				pointX = saisirObjectEntier("Donnez le point X : ");
			}while(!Forme.validerX(pointX));
			do
			{
				pointY = saisirObjectEntier("Donnez le point Y : ");
			}while(!Rectanglee.validerY(pointY));
			do
			{
				base = saisirObjectEntier("Dannez la base : ");
			}while (!Rectanglee.validerBase(base));
			do
			{
				hauteur = saisirObjectEntier("Donnez l'hauteur : ");
			}while (!Rectanglee.validerHauteur(hauteur));
			
			unLosange = new Losange(pointX, pointY, base, hauteur);
		}catch(Exception e)
		{
			
		}
		
		return unLosange;
	}
	private void gestionCreerCarre() {
		Forme unCarre = saisirEtCreerCarre();
		if(unCarre != null)
		{
			ajouterForme(unCarre);
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Aucun Carré n'a pas été ajouté à la liste.");
			alert.show();
		}
	}
	private Forme saisirEtCreerCarre() {
		Carre unCarre = null;
		int pointX, pointY;
		double cote;
		try
		{
			do
			{
				pointX = saisirObjectEntier("Donnez le point X : ");
			}while(!Forme.validerX(pointX));
			do
			{
				pointY = saisirObjectEntier("Donnez le point Y : ");
			}while(!Forme.validerY(pointY));
			do
			{
				cote = saisirObjectEntier("Donnez la côte");
			}while(!Carre.validerCote(cote));
			unCarre = new Carre(pointX,pointY, cote);
		}catch(Exception e)
		{
			
		}
		return unCarre;
	}
	private void gestionCreerRectangle() 
	{
		Forme unRectangle = saisirEtCreerRectangle();
		if(unRectangle != null)
		{
			ajouterForme(unRectangle);
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Aucun Rectangle n'a pas été ajouté à la liste.");
			alert.show();
		}
		creerForme(unRectangle);
	}
	private Forme saisirEtCreerRectangle() {
		Rectanglee unRectangle = null;
		int pointX, pointY; 
		double base, hauteur;
		try
		{
			do
			{
				pointX = saisirObjectEntier("Donnez le point X : ");
			}while(!Forme.validerX(pointX));
			do
			{
				pointY = saisirObjectEntier("Donnez le point Y : ");
			}while(!Rectanglee.validerY(pointY));
			do
			{
				base = saisirObjectEntier("Dannez la base : ");
			}while (!Rectanglee.validerBase(base));
			do
			{
				hauteur = saisirObjectEntier("Donnez l'hauteur : ");
			}while (!Rectanglee.validerHauteur(hauteur));
			
			unRectangle = new Rectanglee(pointX, pointY, base, hauteur);
		}catch(Exception e)
		{
			
		}
		
		return unRectangle;
	}
	/**
	 * Permet de faire la gestion de la création d'un cercle 
	 *  -	Saisir un cercle avec la méthode de saisie appropriée.
	 * -	Valider si on a un objet, si oui le mettre dans la liste.
	 * -	à mettre les messages en conséquence.
	 * @param 
	 */
	public void gestionCreerCercle()
	{
		Forme unCercle = saisirEtCreerCercle();
		//System.out.println(unCercle.toString());
		
		if (unCercle != null)
		{
			ajouterForme(unCercle);
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Le cercle n'a pas été créé.");;
			alert.showAndWait();
		}
		creerForme(unCercle);
	}
	/**
	 * Méthode qui affiche la forme d'un object
	 * @param pForme La forme à afficher
	 */
	private void creerForme(Forme pForme)
	{
		if (pForme instanceof Cercle)
		//if(pForme.getClass().getName().equals("forme.Cercle"))
		{
			
			Circle cercle = new Circle();
			cercle.setCenterX(((Cercle)pForme).getX());
			cercle.setCenterY(((Cercle)pForme).getY());
			cercle.setRadius(((Cercle)pForme).getRayon());
			cercle.setFill(Color.RED);
			
			objectInterface.zoneBas.getChildren().add(cercle);
		}
		else if(pForme.getClass().getName().equals("forme.Rectanglee"))
		{
			Rectangle rectangle=new Rectangle();
			rectangle.setX(pForme.getX());
			rectangle.setY(pForme.getY());
			rectangle.setWidth(((Rectanglee)pForme).getBase());
			rectangle.setHeight(((Rectanglee)pForme).getHauteur()); 
			rectangle.setFill(Color.GREEN); 
			rectangle.setStroke(Color.DARKGREEN); 
			rectangle.setStrokeWidth(5); 
			rectangle.setArcHeight(30); 
			rectangle.setArcWidth(30);
			
			objectInterface.zoneBas.getChildren().add(rectangle);
		}
	}
	/**
	 * Méthode qui permet de saisir et de créer un object Cercle valide. La gestion de l'annulation est tres importante et la saisie se fait avec validation
	 * Important : utilisez les outils de saisis de la classe Util.java du paquetage utilitaire.Util
	 * @return Cercle, un object Cercle valide ou null si la saisie a été annulée
	 */
	private Forme saisirEtCreerCercle() {
		//Variable de retour
		Cercle unCercle = null;
		// Info sur le cercle (l'abscisse, l'ordonné du centre du cercle et le rayon )
		int x,y,r;
		//Saisie du x
		try
		{
			do
			{
				x = saisirObjectEntier("Donnez l'abscisse du centre du cercle : ");
			}
			while(!Cercle.validerX(x));
			//Saisie du y
			do 
			{
				y = saisirObjectEntier("Donenz l'ordonnée du centre du cercle : ");
			}
			while (!Cercle.validerY(y));
			do
			{
				r = saisirObjectEntier("Donnez le rayon du cercle : ");
			}
			while(!Cercle.validerRayon(r));
			
			unCercle = new Cercle(x,y,r);
			System.out.println(unCercle.toString());
		}
		catch(Exception e)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur saisie");
			alert.setHeaderText("Aucun cercle n'a pas été ajouté à la liste.");
			alert.show();
		}
		
		return unCercle;
	}
	/**
	 * Permet de saisir un entier. La saisie est non bloquante
	 * @param pQuestion chaine permettant de poser une question
	 * @return Integer, un object entier (Integer) permettant de retourner la valeur ou "null" si l'utilisateur a décidé d'annuler la saisie
	 */
	private Integer saisirObjectEntier(String string) 
	{
		Optional <String> nombreSaisie = null;
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Saisie d'un entier");
		dialog.setHeaderText(null);
		dialog.setContentText(string);
		
		nombreSaisie = dialog.showAndWait();
		return Integer.valueOf(nombreSaisie.get());
	}
	/**
	 * Ajouter une forme à la structure de données de l'application, le ArrayList
	 * @param pForme la forme à ajouter
	 */
	private void ajouterForme(Forme pForme) 
	{
		this.vecFormes.add(pForme);
		//System.out.println(vecFormes.get(0));
	}
	
	private void mettreAJourListe() {
		//Backup de l'index
		int index = objectInterface.zoneTextList.getSelectionModel().getSelectedIndex();
		
		ObservableList<String> formes = FXCollections.observableArrayList();
		for(Forme f : vecFormes)
		{
			formes.addAll(f.toString());
		}
		objectInterface.zoneTextList.setItems(formes);
		
		//remettre l'index à sa position
		objectInterface.zoneTextList.getSelectionModel().select(index);
	}

	private void gestionQuiter() {
		System.exit(0);
		
	}
	/**
	 * Permet de fermer le fichier courant
	 * -	Si la liste n'est pas vide et que l'utilisateur veut sauvegarder ses données, enregistrer les données courantes (gestionEnregistrerSous) avant de vider la liste
	 */
	private void gestionFermer() {
		if (!vecFormes.isEmpty())
		{
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Voulez vous souvgarder les données???");
			
			Optional<ButtonType> button = alert.showAndWait();
			
			if(button.get() == ButtonType.OK)
			{
				gestionEnregistrer();
				System.out.println("souvegarde");
			}
			else if(button.get() == ButtonType.CANCEL)
			{
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setHeaderText("Vous êtes vraiment sur???");
				alert1.showAndWait();
			}
		}
		vecFormes.clear();
		fileCourant = null;
		
	}

	/**Permet de gérer l'enregistrement d'une liste de formes dans un  nouveau fichier (flux d'octets avec filtre de données)
	 * -	Demander le nom du fichier, l'assigner au fichier courant et écrire dedans les formes
	 * -	Afficher un message si erreur lors de la sauvegarde ou sauvegarde ok
	 */
	private void gestionEnregistrerSous() 
	{
		FileChooser choixFichier = new FileChooser();
		choixFichier.setInitialDirectory(new File(System.getProperty("user.dir")));
		File fichier = choixFichier.showSaveDialog(stage);
		if (fichier != null)
		{
			fileCourant = fichier;
			if(!ecrireTexte(vecFormes, fileCourant))
			{
				Alert alert = new Alert (AlertType.ERROR);
				alert.setHeaderText("Erreur de souvegarde");
				alert.show();
			}
			else
			{
				Alert alert = new Alert (AlertType.CONFIRMATION);
				alert.setHeaderText("Succès");
				alert.show();
			}
		}
		
	}
	/**
	 * Permet de gérer l'enregistrement d'une liste de formes dans le fichier courant
	 *  -	Si on a un fichier courant on écrit les formes de la liste dedans
	 * -	Sinon on demande à  l'utilisateur le nom d'un fichier (gestionEnregistrerSous)
	 * -	Afficher un message si erreur lors de la sauvegarde ou sauvegarde ok
	 */
	private void gestionEnregistrer() {
		if(fileCourant != null)
		{
			if (!ecrireTexte(vecFormes, fileCourant))
			{
				Alert alert = new Alert (AlertType.ERROR);
				alert.setHeaderText("Erreur de souvegarde");
				alert.show();
			}
			else
			{
				Alert alert = new Alert (AlertType.INFORMATION);
				alert.setHeaderText("Succès pour la souvegarde des donnes \n dans le fichier");
				alert.show();
			}
		}
		else
		{
			gestionEnregistrerSous();
		}
		
	}
	private boolean ecrireTexte(ArrayList<Forme> vecFormes, File fileCourant) 
	{
		boolean ecritureOK = true;
		Forme formeTemp = null;
		String forme = null;
		FileWriter fileForme = null;
		BufferedWriter bufferForme = null;
		
		try
		{
			//avec tampon et orientée data
			fileForme = new FileWriter(fileCourant);
			bufferForme = new BufferedWriter(fileForme);
			try
			{
				for (int i = 0; i<vecFormes.size(); i++)
				{
					formeTemp = vecFormes.get(i);
					if (formeTemp instanceof Cercle)
					{
						forme = formeTemp.getClass().getName() + ";" + formeTemp.getX() + ";" +formeTemp.getY() + ";"+ ((Cercle) formeTemp).getRayon();
					}
					else if (formeTemp instanceof Carre)
						{
							forme = formeTemp.getClass().getName() + ";" + formeTemp.getX() + ";" +formeTemp.getY() + ";"+ ((Carre)formeTemp).getBase() + ";"+ ((Carre)formeTemp).getHauteur();
						}
						else if (formeTemp instanceof Rectanglee)
							{
							forme = formeTemp.getClass().getName() + ";" + formeTemp.getX() + ";" +formeTemp.getY() + ";"+ ((Rectanglee)formeTemp).getBase() + ";"+ ((Rectanglee)formeTemp).getHauteur();
							}

							else if (formeTemp instanceof Losange)
								{
								forme = formeTemp.getClass().getName() + ";" + formeTemp.getX() + ";" +formeTemp.getY() + ";"+ ((Losange)formeTemp).getBase() + ";"+ ((Losange)formeTemp).getHauteur();
								}
					bufferForme.write(forme, 0,forme.length());
					//insere une fin de ligne
					bufferForme.newLine();
				}
			}catch(IOException e)
			{
				ecritureOK = false;
			}
			bufferForme.close();
		}
		catch(FileNotFoundException e)
		{
			ecritureOK = false;
		}
		catch (IOException e)
		{
			ecritureOK = false;
		}
		return ecritureOK;
	}
	private void gestionOuvertureFichier() throws IOException 
	{
		FileChooser choixFichier = new FileChooser();
		choixFichier.setInitialDirectory(new File(System.getProperty("user.dir")));
		File fichier = choixFichier.showOpenDialog(stage);
		if (fichier != null)
		{
			fileCourant = fichier;
		if(!lectureTexte(fileCourant))
		{
			Alert alert = new Alert (AlertType.ERROR);
			alert.setHeaderText("Erreur de lecture");
			alert.show();
		}
		else
		{
			Alert alert = new Alert (AlertType.INFORMATION);
			alert.setHeaderText("Succès");
			alert.show();
		}
		}
	}
	private boolean lectureTexte(File fileCourant2) throws IOException 
	{
		boolean lectureOK = true;
		String ligne;
		String [] vecteurLigne;
		Cercle unCercle;
		Rectanglee unRectangle;
		Losange unLosange;
		Carre unCarre;
		try {
			BufferedReader entree = new BufferedReader (new FileReader (fileCourant2));
			do
			{
				ligne = entree.readLine();
				if(ligne != null) 
				{
					vecteurLigne = ligne.split(";");
					if(vecteurLigne[0].equals("forme.Cercle"))
					{
						unCercle = new Cercle(Integer.parseInt(vecteurLigne[1]),Integer.parseInt(vecteurLigne[2]), Double.parseDouble(vecteurLigne[3]) );
						ajouterForme(unCercle);
					}
					else if(vecteurLigne[0].equals("forme.Rectangle"))
					{
						unRectangle = new Rectanglee(Integer.parseInt(vecteurLigne[1]), Integer.parseInt(vecteurLigne[2]),Double.parseDouble(vecteurLigne[3]), Double.parseDouble(vecteurLigne[4]));
						ajouterForme(unRectangle);
					}
					else if(vecteurLigne[0].equals("forme.Losange"))
					{
						unLosange = new Losange(Integer.parseInt(vecteurLigne[1]), Integer.parseInt(vecteurLigne[2]),Double.parseDouble(vecteurLigne[3]), Double.parseDouble(vecteurLigne[4]));
						ajouterForme(unLosange);
					}
					else if(vecteurLigne[0].equals("forme.Carre"))
					{
						unCarre = new Carre(Integer.parseInt(vecteurLigne[1]), Integer.parseInt(vecteurLigne[2]),Double.parseDouble(vecteurLigne[3]));
						ajouterForme(unCarre);
					}
				}
			}while (ligne != null);
			entree.close();
		} catch (FileNotFoundException e) {
			lectureOK = false;
		}
		return lectureOK;
	}
	public static void main(String[] args) {
		launch(args);
	}

}
