import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { FormsModule } from '@angular/forms';
@NgModule({
declarations: [
AppComponent,
SearchPageComponent
],
imports: [
BrowserModule,
AppRoutingModule,
FormsModule ,
HttpClientModule
],
providers: [],
bootstrap: [AppComponent]
})
export class AppModule { }