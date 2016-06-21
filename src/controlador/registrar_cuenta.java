package controlador;

import java.util.List;

import modelo.Cuentas;
import modelo.Moneda;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class registrar_cuenta extends GenericForwardComposer{
	private Window windRegistrarCuenta;
	private Combobox cmbMonedas;
	private Textbox txtNumeroCuenta;
	private Textbox txtBanco;
	private Doublebox txtMontoInicial;
	
	public void onCreate$windRegistrarCuenta(){
		CargarMonedas();
	}
	
	public void onClick$btnGuardar(){		
		try {
			Guardar();
			Cerrar();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK,Messagebox.ERROR);
		}
		
		
	}
	
	public void CargarMonedas(){
		List <Object[]> lista = null;
		Moneda moneda = new Moneda();
		lista = moneda.consultarMonedas();
		if (lista.size() > 0) {
			int i = 0;
			while (i < lista.size()) {
				Comboitem combo = new Comboitem();
				combo.setValue((Integer)lista.get(i)[0]);
				combo.setLabel((String)lista.get(i)[1]);
				cmbMonedas.appendChild(combo);
				++i;
			}				
		} else {
			Comboitem combo = new Comboitem();
			combo.setValue(0);
			combo.setLabel("- No existen monedas -");
			cmbMonedas.appendChild(combo);
		}
		
		
	}
	
	public void Guardar() throws Exception{
		txtNumeroCuenta.setConstraint("no empty");
		txtBanco.setConstraint("no empty");
		txtMontoInicial.setConstraint("no empty");
		if(cmbMonedas.getText().equals("")){
			try {
				throw new Exception("Elija una Moneda");
			} catch (Exception e) {
				throw e;
			}
		}
		
		Cuentas cuenta = new Cuentas();
		Moneda moneda = new Moneda();
		
		cuenta.setNumeroCuenta(txtNumeroCuenta.getText());
		cuenta.setBanco(txtBanco.getText());
		cuenta.setMontoInicial(txtMontoInicial.getValue());
		cuenta.setMontoActual(txtMontoInicial.getValue());
		moneda.setId((Integer)cmbMonedas.getSelectedItem().getValue());
		cuenta.setMoneda(moneda);
		cuenta.setEstatus("AC");
		cuenta.incluir(cuenta);
		
		
		
	}
	
	public void Cerrar(){
		txtNumeroCuenta.setConstraint("");
		txtBanco.setConstraint("");
		txtMontoInicial.setConstraint("");
		txtNumeroCuenta.setText("");
		txtBanco.setText("");
		txtMontoInicial.setText("");
		windRegistrarCuenta.detach();
	}
	
	
	

}
