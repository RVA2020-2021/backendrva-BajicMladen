package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Grupa;
import rva.repository.GrupaRepository;

@RestController
public class GrupaRestController {

	@Autowired
	private GrupaRepository grupaRepository;
	
	@GetMapping("grupa")
	public Collection<Grupa> getGrupe(){
		return grupaRepository.findAll();
	}
	
	@GetMapping("grupa/{id}")
	public Grupa getGrupa(@PathVariable("id") Integer id) {
		return grupaRepository.getOne(id);
	}
	
	@GetMapping("grupaNaziv/{oznaka}")
	public Collection<Grupa> getProjekatNaziv(@PathVariable("oznaka") String oznaka){
		return grupaRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
}
