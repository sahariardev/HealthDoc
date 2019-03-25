import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { $ } from 'protractor';
import {host} from  '../config';
@Component({
selector: 'app-search-page',
templateUrl: './search-page.component.html',
styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {

constructor(private http: HttpClient) { }

url=host()+"/suggest/by/symptom";
ngOnInit() {
}
entries=[
    {
        id: 0,
        description: 'Disease'
    },
    {
        id: 1,
        description: 'Symptoms'
    },
    {
        id: 2,
        description: 'Medicines'
    }
]

data = [];
dissym=this.entries[0];
onSelectionChange(entry) {
    this.dissym = entry;
    console.log("hey "+this.dissym.id)
}
suggestions=[];
disease="";
showSuggestionBox=false;
configUrl = "";
selectSuggestion(item)
{
   this.disease=item;
   this.showSuggestionBox=false;
}
showSuggestions()
{
  if(this.disease.length!=0)
  {
     this.showSuggestionBox=true; 
     //html request
     let dataUrl=this.url+"/"+this.disease
     this.http.get(dataUrl).subscribe(res=>{
        let response:any=res;
       this.suggestions=response.hits.hits;
       console.log("Inside sho suggestion methiod");
       console.log(response);
       console.log(this.suggestions);

     })


  }
  else
  {
    this.showSuggestionBox=false;  
  }
}

onClickSubmit(data) {
    //this.htmlVariable ="";
    
    var disname;
    if(this.dissym.id== 1){
        this.configUrl = host()+"/search/by/symptoms/";
        disname={
            "symptoms" : data.disease.split(',') 
        };
    }
    else  if (this.dissym.id== 0){
        this.configUrl = host()+"/search/by/disease/";
        disname={
            "name" : data.disease
        };
    }
    else if((this.dissym.id== 2))
    {

    }

        this.http.post(this.configUrl,
            disname).subscribe(
        (val) => {
            
            let s:any=val;

            this.data = s.hits.hits;
            console.log(this.configUrl)
            console.log(val);
            if(s.hits.hits.length>0){
                console.log("POST call successful value returned in body", 
            val);
            console.log("POST call successful value returned in body", 
            s.hits.hits[0]);
            }
            else{
                //this.htmlVariable = "<span>No Data Found</span>";
            }
        },
        response => {
            console.log("POST call in error", response);
        },
        () => {
            console.log("The POST observable is now completed.");
        });
// return this.http.get(this.configUrl);
}

}