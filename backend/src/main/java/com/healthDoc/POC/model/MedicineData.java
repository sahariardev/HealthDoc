package com.healthDoc.POC.model;

import java.util.List;

public class MedicineData {

	private String genericName;
	private String weight;
	private String title;
	private String type;
	private String dosageAndAdministration;
	private String sideEffects;
	private List<String> indications;
	public String getGeneric_name() {
		return genericName;
	}
	public void setGeneric_name(String generic_name) {
		this.genericName = generic_name;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDosage_and_administration() {
		return dosageAndAdministration;
	}
	public void setDosage_and_administration(String dosage_and_administration) {
		this.dosageAndAdministration = dosage_and_administration;
	}
	public String getSide_effects() {
		return sideEffects;
	}
	public void setSide_effects(String side_effects) {
		this.sideEffects = side_effects;
	}
	public List<String> getIndications() {
		return indications;
	}
	public void setIndications(List<String> indications) {
		this.indications = indications;
	}
	
	
	
	
}
