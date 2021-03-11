package fr.isika.cdi07.projet3demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.isika.cdi07.projet3demo.model.Categorie;
import fr.isika.cdi07.projet3demo.model.PortefeuilleProjet;
import fr.isika.cdi07.projet3demo.model.PorteurProjet;
import fr.isika.cdi07.projet3demo.model.Projet;
import fr.isika.cdi07.projet3demo.model.Role;
import fr.isika.cdi07.projet3demo.model.Territoire;
import fr.isika.cdi07.projet3demo.model.TypeProjet;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.modelform.ProjetForm;
import fr.isika.cdi07.projet3demo.services.CategorieService;
import fr.isika.cdi07.projet3demo.services.PortefeuilleService;
import fr.isika.cdi07.projet3demo.services.PorteurProjetService;
import fr.isika.cdi07.projet3demo.services.ProjetService;
import fr.isika.cdi07.projet3demo.services.RoleService;
import fr.isika.cdi07.projet3demo.services.TerritoireService;
import fr.isika.cdi07.projet3demo.services.TypeProjetService;
import fr.isika.cdi07.projet3demo.services.UtilisateurService;

@Controller
public class ProjetController {

	private static final Logger LOGGER = Logger.getLogger(ProjetController.class.getSimpleName());

	@Autowired
	private ProjetService projetService;

	@Autowired
	private TerritoireService territoireService;

	@Autowired
	private TypeProjetService typeProjetService;

	@Autowired
	private CategorieService categorieService;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PorteurProjetService porteurProjetService;

	@Autowired
	private PortefeuilleService portefeuilleService;



	//SHOW ALL PROJECT
	@GetMapping("/ShowAllProjetList")
	public String showAllProjetList(Model model) {
		model.addAttribute("listProjet", projetService.afficherAllProjet());
		return "listProjet";
	}
	//SHOW PROJECT WITH STATUS "PUBLIE"
	@GetMapping("/ShowProjetListPublie")
	public String showProjetListPublie(Model model) {
		model.addAttribute("listProjetPublie", projetService.afficherProjetPublie());
		return "catalogue";
	}

	// SHOW FORM TO CREATE PROJECT
	@GetMapping("/ShowNewProjetForm")
	public String showNewProjetForm(Model model, HttpSession session) {

		String userEmail = (String)session.getAttribute("emailUtilisateurConnecte");
		if (userEmail == null) {
			return "redirect:/showConnexionForm";
		}

		Utilisateur user = utilisateurService.chercherUtilisateurParEmail(userEmail);
		if (user == null) {
			return "ErrorSite";
		}



		Projet projet = new Projet();
		ProjetForm monForm = new ProjetForm();
		//		Optional<Role> role = roleService.testIsPorteurProjet(user);

		Role roleOfUser = roleService.hasRole(user, TypeRole.PORTEURPROJET);
		PorteurProjet porteurProjet = porteurProjetService.isPresent(roleOfUser);

		monForm.setRole(roleOfUser);
		monForm.setPorteurProjet(porteurProjet);
		monForm.setProjet(projet);

		Territoire territoire = new Territoire();
		TypeProjet typeProjet = new TypeProjet();
		Categorie categorie = new Categorie();

		model.addAttribute("monForm", monForm);
		model.addAttribute("categorie", categorie);
		model.addAttribute("typeProjet", typeProjet);
		model.addAttribute("territoire", territoire);
		model.addAttribute("listTerritoire", territoireService.afficherAllTerritoire());
		model.addAttribute("listTypeProjet", typeProjetService.afficherAllTypeProjet());

		return "newProjet";
	}


	//WRITE IN DB PROJECT WITH COMPONENT (CATEGORIE, TERRITOIRE, TYPEPROJET, PP, PORTEFEUILLE, ROLE)
	@PostMapping("/saveProjet")
	public String saveProjet(@ModelAttribute("monForm") ProjetForm monForm, @ModelAttribute("typeProjet") TypeProjet typeProjet, 
			@ModelAttribute("territoire") Territoire territoire, HttpSession session) {

		LOGGER.info("Selected data : idTypeProjet : " + typeProjet.getIdTypeProjet() + " idTerritoire " + territoire.getIdTerritoire());

		if(typeProjet.getIdTypeProjet().equals(0L) || territoire.getIdTerritoire().equals(0L)) {
			return "redirect:/ShowNewProjetForm";
		}

		Categorie categorieToUse = null;
		Optional<Categorie> categorie = categorieService.getCategorieByTerritoireAndType(territoire, typeProjet);
		if(categorie.isPresent()) {
			categorieToUse = categorie.get();
		} else {
			Categorie withTypeProjet = new Categorie().withTerritoire(territoire).withTypeProjet(typeProjet);
			categorieToUse = categorieService.ajoutCategorie(withTypeProjet);
		}
		monForm.getProjet().setCategorie(categorieToUse);

		PorteurProjet monPorteur = porteurProjetService.ajoutPorteur(monForm.getPorteurProjet());

		PortefeuilleProjet portefeuille = portefeuilleService.isPresent(monPorteur, "Defaut");
		PortefeuilleProjet addPortefeuille =  portefeuilleService.ajoutPortefeuille(portefeuille);

		monForm.getProjet().setPortefeuilleprojet(addPortefeuille);
		projetService.ajoutProjet(monForm.getProjet());

		return "redirect:/ShowAllProjetList";
	}

