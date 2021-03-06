package modelo;

import general.ManejarEntidades;

import java.util.List;

// Generated 10/06/2016 12:36:04 PM by Hibernate Tools 3.4.0.CR1

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios extends ManejarEntidades implements java.io.Serializable {

	private int id;
	private String nombre;
	private String apellido;
	private String usuario;
	private String clave;
	private String estatus;

	public Usuarios() {
	}

	public Usuarios(int id) {
		this.id = id;
	}

	public Usuarios(int id, String nombre, String apellido, String usuario,
			String clave, String estatus) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.clave = clave;
		this.estatus = estatus;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	public List<Object[]> consultarUsuario(String usuario, String clave){
		List<Object[]> lista = null;
		String sql = "";
		try{
			sql = "select a.id, a.nombre, a.apellido from Usuarios a where a.usuario = '"+usuario+"' and a.clave = '"+clave+"'";
			lista = (List<Object[]>) this.Consultar(sql);
		} catch (Exception ex) {
			System.out.println("ERROR en consultarUsuario "+ex.getMessage());
		}
		return lista;
		
	}

}
