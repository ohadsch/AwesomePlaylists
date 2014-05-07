package com.example.awesomeplaylists.Services;

public class InformationModel {
	final private static InformationModel instance = new InformationModel();
	
	private InformationModel(){}
	
	public static InformationModel getInstance(){
		return instance;
	}

		
	public boolean IsShuffle() {
		return false;
	}

	public void setShuffle(boolean isShuffle) {

	}
	
	public boolean IsScreenStayOn() {
		return false;
	}

	public void setScreenStayOn(boolean isScreenStayOn) {

	}		
}

