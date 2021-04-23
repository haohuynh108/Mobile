import { Component } from '@angular/core';
 
import { Plugins } from "@capacitor/core";
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import {Platform} from '@ionic/angular';
 
const {Storage} = Plugins;
 
@Component({
 selector: 'app-tab1',
 templateUrl: 'tab1.page.html',
 styleUrls: ['tab1.page.scss']
})
export class Tab1Page {
 
 locations: Location[];
 
 name:string;
 
 coordinates: [number, number, number] = [0,0,0];
 
 
 constructor(public plt:Platform, private geo:Geolocation) {
   this.readLocations();
   plt.ready().then((readySrc)=>{
 
     if(readySrc == "cordova"){
 
    this.geo.getCurrentPosition({
      timeout: 1000, enableHighAccuracy: true, maximumAge: 3600
    })
 
    .then(
      (resp) => {
        this.coordinates[0] = resp.timestamp;
        this.coordinates[1] = resp.coords.latitude;
        this.coordinates[2] = resp.coords.longitude;
      
      }
    );
 
    let positionWatch = this.geo.watchPosition({
      timeout: 1000, enableHighAccuracy: true, maximumAge: 3600
    });
 
    positionWatch.subscribe(
      (data:Geoposition)=>{
        this.coordinates[0] = data.timestamp;
        this.coordinates[1] = data.coords.latitude;
        this.coordinates[2] = data.coords.longitude;
       
      },
 
      (error:any)=>{
        console.log("Coordinates", error);
      }
    );
 }else{
 
 console.log("This is a list");
 this.geo.getCurrentPosition({
   timeout: 1000, enableHighAccuracy: true, maximumAge: 3600
 })
 .then(
   (resp) => {
    this.coordinates[0] = resp.timestamp;
    this.coordinates[1] = resp.coords.latitude;
    this.coordinates[2] = resp.coords.longitude;

   }
 );
 
 let positionWatch = this.geo.watchPosition({
   timeout: 1000, enableHighAccuracy: true, maximumAge: 3600
 });
 
 positionWatch.subscribe(
   (data:Geoposition)=>{
    this.coordinates[0] = data.timestamp;
    this.coordinates[1] = data.coords.latitude;
    this.coordinates[2] = data.coords.longitude;
   },
 
   (error:any)=>{
     console.log("Coordinates", error);
   }
 );
 }
 });
 
 }
 
 saveLocation(){
   const location = new Location(this.name, this.coordinates);
   if(this.name != "" && this.name != null && this.coordinates != null){
     this.setObject(JSON.stringify(location.name), location);
     this.name = "";
     this.coordinates[0] = null;
     this.coordinates[1] = null;
     this.coordinates[2]= null;
     
   }else{
     alert("Location cannot be empty");
   }
 }
 
 async setObject(key:string, value:any){
   await Storage.set(
     {
       key: key,
       value: JSON.stringify(value)
     }
   );
   this.readLocations();
 }
 
 async readLocations(){
   this.locations = [];
   const {keys} = await Storage.keys();
   keys.forEach(this.getLocation, this);
 }
 
 async getLocation(key){
   const item = await Storage.get({key: key});
   let location = JSON.parse(item.value);
   this.locations.push(location);
 }
 
 async clear(){
   await Storage.clear();
   this.locations = [];
 }
 
 async deleteLocation(index){
   let location = this.locations[index];
   await Storage.remove({key:JSON.stringify(location.name)});
   this.readLocations();
 }
 
}
 
 
export class Location{
 name:string;
 coordinates: [number, number, number] = [0,0,0];

 
 constructor(name: string, coordinates:[number, number,number]){
   this.name = name;
   this.coordinates = coordinates
   
 }
}
