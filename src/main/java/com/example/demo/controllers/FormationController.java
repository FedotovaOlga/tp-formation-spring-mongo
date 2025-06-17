package com.example.demo.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Formation;
import com.example.demo.repositories.FormationRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class FormationController {
	
	private FormationRepository formationRepository;

	@GetMapping("/admin")
	public String showFormations (Model model) {
		model.addAttribute("formations", formationRepository.findAll());
		return "showFormations";
	}
	
	@GetMapping("/admin/addFormation")
	public String addFormation(Model model) {
		model.addAttribute("verbe", "Ajouter");
		model.addAttribute("action", "addFormation");
		model.addAttribute("formation", new Formation());
		return "formation";
	}
	
	@PostMapping("/admin/addFormation")
	public String addFormation(@ModelAttribute Formation formation, Model model) {
		model.addAttribute("formation", formationRepository.save(formation));
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/editFormation/{id}")
	public String editFormation(Model model, @PathVariable Integer id) {
		var result = formationRepository.findById(id);
		if (result.isPresent()) {
			model.addAttribute("verbe", "Modifier");
			model.addAttribute("action", "editFormation");
			model.addAttribute("formation", result.get());
		} else {
			model.addAttribute("formation", new Formation());
		}
		return "formation";
	}
	
	@PostMapping("/admin/editFormation")
	public String editFormation(@ModelAttribute Formation formation, Model model) {
		model.addAttribute("formation", formationRepository.save(formation));
		var result = formationRepository.findById(formation.getId());
		if (result.isPresent()) {
			formationRepository.save(formation);
		}
		return "redirect:/admin";
	}
	
	@PostMapping("/admin")
	public String showFormations(Model model, @RequestParam String nom) {
		model.addAttribute("formations", formationRepository.findByNomContaining(nom));
		return "showFormations";
	}
	
	@GetMapping("/admin/sortFormationsByName")
	public String sortFormationsByName(Model model) {
		model.addAttribute("formations", formationRepository.findAll(Sort.by("nom")));
		return "showFormations";
	}
	
	@GetMapping("/admin/sortFormationsByDate")
	public String sortFormationsByDate(Model model) {
		model.addAttribute("formations", formationRepository.findAll(Sort.by("dateDebut")));
		return "showFormations";
	}
	

}
