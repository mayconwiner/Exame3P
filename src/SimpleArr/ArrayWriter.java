package SimpleArr;

public class ArrayWriter implements Runnable {
	private final SimpleArray sharedSimpleArray;
	private final int startValue;

	public ArrayWriter(int value, SimpleArray array) {
		startValue = value;
		sharedSimpleArray = array;
	}// fim do construtor

	public void run() {
		for (int i = startValue; i < startValue + 3; i++) {
			sharedSimpleArray.add(i); // adiciona um elemento ao array
										// compartilhado
		}
	}
}
