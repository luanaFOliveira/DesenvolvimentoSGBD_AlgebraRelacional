package engine.virtualization.record.manager.storage;

import engine.virtualization.record.Record;
import engine.virtualization.record.RecordStream;

import java.math.BigInteger;
import java.util.List;

public interface RecordStorageController {

	/*
	 *	O RecordStorage organiza os records baseado em posi��es, ou seja, a key passada
	 * � relativa a posi��o que aquele storage est� armazenado sendo 0 o primeiro dado
	 * e N-1 o ultimo dado.
	 *
	 * 	O RecordStorage tamb�m implementa
	 */

	/*
	 * Inicia um arquivo do zero
	 * Reinicia todos os dados necess�rios
	 */
	public void restartFileSet() ;

	/*
	 * For�a os buffers a liberarem as modifica��es escritas
	 */
	public void flush() ;

	/*
	 * Le o dado a partir da posi��o solicitada
	 */
	public Record read(long key) ;
	public void read(long key,byte[] buffer) ;

	/*
	 * Sobrescreve o record naquela posi��o
	 */
	public long write(Record r,long key);
	
	/*
	 * Escreve um record na posi��o anterior aquela
	 */
	public long writeNew(Record r);
	public void writeNew(List<Record> list);

	/*
	 * Faz uma busca de um dado pela primary key utilizando o melhor algoritmo daquela
	 * estrutura
	 */
	public boolean search(BigInteger pk, byte[] buffer);

	/*
	 * Retorna um objeto para efetuar a leitura sequencial
	 */
	public RecordStream sequencialRead();
}
