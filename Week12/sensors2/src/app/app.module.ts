import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';
 
import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
 
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { DeviceMotion } from '@ionic-native/device-motion/ngx';
import { Geolocation } from '@ionic-native/geolocation/ngx';
 
@NgModule({
 declarations: [AppComponent],
 entryComponents: [],
 imports: [BrowserModule, IonicModule.forRoot(), AppRoutingModule],
 providers: [{ provide: RouteReuseStrategy, useClass: IonicRouteStrategy }, DeviceMotion, Geolocation],
 bootstrap: [AppComponent],
})
export class AppModule {}
