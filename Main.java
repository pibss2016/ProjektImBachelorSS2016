package pck3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{

	public static void main(String[] args) {
		
		Lane lanex = new Lane(10);
		Thread c = new Thread(new Car(3, lanex,'x'));
		Thread e = new Thread(new Car(1, lanex,'y'));
		Thread c2 = new Thread(new Car(3, lanex,'t'));
		Thread e2 = new Thread(new Car(1, lanex,'k'));
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		while(true){
		
		executor.execute(c);
		executor.execute(e);
		executor.execute(c2);
		executor.execute(e2);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println(new String(lanex.getLane()));

		}
	}

}
