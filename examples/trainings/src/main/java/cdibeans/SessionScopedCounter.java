package cdibeans;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;

@SessionScoped
public class SessionScopedCounter implements Serializable {

	private int counter = 0;

	public void count(){
		counter++;
	}

	public int getCounter() {
		return counter;
	}
}