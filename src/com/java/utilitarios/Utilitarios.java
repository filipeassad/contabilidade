package com.java.utilitarios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Utilitarios {
	
	public static String converteDoubleToString(Double valor){		
		
		
		String[] vetor = new BigDecimal(valor).toString().split("\\.");
		List<String> lista = new ArrayList<>();		
		lista.add("");
		int contador = 0;
		for(int i = vetor[0].length() - 1; i > -1; i--){
			contador++;
			String atualizando = vetor[0].charAt(i) + lista.remove(lista.size() -1);
			lista.add(atualizando);
			
			if(contador == 3){
				lista.add("");
				contador = 0;
			}			
		}
		
		String strFinal = lista.remove(0);
		for(String n : lista){
			strFinal = n + "." + strFinal;
		}
		
		if(vetor.length > 1){
			return strFinal + "," + vetor[1];
		}else{
			return strFinal + ",00";
		}		
		
	}
	
	public static String removeMascara(String valor){
		
		String vtValor[] = valor.split("\\.");
		
		if(vtValor.length > 0){
			valor = "";
			for(int i = 0; i< vtValor.length; i++){
				valor = valor + vtValor[i] ;
			}
		}
		
		vtValor = valor.split(",");
		if(vtValor.length > 0){
			valor = vtValor[0] + "." + vtValor[1];			
		}
		
		return valor;
	}
	
}
