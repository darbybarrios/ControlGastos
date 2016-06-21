package controlador;

import java.text.DecimalFormat;
import java.util.List;

import modelo.Cuentas;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

public class cuentas extends GenericForwardComposer{
	private Listbox listaCuentas;
	DecimalFormat formato = new DecimalFormat("###,###,###,###,##0.00");
	public void onCreate$windCuentas(){
		CargarLista();
		
	}
	
	public void onClick$btnNuevo(){
		final Window ventana = (Window) Executions.createComponents("registrar_cuenta.zul", null, null);
		ventana.setMaximizable(false);
		ventana.setClosable(true);
		ventana.doModal();
		LimpiarList(listaCuentas);
		CargarLista();
	}
	
	public void CargarLista(){
		Cuentas cuentas = new Cuentas();
		List<Object[]> lista = null;
		lista = cuentas.consultarCuentas();
		if(lista.size()>0){
			for(int i=0;i<lista.size();i++){
				final Listitem item = new Listitem();
				item.appendChild(new Listcell(lista.get(i)[0].toString()));
				item.appendChild(new Listcell(lista.get(i)[1].toString()));
				item.appendChild(new Listcell(lista.get(i)[2].toString()));
				item.appendChild(new Listcell(formato.format(lista.get(i)[3])));
				listaCuentas.appendChild(item);
			}
		}	
		
	}
	
	public void onSelect$listaCuentas(){
		if(listaCuentas.getSelectedItem() != null){
			Integer id = null;
			List hijo = listaCuentas.getSelectedItem().getChildren();
			id = Integer.valueOf(((Listcell) hijo.get(0)).getLabel());
			final Window ventana = (Window) Executions.createComponents("consultar_cuenta.zul", null, null);
			ventana.setAttribute("id",id,true);
			ventana.setMaximizable(false);
			ventana.setClosable(true);
			ventana.doModal();
			
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
