package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Smer;
import rva.repository.SmerRepository;

@RestController
public class SmerRestController {

	@Autowired
	private SmerRepository smerRepository;
	
	@GetMapping("smer")
	public Collection<Smer> getSmerovi(){
		return smerRepository.findAll();
	}
	
	@GetMapping("smer/{id}")
	public Smer getSmer(@PathVariable("id") Integer id){
		return smerRepository.getOne(id);
	}
	
	@GetMapping("smerNaziv/{naziv}")
	public Collection<Smer> getSmerNaziv(@PathVariable("naziv") String naziv){
		return smerRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
}
