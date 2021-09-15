package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Grupa;
import rva.jpa.Student;
import rva.repository.GrupaRepository;
import rva.repository.StudentRepository;

@CrossOrigin
@RestController
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private GrupaRepository grupaRepository;
	
	@GetMapping("student")
	public Collection<Student> getStudente(){
		return studentRepository.findAll();
	}
	
	@GetMapping("student/{id}")
	public Student getStudent(@PathVariable("id") Integer id){
		return studentRepository.getOne(id);
	} 
	
	
	@GetMapping("studentIme/{ime}")
	public Collection<Student> getStudentIme(@PathVariable("ime") String ime){
		return studentRepository.findByImeContainingIgnoreCase(ime);
	}
	
	@GetMapping("studentPrezime/{prz}")
	public Collection<Student> getStudentPrezime(@PathVariable("prz") String prz){
		return studentRepository.findByPrezimeContainingIgnoreCase(prz);
	}
	
	@GetMapping("studentByGrupaID/{id}")
	public Collection<Student> getStudentPoGrupi(@PathVariable("id") Integer id){
		Grupa g=grupaRepository.getOne(id);
		return studentRepository.findByGrupa(g);
	}
	
	@PostMapping("student")
	public ResponseEntity<Student> insertStudent(@RequestBody Student student){
		if(!studentRepository.existsById(student.getId())) {
			studentRepository.save(student);
			return new ResponseEntity<Student>(HttpStatus.OK);
		}
		return new ResponseEntity<Student>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("student")
	public ResponseEntity<Student> upDateStudent(@RequestBody Student student){
		if(!studentRepository.existsById(student.getId())) 
			return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
		studentRepository.save(student);
		return new ResponseEntity<Student>(HttpStatus.OK);
	}
	
	@DeleteMapping("student/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id")  Integer id){
		if(!studentRepository.existsById(id)) {
			return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
		}		
		studentRepository.deleteById(id);
		return new ResponseEntity<Student>(HttpStatus.OK);
	}
	
}
