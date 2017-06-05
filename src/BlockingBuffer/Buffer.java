package BlockingBuffer;

public interface Buffer {
	// coloca o valor int do Buffer
	public void set(int value) throws InterruptedException;
	
	//retorna o valor int a partir do buffer 
	public int get() throws InterruptedException;

}
