package com.healthDoc.POC.request;

import java.util.ArrayList;

public class SymptomsRequest {
	private ArrayList<String> symptoms;

	public ArrayList<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(ArrayList<String> symptoms) {
		this.symptoms = symptoms;
	}

	@Override
	public String toString() {
		return "Symptoms [symptoms=" + symptoms + "]";
	}
	
	

}
