package controlador;

import java.text.DecimalFormat;
import java.util.List;

import modelo.Cuentas;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

public class tablero extends GenericForwardComposer{
	private Label saldoDolares;
	private Label saldoBolivares;
	private Label efectivoDolares;
	private Label efectivoBolivares;
	private Label totalDolares;
	private Label totalBolivares;
	private Double sDolares;
	private Double sBolivares;
	private Double eBolivares;
	private Double eDolares;
	DecimalFormat formato = new DecimalFormat("###,###,###,###,##0.00");
	
	public void onCreate$windTablero(){
		totalDolares.setValue(ConsultarSaldoDolares());
		totalBolivares.setValue(ConsultarSaldoBolivares());
		efectivoDolares.setValue(ConsultarEfectivoDolares());
		efectivoBolivares.setValue(ConsultarEfectivoBolivares());
		saldoDolares.setValue(CalcularSaldoDolares());
		saldoBolivares.setValue(CalcularSaldoBolivares());
		
		
		
	}
	
	public String ConsultarSaldoBolivares(){
		
		Cuentas cuentas = new Cuentas();
		List<Object[]> lista = null;
		lista = cuentas.saldoBolivares();
		String saldo= "0,00";
		if(lista.size() > 0){
			saldo = formato.format(lista.get(0)[1]);
			sBolivares = (Double) lista.get(0)[1];
			
		}
		
		return saldo;
		
	}
	
	public String ConsultarSaldoDolares(){
		Cuentas cuentas = new Cuentas();
		List<Object[]> lista = null;
		lista = cuentas.saldoDolares();
		String saldo= "0,00";
		if(lista.size() > 0){
			saldo = formato.format(lista.get(0)[1]);
			sDolares = (Double) lista.get(0)[1];
			
		}
		
		return saldo;
		
	}
	
	public String ConsultarEfectivoDolares(){
		Cuentas cuentas = new Cuentas();
		List<Object[]> lista = null;
		lista = cuentas.efectivoDolares();
		String saldo= "0,00";
		if(lista.size() > 0){
			saldo = formato.format(lista.get(0)[1]);
			eDolares = (Double) lista.get(0)[1];
		}
		
		return saldo;
	}
	
	public String ConsultarEfectivoBolivares(){
		Cuentas cuentas = new Cuentas();
		List<Object[]> lista = null;
		lista = cuentas.efectivoBolivares();
		String saldo= "0,00";
		if(lista.size() > 0){
			saldo = formato.format(lista.get(0)[1]);
			eBolivares = (Double) lista.get(0)[1];
		}
		
		return saldo;
	}
	
	public String CalcularSaldoBolivares(){
		String saldo = "0,00";
		Double total = 0.00;
		if(sBolivares != 0){
			total = sBolivares - eBolivares;
			saldo = formato.format(total);
		}else{
			saldo = formato.format(eBolivares);
		}
		
		return saldo;
		
	}
	
	public String CalcularSaldoDolares(){
		String saldo = "0,00";
		Double total = 0.00;
		if(sDolares != 0){
			total = sDolares - eDolares;
			saldo = formato.format(total);
		}else{
			saldo = formato.format(eDolares);
		}
		
		return saldo;
		
	}
}