	//SHOW FORM TO UPDATE PROJECT
	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateProjetForm(@PathVariable (value = "id") Long id, Model model, HttpSession session) {

		String userEmail = (String)session.getAttribute("emailUtilisateurConnecte");
		if (userEmail == null) {
			return "redirect:/showConnexionForm";
		}

		Utilisateur user = utilisateurService.chercherUtilisateurParEmail(userEmail);
		if (user == null) {
			return "ErrorSite";
		}

		Role roleOfUser = roleService.hasRole(user, TypeRole.PORTEURPROJET);

		Projet projet = projetService.getProjetByIdNoOptional(id);
		ProjetForm monForm = new ProjetForm();

		PorteurProjet porteurProjet = porteurProjetService.isPresent(roleOfUser);

		LOGGER.info("*************PORTEUR PROJET : " + porteurProjet);
		monForm.setRole(roleOfUser);
		monForm.setPorteurProjet(porteurProjet);
		monForm.setProjet(projet);

		Territoire territoire = projet.getCategorie().getTerritoire();
		TypeProjet typeProjet = projet.getCategorie().getTypeProjet();

		monForm.setTerritoire(territoire);
		monForm.setTypeProjet(typeProjet);
		
		model.addAttribute("monForm", monForm);
	
		model.addAttribute("listTerritoire", territoireService.afficherAllTerritoire());
		model.addAttribute("listTypeProjet", typeProjetService.afficherAllTypeProjet());

		return "updateProjet";
	}


	@PostMapping("/updateProjet")
	public String updateProjet(@ModelAttribute("monForm") ProjetForm monForm, HttpSession session) {

		LOGGER.info("Selected data : idTypeProjet : " + monForm.getTypeProjet().getIdTypeProjet() + " idTerritoire " + monForm.getTerritoire().getIdTerritoire());

		if(monForm.getTypeProjet().getIdTypeProjet().equals(0L) || monForm.getTerritoire().getIdTerritoire().equals(0L)) {
			return "redirect:/ShowNewProjetForm";
		}

		Categorie categorieToUse = null;
		Optional<Categorie> categorie = categorieService.getCategorieByTerritoireAndType(monForm.getTerritoire(), monForm.getTypeProjet());
		if(categorie.isPresent()) {
			categorieToUse = categorie.get();
		} else {
			Categorie withTypeProjet = new Categorie().withTerritoire(monForm.getTerritoire()).withTypeProjet(monForm.getTypeProjet());
			categorieToUse = categorieService.ajoutCategorie(withTypeProjet);
		}
		monForm.getProjet().setCategorie(categorieToUse);
		LOGGER.info("PORTEURprojet POSTTTT : " + monForm.getPorteurProjet());
		PorteurProjet monPorteur = porteurProjetService.ajoutPorteur(monForm.getPorteurProjet());

		LOGGER.info("PORTEFEUILLE ************************* POSTTTT : "+monPorteur);

		PortefeuilleProjet portefeuille = portefeuilleService.isPresent(monPorteur, "defaut");
		PortefeuilleProjet addPortefeuille =  portefeuilleService.ajoutPortefeuille(portefeuille);

		monForm.getProjet().setPortefeuilleprojet(addPortefeuille);

		projetService.ajoutProjet(monForm.getProjet());

		return "redirect:/ShowAllProjetList";
	}


	// SHOW LIST PROJECT BY USER PP
	@GetMapping("/showListProjetByUser")
	public String showListProjetByUser(Model model, HttpSession session) {

		String userEmail = (String)session.getAttribute("emailUtilisateurConnecte");
		if (userEmail == null) {
			return "redirect:/showConnexionForm";
		}

		Utilisateur user = utilisateurService.chercherUtilisateurParEmail(userEmail);
		if (user == null) {
			return "ErrorSite";
		}

		// Role >>>> PorteurProjet?
		Optional<Role> role = roleService.testIsPorteurProjet(user);
		if(!role.isPresent())
			return "noFoundListProjet";

		//OUI >>>> chercher les portefeuilles lies au role
		List<Projet> projetRecup = projetService.getListProjet(role.get());

		for(Projet p : projetRecup) {
			LOGGER.info("projet : " + p);
		}

		//pour chaque liste affiche la lsite des projets  

		String message = "Bonjour ";
		String message2 = "Voici vos projets par statut";
		model.addAttribute("message", message);
		model.addAttribute("prenomUtilisateur", session.getAttribute("prenomUtilisateur"));
		model.addAttribute("message2", message2);
		model.addAttribute("listProjetByPP", projetRecup);


		return "listProjetByUser";
	}

