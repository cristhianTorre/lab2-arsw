package edu.eci.arsw.primefinder;

import java.util.Timer;
import java.util.TimerTask;

public class Main {


	public static void main(String[] args) {
		PrimeFinderThread philo = new PrimeFinderThread(0, 9999999);
		PrimeFinderThread shilo = new PrimeFinderThread(10000000, 19999999);
		PrimeFinderThread thilo = new PrimeFinderThread(20000000, 30000000);
		//PrimeFinderThread pft=new PrimeFinderThread(0, 30000000);
		long inicio = System.currentTimeMillis();
		philo.start();
		shilo.start();
		thilo.start();
		if (inicio == 500) {
			System.out.println("Entra al condicional");
			philo.interrupt();
			shilo.interrupt();
			thilo.interrupt();
		}

	}
}
