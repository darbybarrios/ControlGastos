package controlador;

import general.Credenciales;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class index extends GenericForwardComposer {
	
	private Textbox txtUsuario;
	private Textbox txtContrasena;
	private Textbox id;
	Credenciales Credenciales = new Credenciales();
	public void onClick$btnEntrar(){
		ValidarLogin();
	}
	
	public void ValidarLogin(){    	
        txtUsuario.setConstraint("no empty");
        txtContrasena.setConstraint("no empty");
        txtUsuario.getValue();
        txtContrasena.getValue();
        try{
        	//HttpServletRequest servletRequest= (HttpServletRequest) Executions.getCurrent().getNativeRequest();
    	    Credenciales = Credenciales.ValidarLogin(txtUsuario.getText(),txtContrasena.getText()/*,servletRequest*/);                     
    	    session.setAttribute("Credenciales",Credenciales);
    	    
    	    Executions.getCurrent().sendRedirect("principal.zul");        	
        }
        catch (Exception ex){
              if (ex.getMessage()==null){
            	  Messagebox.show("El usuario"+" " + "''"+txtUsuario.getText()+"''"+" "+"no tiene permisos para ingresar al sistema", "Error", Messagebox.OK,Messagebox.ERROR);
                  
              }else{
            	  Messagebox.show(ex.getMessage(), "Error", Messagebox.OK,Messagebox.ERROR);
            	  
              }
        }  
        
        
        
	}   

}
