package controlador;

import java.text.DecimalFormat;
import java.util.List;

import modelo.Gastos;
import modelo.Ingresos;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class gastos extends GenericForwardComposer{
	
	private Listbox listaGastos;
	DecimalFormat formato = new DecimalFormat("###,###,###,###,##0.00");
	
	public void onCreate$windGastos(){
		CargarGastos();
	}
	
	public void onClick$btnNuevo(){
		Window ventana = (Window) Executions.createComponents("registrar_gasto.zul", null, null);
		ventana.setMaximizable(false);
		ventana.setClosable(true);
		ventana.doModal();
		LimpiarList(listaGastos);
		CargarGastos();
	}
	
	public void CargarGastos(){
		Gastos gastos = new Gastos();
		List<Object[]> lista = null;
		
		lista = gastos.consultarGastos();
		if(lista.size() > 0){
			for(int i=0;i<lista.size();i++){
				final Listitem item = new Listitem();
				item.appendChild(new Listcell(lista.get(i)[1].toString()));
				item.appendChild(new Listcell(lista.get(i)[2].toString()));
				item.appendChild(new Listcell(lista.get(i)[3].toString()));
				item.appendChild(new Listcell(formato.format(lista.get(i)[4])));
				listaGastos.appendChild(item);
			}
			
		}
		
		
		
	}
	
	void LimpiarList(Listbox lista) {
		int n = 0;
		try{
		while (lista.getItems().size() > n) {
			lista.removeItemAt(n);
		}
		lista.setRows(0);
		}
		catch (Exception e){
			System.out.println(" error limpiando lista "+e);
		}
	}	

}
