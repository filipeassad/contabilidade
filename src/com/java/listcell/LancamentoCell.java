package com.java.listcell;

import com.java.interfaces.LancamentosInterface;
import com.java.item.LancamentoItem;
import com.java.model.Lancamento;

import javafx.scene.control.ListCell;

public class LancamentoCell extends ListCell<Lancamento>{

	private LancamentosInterface lancamentosInterface;

	public LancamentoCell(LancamentosInterface lancamentosInterface) {
		super();
		this.lancamentosInterface = lancamentosInterface;
	}
	
	@Override
	protected void updateItem(Lancamento item, boolean empty) {
		super.updateItem(item, empty);
		if(item != null){
			LancamentoItem lancamentoItem = new LancamentoItem(lancamentosInterface);
			lancamentoItem.setInfo(item);
			setGraphic(lancamentoItem.getBox());
		}
	}
	
}
