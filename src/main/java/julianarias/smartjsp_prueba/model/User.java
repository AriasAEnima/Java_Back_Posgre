package julianarias.smartjsp_prueba.model;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  

@Entity(name="usuarios")
@Table(name="usuarios")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(name= "nombre")
	private String nombre;
	
	@Column(name= "apellido")
	private String apellido;
	
	@Column(name= "nacionalidad")
	private String nacionalidad;
	
	@Column(name= "tipo_documento")
	private String tipoDocumento;
	
	@Column(name= "documento")
	private Long documento;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "telefono")
	private String telefono;
	
	@Column(name= "observaciones")
	private String observaciones;
	
	@Column(name= "fecha")
	private Date fecha;
	
	@Column(name= "estado")
	private boolean estado;	
		
	public User() {
		super();
	}	
	
		

	public User(long id, String nombre, String apellido, String nacionalidad, String tipoDocumento, Long documento,
			String email, String telefono, String observaciones, Date fecha, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.email = email;
		this.telefono = telefono;
		this.observaciones = observaciones;
		this.fecha = fecha;		
		this.estado = estado;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getDocumento() {
		return documento;
	}	
	
	public void setDocumento(Long documento) {
		this.documento = documento;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "ID: "+id+", Nombre: "+nombre+", Apellido: "+apellido+", Tipo Documento: "+tipoDocumento+", Documento: "+documento+", Email: "+email+
				", Telefono: "+telefono+", Observaciones: "+observaciones+", Fecha: "+fecha+", Estado: "+estado;
	}
	

}
