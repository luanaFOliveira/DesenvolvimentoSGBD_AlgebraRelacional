package engine.virtualization.record.manager;

import java.math.BigInteger;
import java.util.List;

import engine.file.FileManager;
import engine.file.buffers.BlockBuffer;
import engine.virtualization.record.Record;
import engine.virtualization.record.RecordInfoExtractor;
import engine.virtualization.record.RecordStream;

public abstract class RecordManager{
	
	protected FileManager fileManager;
	protected RecordInfoExtractor recordInterface;
	
	public RecordManager(FileManager fm, RecordInfoExtractor ri) {
		this.fileManager=fm;
		this.recordInterface=ri;
	}

	protected BlockBuffer getBlockBuffer() {
		return fileManager.getBuffer();
	}
	protected RecordInfoExtractor getRecordInterface() {
		return recordInterface;
	}
	protected FileManager getFileManager() {
		return fileManager;
	}
	
	/*
	 * Inicia um arquivo do zero
	 * Reinicia todos os dados necess�rios
	 */
	public abstract void restart() ;
	
	/*
	 * For�a os buffers a liberarem as modifica��es escritas
	 */
	public void flush(){
		if(fileManager!=null)
			fileManager.flush();
	}
	
	/*
	 * Fecha a manipula��o do arquivo e faz o salvametno dos dados
	 */
	public void close(){
		if(fileManager!=null)
			fileManager.close();
	}

	/*
	 * Le um record a partir de uma chave primaria
	 */
	public abstract Record read(BigInteger pk);
	public abstract void read(BigInteger pk,byte[] buffer);
	
	/*
	 * Essa fun��o tem como objetivo procurar algum record que tenha a chave primaria correspondente
	 * e atualiza-la com as informa��es do record correspondente.
	 * Caso n�o encontre ela deve ser adicionada a lista
	 */
	public abstract void write(Record r) ;
	public abstract void write(List<Record> list) ;
	
	/*
	 * Retorna true se o record manager garante os dados ordenados pela primary key
	 */
	public abstract boolean isOrdened();
	
	/*
	 * Retorna um objeto que vai fazer a leitura sequencial dos records.
	 * Esse objeto possui func�es auxiliares de controle
	 */
	public abstract RecordStream sequencialRead();
	
}
