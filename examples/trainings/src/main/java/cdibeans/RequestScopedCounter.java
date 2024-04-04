package cdibeans;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class RequestScopedCounter implements Serializable {

	private int counter = 0;

	@PostConstruct
	private void init() {
		counter = 6;
	}

	public void count(){
		counter++;
	}

	public int getCounter() {
		return counter;
	}
}