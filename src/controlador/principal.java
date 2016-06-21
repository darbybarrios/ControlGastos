package controlador;

import java.util.Locale;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;

public class principal extends GenericForwardComposer {
public Include pagina;

	public void onCreate$windPrincipal(){
		Locale formato = new Locale("es", "VE");
		Locale.setDefault(formato);
	}
	
	public void onClick$menuTablero(){
	
		pagina.setSrc("tablero.zul");  
	}
	
	public void onClick$menuCuentas(){
		
		pagina.setSrc("cuentas.zul");  
	}
	
	public void onClick$menuIngreso(){
		
		pagina.setSrc("ingresos.zul");  
	}
	
	public void onClick$menuGastos(){
		
		pagina.setSrc("gastos.zul");  
	}

}
