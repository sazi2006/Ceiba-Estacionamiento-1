import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from './app.component';
import { VehiculosComponent } from './vehiculos/vehiculos.component';
import { NavbarComponent } from './navbar/navbar.component';
import { IngresoComponent } from './ingreso/ingreso.component';
import { SalidaComponent } from './salida/salida.component';
import { AppRoutingModule } from './app.routing';
import { VehiculosService } from './vehiculos/vehiculos.service';



@NgModule({
  declarations: [
    AppComponent,
    VehiculosComponent,
    NavbarComponent,
    IngresoComponent,
    SalidaComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [VehiculosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
