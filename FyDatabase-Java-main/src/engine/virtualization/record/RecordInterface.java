package engine.virtualization.record;

import java.math.BigInteger;

public class RecordInterface{

	private RecordInfoExtractor infoExtraction;
	public RecordInterface(RecordInfoExtractor extraction){
		this.infoExtraction=extraction;
	}

	/*
	 * Atualiza a referencia da primary key na tabela de referencias do manipulador;
	 * N�o � ncess�rio a implementa��o de uma l�gica dentro dessa fun��o
	 * mas pode ser utilizada por otmizadores e cache para busca futura
	 */
	public void updeteReference(BigInteger pk,long key){

	}

	public RecordInfoExtractor getExtractor(){
		return infoExtraction;
	}

}
