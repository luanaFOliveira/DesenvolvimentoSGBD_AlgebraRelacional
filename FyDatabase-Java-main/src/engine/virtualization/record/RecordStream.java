package engine.virtualization.record;


import java.math.BigInteger;

public interface RecordStream {
	
	/*
	 * Abre e fecha o leitor sequencial
	 * � importante caso seja necess�rio bloquear a tabela dependendo do record manager
	 * Na fun��o open deve ser passado true caso possa ser chamado a fun��o write
	 */
	public void open(boolean lockToWrite);
	public void close();
	
	/*
	 * Verifica se existe um record para a pr�xima leitura
	 */
	public boolean hasNext() ;

	/*
	 * Retorna o record no ponteiro atual, e atualiza o ponteiro para o pr�ximo ponteiro;
	 */
	public Record next();
	
	/*
	 * Faz a leitura do record ou a posi��o em que ele est� armazendo no banco de dados
	 */
	public Record getRecord();
	
	/*
	 * Faz a chamada de escrita do record na posi��o em que estava
	 * Caso seja necess�rio, o objeto ira fazer chamadas de atualiza��o da posi��o dos outros records
	 */
	public void write(Record r);

	/*
	 * Diz que o item atual deve ser definido como removido
	 */
	public void remove();
	
	/*
	 * Reinicia a leitura da stream, voltando para a primieira posi��o
	 */
	public void reset();
	/*
	 * Define a posi��o para a leitura, caso seja passado uma posi��o inv�lida, a leitura pode ocorrer de forma errada.
	 */
	public void setPointer(BigInteger pk);
	public BigInteger getPointer();
	
}
