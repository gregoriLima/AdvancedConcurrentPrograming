package br.gregori.servidor;

import java.lang.Thread.UncaughtExceptionHandler;


public class TratadorDeExceptions implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {

		System.out.println("Exception na Thread: " + t.getName() + " " + e.getMessage());
		
	}

}
