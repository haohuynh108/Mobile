import { Component } from '@angular/core';
import { DeviceMotion, DeviceMotionAccelerationData } from '@ionic-native/device-motion/ngx';
import { Geolocation, Geoposition } from '@ionic-native/geolocation/ngx';
import {Platform} from '@ionic/angular';
 
 
@Component({
selector: 'app-home',
templateUrl: 'home.page.html',
styleUrls: ['home.page.scss'],
})
 
export class HomePage {
 
acceleration: [number, number, number] = [0,0,0];
position: [number, number] = [0,0];
 
constructor(public plt:Platform, private deviceMotion: DeviceMotion, private geo:Geolocation) {
 
  plt.ready().then((readySrc)=>{
 
    if(readySrc == "cordova"){
    this.deviceMotion.getCurrentAcceleration()
 
     .then(
       (acc: DeviceMotionAccelerationData)=>{
       console.log("ACC:", acc.x, acc.y, acc.z);
 
     },
     (error:any)=>console.log("ACC", error)
   );
 
   let accWatch = this.deviceMotion.watchAcceleration();
 
   accWatch.subscribe((acc: DeviceMotionAccelerationData)=>{
     console.log("ACC:", acc.x, acc.y, acc.z);
     this.acceleration = [acc.x, acc.y, acc.z];
   },
   (error:any)=>console.log("ACC", error));
 
   this.geo.getCurrentPosition({
     timeout: 1000, enableHighAccuracy: true, maximumAge: 3600
   })
 
   .then(
     (resp) => {
       this.position[0] = resp.coords.latitude;
       this.position[1] = resp.coords.longitude;
     }
   );
 
   let positionWatch = this.geo.watchPosition({
     timeout: 1000, enableHighAccuracy: true, maximumAge: 3600
   });
 
   positionWatch.subscribe(
     (data:Geoposition)=>{
       this.position[0] = data.coords.latitude;
       this.position[1] = data.coords.longitude;
     },
 
     (error:any)=>{
       console.log("POSITION", error);
     }
   );
}else{
 
console.log("This is a browser");
this.geo.getCurrentPosition({
  timeout: 1000, enableHighAccuracy: true, maximumAge: 3600
})
.then(
  (resp) => {
    this.position[0] = resp.coords.latitude;
    this.position[1] = resp.coords.longitude;
  }
);
 
let positionWatch = this.geo.watchPosition({
  timeout: 1000, enableHighAccuracy: true, maximumAge: 3600
});
 
positionWatch.subscribe(
  (data:Geoposition)=>{
    this.position[0] = data.coords.latitude;
    this.position[1] = data.coords.longitude;
  },
 
  (error:any)=>{
    console.log("POSITION", error);
  }
);
}
});
}
 
}
