package application;
	
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Interface{
	
	private MenuBar mainMenu = null;
	protected Menu menuFichier = null;
	protected MenuItem menuEnregistrer = null;
	protected MenuItem menuEnregistrerSous = null;
	protected MenuItem menuFermer = null;
	protected MenuItem menuQuitter = null;
	
	protected Menu menuTratement = null;
	protected MenuItem menuOuvrirFichier = null;
	
	private BorderPane root = null;
	protected Scene scene = null;
	
	private VBox zoneHaut = null;
	private VBox zoneGauche = null;
	
	private VBox zoneCenter = null;
	private TextArea zoneText = null;
	protected HBox zoneBas = null;
	
	protected ListView <String> zoneTextList = null;
	
	private VBox zoneRightBoutons = null;
	protected Button btnCercle = null;
	protected Button btnRectangle = null;
	protected Button btnLosange = null;
	protected Button btnCarre = null; 
	
	public Interface() {
		try {
			creerZoneMenu();
			creerZoneGauche();
			creerZoneRightButons();
			//creerZoneCenter();
			creerZoneBas();
			
			
			root = new BorderPane();
			scene = new Scene(root,800,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root.setTop(mainMenu);
			root.setLeft(zoneGauche);
			root.setCenter(zoneCenter);
			root.setRight(zoneRightBoutons);
			root.setBottom(zoneBas);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void creerZoneBas() 
	{
		zoneBas = new HBox();
		zoneBas.setMinSize(400, 400);
	}
	@SuppressWarnings("unused")
	private void creerZoneCenter() 
	{
		zoneCenter = new VBox();
		zoneText = new TextArea();
		zoneText.minHeight(300);
		zoneText.minWidth(400);
		//zoneText.setPrefRowCount(30);
		zoneCenter.getChildren().add(zoneText);
		
	}
	public void creerZoneMenu ()
	{
		zoneHaut = new VBox();
		mainMenu = new MenuBar();
		
		menuFichier = new Menu("Fichier");
		menuEnregistrer = new MenuItem("Enregistrer");
		menuEnregistrerSous = new MenuItem("Enregistrer sous ...");
		menuFermer = new MenuItem("Fermer");
		
		menuQuitter = new MenuItem ("Quitter");
		//ajout de la methode quitter pour le menuQuitter
		menuQuitter.setOnAction(new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent event)
					{
						System.exit(0);
					}
				}
				);
		//ajout des menuItems dans le menuPrincipal;
		menuFichier.getItems().addAll(menuEnregistrer, menuEnregistrerSous, menuFermer, menuQuitter);
		
		menuTratement= new Menu("Traitement fichier");
		menuOuvrirFichier = new MenuItem("Ouvrir...");
		
		menuTratement.getItems().add(menuOuvrirFichier);
		//ajout du menuPrincipal dans la bar de menu
		mainMenu.getMenus().addAll(menuFichier, menuTratement);
		//ajout du menu dans la zone de la scene
		zoneHaut.getChildren().add(mainMenu);
	}
	
	public void creerZoneGauche()
	{
		zoneGauche = new VBox();
		
		zoneTextList = new ListView <String>();
		zoneTextList.setMinSize(600, 400);
		//ObservableList<String> valeurs= FXCollections.observableArrayList("val1", "val2", "val3"); 
		//zoneTextList.setItems(valeurs);
		
		//zoneTextList.setEditable(true);
		//zoneText.setPrefSize(10, 10);
		zoneGauche.getChildren().add(zoneTextList);
	}
	
	public void creerZoneRightButons()
	{
		zoneRightBoutons = new VBox();
		
		btnCercle = new Button("Créer un Cercle");
		btnCercle.getStyleClass().add("record-sales");
		btnCercle.setMaxSize(200, 50);
		
		btnRectangle = new Button("Créer un Rectangle");
		btnRectangle.getStyleClass().add("record-sales");
		btnRectangle.setMaxSize(200, 50);
		
		btnLosange  = new Button("Créer un Losange");
		btnLosange.getStyleClass().add("record-sales");
		btnLosange.setMaxSize(200, 50);
		
		btnCarre = new Button("Créer un Carrée");
		btnCarre.getStyleClass().add("record-sales");
		btnCarre.setMaxSize(200, 50);
		
		zoneRightBoutons.getChildren().addAll(btnCercle, btnRectangle, btnLosange, btnCarre);
		zoneRightBoutons.setPadding(new Insets(15,12,15,12));
		zoneRightBoutons.setSpacing(6);
	}
}
