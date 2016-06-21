package general;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import modelo.Usuarios;






public class Credenciales implements Serializable{
	private Integer id_usuario;
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	private String usuario;
	private String nombre;
	private String apellido;
	private String clave;
	private String indentificador;
	private String tipo;
	private String estatus;
	private String destino;
	private String correo;
	private Integer id_destino;

	public Credenciales() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void jbInit() throws Exception {
		
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getIndentificador() {
		return indentificador;
	}
	public void setIndentificador(String indentificador) {
		this.indentificador = indentificador;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Integer getId_destino() {
		return id_destino;
	}
	public void setId_destino(Integer id_destino) {
		this.id_destino = id_destino;
	}
	
	public Credenciales ValidarLogin(String usuario, String clave/*,HttpServletRequest servletRequest*/)throws Exception{
		try{
			List<Object[]> listaUsuario = null;			
			Credenciales Credenciales = new Credenciales();
			Usuarios objUsuario = new Usuarios();
			
				listaUsuario = objUsuario.consultarUsuario(usuario,clave);
			
			
			if (!(listaUsuario == null)) {				
									
					Credenciales.setId_usuario((Integer) listaUsuario.get(0)[0]);
					Credenciales.setUsuario(usuario);
					Credenciales.setNombre((String) listaUsuario.get(0)[1]);
					Credenciales.setApellido((String) listaUsuario.get(0)[2]);
					
				
			}else{
				throw new Exception("Usuario o Clave incorrecta");
			}
			return Credenciales;
		}catch(Exception ex){
			throw ex;
		}
	
		
	}
}
