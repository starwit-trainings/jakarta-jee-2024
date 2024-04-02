package cdibeans;

import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationScopedCounter implements Serializable {

	private int counter = 0;

	public void count(){
		counter++;
	}

	public int getCounter() {
		return counter;
	}
}