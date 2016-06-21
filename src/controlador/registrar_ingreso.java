package controlador;

import java.util.Date;
import java.util.List;

import modelo.Cuentas;
import modelo.DetalleCuentas;
import modelo.Gastos;
import modelo.Ingresos;
import modelo.Moneda;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.sun.mail.handlers.message_rfc822;

public class registrar_ingreso extends GenericForwardComposer {
	
	private Combobox cmbCuentas;
	private Doublebox txtMonto;
	private Textbox txtDescripcion;
	private Window windRegistrarIngresos;
	
	public void onCreate$windRegistrarIngresos(){
		CargarCuentas();
	}
	
	public void onClick$btnGuardar() throws Exception{
		txtDescripcion.setConstraint("no empty");
		txtMonto.setConstraint("no empty");
		Double montoActual = 0.00;
		Double montoActualaux = 0.00;
		Ingresos ingreso = new Ingresos();
		Gastos gasto = new Gastos();
		gasto = null;
		Cuentas cuenta = new Cuentas();
		List<Object[]> lista = null;
		lista = cuenta.consultarCuenta((Integer)cmbCuentas.getSelectedItem().getValue());
		if(lista.size() > 0){
			montoActual = (Double) lista.get(0)[4];
		}
		
		montoActualaux = montoActual + txtMonto.getValue();
		
		
		
		DetalleCuentas detalle = new DetalleCuentas();
		Date fecha = new Date();
		cuenta.setId((Integer)cmbCuentas.getSelectedItem().getValue());
		ingreso.setCuentas(cuenta);
		ingreso.setDescripcion(txtDescripcion.getText());
		ingreso.setEstatus("AC");
		ingreso.setMonto(txtMonto.getValue());
		ingreso.setFecha(fecha);
		ingreso.incluir(ingreso);
		
		detalle.setCuentas(cuenta);
		detalle.setDescripcion(txtDescripcion.getText());
		detalle.setFecha(fecha);
		detalle.setIngresos(ingreso);
		detalle.setGastos(gasto);
		detalle.setMontoActual(montoActualaux);
		detalle.setEstatus("AC");
		detalle.incluir(detalle);
		
		cuenta.ActualizarCampos("update Cuentas set montoActual = "+montoActualaux+" where id = "+cuenta.getId() +"");
		
		Messagebox.show("Ingreso guardado..", "Mensaje", Messagebox.OK,Messagebox.INFORMATION);
		Limpiar();
		
	}
	
	public void onClose$windRegistrarIngreso(){
		Limpiar();
		windRegistrarIngresos.detach();
	}
	
	public void CargarCuentas(){
		List <Object[]> lista = null;
		Cuentas cuenta = new Cuentas();
		lista = cuenta.consultarCuentas();
		if (lista.size() > 0) {
			int i = 0;
			while (i < lista.size()) {
				Comboitem combo = new Comboitem();
				combo.setValue((Integer)lista.get(i)[0]);
				combo.setLabel((String)lista.get(i)[2]);
				cmbCuentas.appendChild(combo);
				++i;
			}				
		} else {
			Comboitem combo = new Comboitem();
			combo.setValue(0);
			combo.setLabel("- No existen cuentas -");
			cmbCuentas.appendChild(combo);
		}
		
		
	}
	
	public void Limpiar(){
		txtDescripcion.setConstraint("");
		txtMonto.setConstraint("");
		txtDescripcion.setText("");
		txtMonto.setText("");
		
	}

}
