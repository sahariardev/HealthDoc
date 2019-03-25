package com.healthDoc.POC.model;

import java.util.ArrayList;
import java.util.List;

public class DiseaseData {

	public String name;
	public List<String> data=new ArrayList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public void addData(String item)
	{
		data.add(item);
	}
	@Override
	public String toString() {
		return "DiseaseData [name=" + name + ", data=" + data + "]";
	}
	
	
	
}
