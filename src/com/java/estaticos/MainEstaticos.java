package com.java.estaticos;

import com.java.interfaces.MainInterface;
import com.java.model.Lancamento;

public class MainEstaticos {

	private static MainInterface mainInterface;
	private static Lancamento lancamento;
	
	public static MainInterface getMainInterface() {
		return mainInterface;
	}
	public static void setMainInterface(MainInterface mainInterface) {
		MainEstaticos.mainInterface = mainInterface;
	}
	public static Lancamento getLancamento() {
		return lancamento;
	}
	public static void setLancamento(Lancamento lancamento) {
		MainEstaticos.lancamento = lancamento;
	}	
	
}