	//Recherche 
	//Form pour une recherche simple
	@GetMapping("/showSearchBox")
	public String saisirRechercheProjetParTitre(Model model) {
		RechercheParTitreForm rechercheParTitreForm = new RechercheParTitreForm();
		model.addAttribute("rechercheParTitreForm", rechercheParTitreForm);
		return "recherche_titre";
	}
	//Recherche par titre
	@PostMapping("/rechercherProjetParTitre")
	public String rechercherProjetParTitre(@ModelAttribute("rechercheParTitreForm") RechercheParTitreForm rechercheParTitreForm,
			Model model) {
		List<Projet> listeProjets = projetService.rechercherProjetParTitre(rechercheParTitreForm.getTitre());
		if(!listeProjets.isEmpty()) {
			model.addAttribute("listeProjetTrouveParTitre", listeProjets);		
			return "listeProjets_recherche";
		}
		return "redirect:/showSearchBox";
	}
	//Form pour une recherche multicritères
	@GetMapping("/showSearchBoxMulticriteres")
	public String saisirRechercheProjetMulticriteres(Model model) {
		RechercheMulticriteresForm rechercheMultiForm = new RechercheMulticriteresForm();

		model.addAttribute("listTerritoire", territoireService.afficherAllTerritoire());
		System.out.println("Liste territoire : " + territoireService.afficherAllTerritoire());
		model.addAttribute("listTypeProjet", typeProjetService.afficherAllTypeProjet());
		model.addAttribute("rechercheMultiForm", rechercheMultiForm);
		return "recherche_multicriteres";
	}

	//Recherche multicritères
	@PostMapping("/rechercherProjetMulticriteres")
	public String rechercherProjetMulticriteres(@ModelAttribute("rechercheMultiForm") RechercheMulticriteresForm rechercheMultiForm,
			Model model) {
		List<Projet> listeProjetsmulti = projetService.rechercherProjetParCriteres(rechercheMultiForm.getTitre(),
				rechercheMultiForm.getTypeProjet().getIdTypeProjet(), rechercheMultiForm.getTerritoire().getIdTerritoire());
		System.out.println(listeProjetsmulti.size());
		if(!listeProjetsmulti.isEmpty()) {
			model.addAttribute("listeProjetsRechercheMulticriteres", listeProjetsmulti);
			return "listeProjets_rechercheMulti";
		}
		return "redirect:/showSearchBoxMulticriteres";
	}


	///////FIN GIT MASTER //////////

	//show One Projet

	@GetMapping("/showProjet/{id}")
	public String showProjet(@PathVariable (value = "id") Long id, Model model) {

		//		PresentationProjetForm form = new PresentationProjetForm();

		Projet projet = projetService.getProjetByIdNoOptional(id);
		//		form.setProjet(projet);

		//		PortefeuilleProjet portefeuilleProjet = projet.getPortefeuilleprojet();
		//		form.setPorteFeuille(portefeuilleProjet);
		//		
		//		PorteurProjet porteurProjet = projet.getPortefeuilleprojet().getPorteurprojet();
		//		form.setPorteurProjet(porteurProjet);
		//		
		//		Role role = projet.getPortefeuilleprojet().getPorteurprojet().getRole();
		//		form.setRole(role);
		//		
		//		Utilisateur utilisateur = projet.getPortefeuilleprojet().getPorteurprojet().getRole().getUtilisateur();
		//		form.setUtilisateur(utilisateur);
		//		
		//		Categorie categorie = projet.getCategorie();
		//		form.setCategorie(categorie);
		//		
		//		Territoire territoire = projet.getCategorie().getTerritoire();
		//		form.setTerritoire(territoire);
		//		
		//		TypeProjet typeProjet = projet.getCategorie().getTypeProjet();
		//		form.setTypeProjet(typeProjet);



		// document recup
		// form set doc

		model.addAttribute("projet", projet);
		//		model.addAttribute("typeProjet", typeProjet);
		//		model.addAttribute("territoire", territoire);
		//		model.addAttribute("porteFeuille", portefeuilleProjet);
		//		model.addAttribute("porteurProjet", porteurProjet);
		//		model.addAttribute("role", role);
		//		model.addAttribute("utilisateur", utilisateur);

		return "vueProjet";
	}






}

