package modelo;

// Generated 10/06/2016 12:39:05 PM by Hibernate Tools 3.4.0.CR1

import general.ManejarEntidades;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Moneda generated by hbm2java
 */
public class Moneda extends ManejarEntidades implements java.io.Serializable {

	private int id;
	private String nombre;
	private String sigla;
	private String estatus;
	private Set cuentases = new HashSet(0);

	public Moneda() {
	}

	public Moneda(int id) {
		this.id = id;
	}

	public Moneda(int id, String nombre, String sigla, String estatus,
			Set cuentases) {
		this.id = id;
		this.nombre = nombre;
		this.sigla = sigla;
		this.estatus = estatus;
		this.cuentases = cuentases;
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

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Set getCuentases() {
		return this.cuentases;
	}

	public void setCuentases(Set cuentases) {
		this.cuentases = cuentases;
	}
	
	public List<Object[]> consultarMonedas(){
		List<Object[]> lista = null;
		String sql = "";
		try{
			sql = "select a.id, a.nombre, a.sigla from Moneda a where a.estatus='AC' ";
			lista = (List<Object[]>) this.ConsultarLista(sql);
		} catch (Exception ex) {
			System.out.println("ERROR en consultarMonedas "+ex.getMessage());
		}
		return lista;
		
	}
	
	

}
