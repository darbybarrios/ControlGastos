package modelo;

// Generated 13/06/2016 12:31:36 PM by Hibernate Tools 3.4.0.CR1

import general.ManejarEntidades;

import java.util.Date;
import java.util.List;

/**
 * DetalleCuentas generated by hbm2java
 */
public class DetalleCuentas extends ManejarEntidades implements java.io.Serializable {

	private int id;
	private Ingresos ingresos;
	private Gastos gastos;
	private Cuentas cuentas;
	private Date fecha;
	private Double montoActual;
	private String estatus;
	private String descripcion;

	public DetalleCuentas() {
	}

	public DetalleCuentas(int id) {
		this.id = id;
	}

	public DetalleCuentas(int id, Ingresos ingresos, Gastos gastos,
			Cuentas cuentas, Date fecha, Double montoActual, String estatus,
			String descripcion) {
		this.id = id;
		this.ingresos = ingresos;
		this.gastos = gastos;
		this.cuentas = cuentas;
		this.fecha = fecha;
		this.montoActual = montoActual;
		this.estatus = estatus;
		this.descripcion = descripcion;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ingresos getIngresos() {
		return this.ingresos;
	}

	public void setIngresos(Ingresos ingresos) {
		this.ingresos = ingresos;
	}

	public Gastos getGastos() {
		return this.gastos;
	}

	public void setGastos(Gastos gastos) {
		this.gastos = gastos;
	}

	public Cuentas getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(Cuentas cuentas) {
		this.cuentas = cuentas;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMontoActual() {
		return this.montoActual;
	}

	public void setMontoActual(Double montoActual) {
		this.montoActual = montoActual;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Object[]> consultarDetalles(Integer id){
		List<Object[]> lista = null;
		String sql = "";
		try{
			sql = "select a.fecha, a.descripcion, coalesce(a.ingresos.id,0), coalesce(a.gastos.id,0), a.montoActual from DetalleCuentas a where a.cuentas.id="+id+" and a.estatus='AC' order by a.id desc";
			lista = (List<Object[]>) this.ConsultarLista(sql);
		} catch (Exception ex) {
			System.out.println("ERROR en consultarDetalles "+ex.getMessage());
		}
		return lista;
		
	}
}
