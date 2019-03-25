package com.healthDoc.POC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthDoc.POC.request.DiseaseRequest;
import com.healthDoc.POC.request.SymptomsRequest;
import com.healthDoc.POC.service.SearchService;
@CrossOrigin()
@RestController()
@RequestMapping("/api/v1/")
public class SearchController {

     @Autowired
     SearchService searchService;
	
	//search by disease name[single value]
	@PostMapping("/search/by/disease/")
	public String searchByDiseaseName(@RequestBody DiseaseRequest request)
	{
		String result=searchService.searchByDiseaseName(request.getName());
		return result;
	}
	//search symptom[multi value]
	@PostMapping("/search/by/symptoms/")
	public String searchBySymptomsName(@RequestBody SymptomsRequest request) {
		return searchService.searchBySymptoms(request.getSymptoms());
	}
	@GetMapping("suggest/by/symptom/{key}")
	public String suggestBySymptom(@PathVariable String key)
	{
		return searchService.suggestBySymptom(key);
	}
		//autocomplete
}
