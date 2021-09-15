package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Student;
import rva.repository.StudentRepository;

@RestController
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;
	
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
}
