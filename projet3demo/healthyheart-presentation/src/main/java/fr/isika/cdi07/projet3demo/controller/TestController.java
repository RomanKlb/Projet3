package fr.isika.cdi07.projet3demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.isika.cdi07.projet3demo.services.DonService;

@Controller
public class TestController {
	
	@Autowired 
	private DonService donService;

	@GetMapping("/faireUnDon")
	public String ajouterDonMonetaire(Model model) {
		model.addAttribute("count", donService.compterDonsMonetaire());
		model.addAttribute("donMonetaireList", donService.afficherDonsMonetaires());
		return "faireUnDon";
	}

}
