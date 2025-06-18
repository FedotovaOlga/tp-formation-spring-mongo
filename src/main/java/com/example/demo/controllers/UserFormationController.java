package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Formation;
import com.example.demo.entities.User;
import com.example.demo.repositories.FormationRepository;
import com.example.demo.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserFormationController {
	
	private FormationRepository formationRepository;
	private UserRepository userRepository;
	private MongoTemplate mongoTemplate;

	@GetMapping("/user")
	public String showFormations (Model model) {
		model.addAttribute("formations", formationRepository.findAll());
		User user = userRepository.findByNomUtilisateur("user");
		saveStatistics(user, "consulter", "liste de formations");
		return "userShowFormations";
	}
	
	@PostMapping("/user")
	public String showFormations(Model model, @RequestParam String nom) {
		model.addAttribute("formations", formationRepository.findByNomContaining(nom));
		User user = userRepository.findByNomUtilisateur("user");
		saveStatistics(user, "rechercher", nom);
		return "userShowFormations";
	}
	
	@GetMapping("/user/sortFormationsByName")
	public String sortFormationsByName(Model model) {
		model.addAttribute("formations", formationRepository.findAll(Sort.by("nom")));
		return "userShowFormations";
	}
	
	@GetMapping("/user/sortFormationsByDate")
	public String sortFormationsByDate(Model model) {
		model.addAttribute("formations", formationRepository.findAll(Sort.by("dateDebut")));
		return "userShowFormations";
	}
	
	@PostMapping("/user/register/{id}")
	public String register(Model model, @PathVariable Integer id) {
		Formation formation = formationRepository.findById(id).orElse(null);
		if (formation == null) {
			model.addAttribute("formations", List.of());
			return "registerList";
		}
		
		User user = userRepository.findByNomUtilisateur("user");
		
		if (!user.getFormations().contains(formation)) {
			user.getFormations().add(formation);
			userRepository.save(user);
		}
		
		model.addAttribute("formations", user.getFormations());
		return "registerList";
	}
	
	@GetMapping("/user/register")
	public String register(Model model) {
		User user = userRepository.findByNomUtilisateur("user");
		model.addAttribute("formations", user.getFormations());
		return "registerList";
	}
	
	private void saveStatistics(User user, String typeAction, Object value) {
		Document doc = new Document("nom", user.getNom())
				.append("prenom", user.getPrenom())
				.append(typeAction, value)
				.append("date_operation", LocalDateTime.now());
		System.out.println(mongoTemplate.insert(doc, "statistics"));
				
	}

}
