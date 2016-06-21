package controlador;

import java.text.DecimalFormat;
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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class consultar_cuenta extends GenericForwardComposer {
	private Combobox cmbMonedas;
	private Window windConsultarCuenta;
	private Integer id;
	private Textbox txtNumeroCuenta;
	private Textbox txtBanco;
	private Doublebox txtMontoInicial;
	private Listbox listaMontos;
	DecimalFormat formato = new DecimalFormat("###,###,###,###,##0.00");
	public void onCreate$windConsultarCuenta(){
		id = (Integer) windConsultarCuenta.getAttribute("id",true);
		CargarMonedas();
		ConsultarCuenta();
		CargarMontos();
		
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
	
	public void ConsultarCuenta(){
		Cuentas cuenta = new Cuentas();
		List<Object[]> lista = null;
		lista = cuenta.consultarCuenta(id);
		if(lista.size() > 0){
			txtNumeroCuenta.setText(lista.get(0)[0].toString());
			txtBanco.setText(lista.get(0)[1].toString());
			SeleccionItem(cmbMonedas,lista.get(0)[3].toString());
			
		}
		
	}
	
	public void CargarMontos(){
		DetalleCuentas detalle = new DetalleCuentas();
		Ingresos ingreso = new Ingresos();
		Gastos gasto = new Gastos();
		List<Object[]> lista = null;
		lista = detalle.consultarDetalles(id);
		if(lista.size() > 0){
			for(int i=0;i<lista.size();i++){
				String ingre = "0,00";
				String gast = "0,00";
				List<Object[]> lista1 = null;
				List<Object[]> lista2 = null;
			
				final Listitem item = new Listitem();
				item.appendChild(new Listcell(lista.get(i)[0].toString()));
				item.appendChild(new Listcell(lista.get(i)[1].toString()));
				if(!lista.get(i)[2].toString().equals("0")){
					lista1 = ingreso.consultarIngreso((Integer)lista.get(i)[2]);
					item.appendChild(new Listcell(formato.format(lista1.get(0)[1])));					
				}else{
					item.appendChild(new Listcell(ingre));
				}
				if(!lista.get(i)[3].toString().equals("0")){
					lista2 = gasto.consultarGasto((Integer)lista.get(i)[3]);
					item.appendChild(new Listcell(formato.format(lista2.get(0)[1])));
					
				}else{
					item.appendChild(new Listcell(gast));
				}
				
				
				item.appendChild(new Listcell(formato.format(lista.get(i)[4])));
				listaMontos.appendChild(item);
			}
			
		}
		
	}
	
	public void SeleccionItem(Combobox combo,String valor) {
		
        if (valor == null) {
  			combo.setSelectedIndex(-1);
  		} else {
  			for (int i = 0; combo.getItemCount()>i; ++i) {
  				Comboitem item = combo.getItemAtIndex(i);
				if (valor.equals(item.getValue().toString())) {  					
					combo.setSelectedIndex(i);
  					break;
  				}
  			}
  		}
  	}
	
	

}


