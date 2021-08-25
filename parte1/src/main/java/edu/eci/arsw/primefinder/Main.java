package edu.eci.arsw.primefinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * public class Main {
 *
 *
 * 	public static void main(String[] args) {
 * 		PrimeFinderThread philo = new PrimeFinderThread(0, 9999999);
 * 		PrimeFinderThread shilo = new PrimeFinderThread(10000000, 19999999);
 * 		PrimeFinderThread thilo = new PrimeFinderThread(20000000, 30000000);
 * 		//PrimeFinderThread pft=new PrimeFinderThread(0, 30000000);
 * 		long inicio = System.currentTimeMillis();
 * 		philo.start();
 * 		shilo.start();
 * 		thilo.start();
 * 		if (inicio == 500) {
 * 			System.out.println("Entra al condicional");
 * 			philo.interrupt();
 * 			shilo.interrupt();
 * 			thilo.interrupt();
 *                }
 * 	}
 * }
 */

/**
 * public class Main {
 * 	public static void main(String[] args) {
 * 		PrimeFinderThread pft1=new PrimeFinderThread(0, 10000000);
 * 		PrimeFinderThread pft2=new PrimeFinderThread(10000000, 20000000);
 * 		PrimeFinderThread pft3=new PrimeFinderThread(20000000, 30000000);
 * 		pft1.start();
 * 		pft2.start();
 * 		pft3.start();
 *        }
 * }
 */

public class Main {
	public static void main(String[] args) {
		PrimeFinderThread pft1=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft2=new PrimeFinderThread(10000000, 20000000);
		PrimeFinderThread pft3=new PrimeFinderThread(20000000, 30000000);
		pft1.start();
		pft2.start();
		pft3.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		new Timer().schedule(
				new TimerTask() {
					@Override
					public void run() {
						try {
							pft1.suspend();
							pft2.suspend();
							pft3.suspend();
							while(br.read() != '\n') {
								br.read();
							}
							pft1.resume();
							pft2.resume();
							pft3.resume();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				},5000);
	}
}
