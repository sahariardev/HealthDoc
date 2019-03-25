package com.healthDoc.POC.controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.healthDoc.POC.model.DiseaseData;
import com.healthDoc.POC.model.MedicineData;

@CrossOrigin
@RestController
public class TestController {
	@Value("${app.hostUrl}")
	public String host;

	// test elasticsearch
	@GetMapping("search/by/symtom/{symtom}")
	public String test(@PathVariable String symtom) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = "http://192.168.50.156:9200/_search?q=symptom:" + symtom;
		ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
		System.out.println(response.getBody());
		return response.getBody();
	}

	@GetMapping("post/sample/data/{from}/{to}")
	public String postSample(@PathVariable int from,@PathVariable int to) {
		RestTemplate restTemplate = new RestTemplate();
		String fooResourceUrl = host + "/medicines/medicine/";
		System.out.println(fooResourceUrl);
		System.out.println("Starting");
		while (from <= to) {

			try {

				Document doc = Jsoup.connect("https://medex.com.bd/brands/" + (from)).get();
				Element brandHeader = doc.getElementsByClass("brand-header").get(0);
				Element titledom = brandHeader.child(0);
				JSONObject mainObject = new JSONObject();
				String type = titledom.getElementsByClass("h1-subtitle").text();

				String title = titledom.text().replace(type, "").trim();
				mainObject.put("title", title);
				mainObject.put("type", type);
				String genericName = brandHeader.child(1).text();
				mainObject.put("genericName", genericName);
				String weight = brandHeader.child(2).text();
				mainObject.put("weight", weight);
				String indications = doc.getElementsByClass("ac-body").get(0).text();
				indications = indications.replace(genericName + " is indicated for", "").trim();
				String[] ind = indications.split(",");
				JSONArray list = new JSONArray();
				ArrayList<String> dbList=new ArrayList<String>();
				for (int d = 0; d < ind.length; d++) {
					list.add(ind[d]);
					dbList.add(ind[d]);
				}

				mainObject.put("indications", list);

				String dosageAndAdministration = doc.getElementsByClass("ac-body").get(3).html();
				mainObject.put("dosageAndAdministration", dosageAndAdministration);
				String sideEffects = doc.getElementsByClass("ac-body").get(6).html();
				mainObject.put("sideEffects", sideEffects);
				//System.out.println(mainObject.toJSONString());
				System.out.println("200 " + from);
				
				MedicineData data=new MedicineData();
				data.setTitle(title);
				data.setDosage_and_administration(dosageAndAdministration);
				data.setGeneric_name(genericName);
				data.setSide_effects(sideEffects);
				data.setType(type);
				data.setWeight(weight);
				data.setIndications(dbList);
				data = restTemplate.postForObject(fooResourceUrl, data, MedicineData.class);
				

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("404 " + from);
			}
			from++;
		}

		
		
		return "Done";
	}

}
