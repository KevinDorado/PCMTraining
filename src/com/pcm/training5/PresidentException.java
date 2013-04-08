package com.pcm.training5;

@SuppressWarnings("all")
public class PresidentException extends Exception {
	
	public PresidentException(){
		super("Generic Exception");
	}
	
	public PresidentException(String message){
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
