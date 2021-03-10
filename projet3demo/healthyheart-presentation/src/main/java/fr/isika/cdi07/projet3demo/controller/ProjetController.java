package fr.isika.cdi07.projet3demo.controller;

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
import fr.isika.cdi07.projet3demo.model.TypePorteur;
import fr.isika.cdi07.projet3demo.model.TypeProjet;
import fr.isika.cdi07.projet3demo.model.TypeRole;
import fr.isika.cdi07.projet3demo.model.Utilisateur;
import fr.isika.cdi07.projet3demo.services.CategorieService;
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



	//afficher tous les projets
	@GetMapping("/ShowAllProjetList")
	public String showAllProjetList(Model model) {
		model.addAttribute("listProjet", projetService.afficherAllProjet());
		return "listProjet";
	}
	//afficher le catalogue avec juste projet statut publié
	@GetMapping("/ShowProjetListPublie")
	public String showProjetListPublie(Model model) {
		model.addAttribute("listProjetPublie", projetService.afficherProjetPublie());
		return "catalogue";
	}

	// afficher création projet (newprojet)
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
		
		Role roleOfUser = roleService.hasRole(user, TypeRole.PORTEURPROJET);

		Projet projet = new Projet();
		ProjetForm monForm = new ProjetForm();
		PorteurProjet porteurProjet = porteurProjetService.isPresent(roleOfUser);
		
		porteurProjet.setRole(roleOfUser);
		
		
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


	//enregistrer dans la base un projet with territoire et type projet
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

	//Update
	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateProjetForm(@PathVariable (value = "id") Long id, Model model) {
		// get Projet from the service
		Optional<Projet> optProjet = projetService.getProjetById(id);
		if(!optProjet.isPresent())
			return "noFoundProjet";
		Optional<Categorie> categorie = categorieService.getCategorieById(id);
		Optional<Territoire> territoire = territoireService.getTerritoireById(id);
		Optional<TypeProjet> typeProjet = typeProjetService.getTerritoireById(id);		

		// set Projet as a model attribute to pre-populate the form
		model.addAttribute("projet", optProjet);
		model.addAttribute("categorie", categorie);
		model.addAttribute("typeProjet", typeProjet);
		model.addAttribute("territoire", territoire);
		model.addAttribute("listTerritoire", territoireService.afficherAllTerritoire());
		model.addAttribute("listTypeProjet", typeProjetService.afficherAllTypeProjet());

		return "updateProjet";
	}


}

