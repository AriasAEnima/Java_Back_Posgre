package julianarias.smartjsp_prueba.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import julianarias.smartjsp_prueba.exception.ResourceNotFoundException;
import julianarias.smartjsp_prueba.exception.ViolationDataIntegrityException;
import julianarias.smartjsp_prueba.model.User;
import julianarias.smartjsp_prueba.repository.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/service")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
		
	@GetMapping("users")
	public List<User> getAllUser(){
		System.out.println(this.userRepository.findByEstado(true).get(0).toString());
		return this.userRepository.findByEstado(true);
	}
	
	@GetMapping("/users/{documento}")
	public ResponseEntity<User> getUserByDocumento(@PathVariable(value="documento") Long documento) throws ResourceNotFoundException  {
		 User usuario = userRepository.findByDocumentoAndEstado(documento,true)
				 .orElseThrow(() -> new ResourceNotFoundException ("Usuario no encontrado con documento : " + documento));
		        return ResponseEntity.ok().body(usuario);
	}
	
	@GetMapping("filtro")
	public List<User> getUserFiltered(@RequestBody User userDetails){
		System.out.println(userDetails.getNombre()+" "+userDetails.getApellido()+" "+userDetails.getDocumento());
		return this.userRepository.findUserByNombreAndApellidoAndDocumento(userDetails.getNombre(), userDetails.getApellido(), userDetails.getDocumento());
	}
	
	@PostMapping("newuser")
	public ResponseEntity<User> createUser(@RequestBody User usuario) throws ViolationDataIntegrityException {			
		usuario.setFecha(new Date(System.currentTimeMillis()));
		usuario.setEstado(true);
		return saveUser(usuario);
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id,  @RequestBody User userDetails) throws ResourceNotFoundException, ViolationDataIntegrityException{
		 User usuario = userRepository.findById(id)
		          .orElseThrow(() -> new ResourceNotFoundException ("Usuario no encontrado con id : " + id));
		updateUserByDetails(usuario,userDetails);
		return saveUser(usuario);
		
    }
	
	private ResponseEntity<User> saveUser(User usuario) throws ViolationDataIntegrityException{
		try {
			User newuser= userRepository.save(usuario);
			return ResponseEntity.ok(newuser);
		}catch(DataIntegrityViolationException   e) {
			throw new ViolationDataIntegrityException(ViolationDataIntegrityException.DATA_ERROR);
		}		
	}
	
	@DeleteMapping("users/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id)
	     throws ResourceNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id :" + id));
		user.setEstado(false);
		userRepository.save(user);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}
	

	private void updateUserByDetails(User usuario, User userDetails) {
		 usuario.setNombre(userDetails.getNombre());
		 usuario.setApellido(userDetails.getApellido());
		 usuario.setTipoDocumento(userDetails.getTipoDocumento());
		 usuario.setEmail(userDetails.getEmail());
		 usuario.setFecha(new Date(System.currentTimeMillis()));		 
		 if(userDetails.getTelefono()!="") {
			 usuario.setTelefono(userDetails.getTelefono());			 
		 }		 
		 if(userDetails.getDocumento()!=null) {
			 usuario.setDocumento(userDetails.getDocumento());
		 }		 
		 if(userDetails.getObservaciones()!="") {
			 usuario.setObservaciones(userDetails.getObservaciones());
		 }		 
		 if(userDetails.getNacionalidad()!="") {
			 usuario.setNacionalidad(userDetails.getNacionalidad());
		 }		 
		
	}	
	
	


}
