package julianarias.smartjsp_prueba.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import julianarias.smartjsp_prueba.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
		
	
	public Optional<User> findByDocumentoAndEstado(Long documento,boolean estado);
	
	@Query("SELECT c FROM usuarios c WHERE (:nombre is null or LOWER(c.nombre) like LOWER(CONCAT('%', cast(:nombre as string), '%'))) "
			+ "and (:apellido is null or LOWER(c.apellido) like LOWER(CONCAT('%', cast(:apellido as string), '%'))) and (:documento is null or c.documento = :documento) and c.estado=TRUE")
	List<User> findUserByNombreAndApellidoAndDocumento(@Param("nombre") String nombre, @Param("apellido") String apellido , @Param("documento") Long documento);
	
	
	public List<User> findByEstado(Boolean estado);
}
