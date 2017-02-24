package filosofos;

import java.util.concurrent.Semaphore;

public class Palillo extends Semaphore{

	
	private static final long serialVersionUID = -4771795970095303765L;

	public Palillo(int permits) {
		super(permits);
	}	
	
	public Palillo(int permits, boolean b) {
		super(permits, b);
	}
	
}
