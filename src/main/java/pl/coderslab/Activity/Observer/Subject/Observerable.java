package pl.coderslab.Activity.Observer.Subject;

import pl.coderslab.Activity.Observer.Observer;

public interface Observerable {
	
	void attatchObserver(Observer observer);
	void detatchObserver(Observer observer);
	void notifyObservers(String content);
	

}
