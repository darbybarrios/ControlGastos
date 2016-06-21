package controlador;

import java.util.Date;
import java.util.List;

import modelo.Cuentas;
import modelo.DetalleCuentas;
import modelo.Gastos;
import modelo.Ingresos;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class registrar_gasto extends GenericForwardComposer{
	private Combobox cmbCuentas;
	private Doublebox txtMonto;
	private Textbox txtDescripcion;
	private Window windRegistrarGasto;
	
	public void onCreate$windRegistrarGasto(){
		CargarCuentas();
	}
	
	public void onClose$windRegistrarGasto(){
		Limpiar();
		windRegistrarGasto.detach();
	}
	
	public void onClick$btnGuardar() throws Exception{
		txtDescripcion.setConstraint("no empty");
		txtMonto.setConstraint("no empty");
		Double montoActual = 0.00;
		Double montoActualaux = 0.00;
		Ingresos ingreso = new Ingresos();
		Gastos gasto = new Gastos();
		ingreso = null;
		Cuentas cuenta = new Cuentas();
		List<Object[]> lista = null;
		lista = cuenta.consultarCuenta((Integer)cmbCuentas.getSelectedItem().getValue());
		if(lista.size() > 0){
			montoActual = (Double) lista.get(0)[4];
		}
		
		montoActualaux = montoActual - txtMonto.getValue();
		
		
		
		DetalleCuentas detalle = new DetalleCuentas();
		Date fecha = new Date();
		cuenta.setId((Integer)cmbCuentas.getSelectedItem().getValue());
		gasto.setCuentas(cuenta);
		gasto.setDescripcion(txtDescripcion.getText());
		gasto.setEstatus("AC");
		gasto.setMonto(txtMonto.getValue());
		gasto.setFecha(fecha);
		gasto.incluir(gasto);
		
		detalle.setCuentas(cuenta);
		detalle.setDescripcion(txtDescripcion.getText());
		detalle.setFecha(fecha);
		detalle.setIngresos(ingreso);
		detalle.setGastos(gasto);
		detalle.setMontoActual(montoActualaux);
		detalle.setEstatus("AC");
		detalle.incluir(detalle);
		
		cuenta.ActualizarCampos("update Cuentas set montoActual = "+montoActualaux+" where id = "+cuenta.getId() +"");
		
		Messagebox.show("Gasto guardado..", "Mensaje", Messagebox.OK,Messagebox.INFORMATION);
		Limpiar();
		
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
