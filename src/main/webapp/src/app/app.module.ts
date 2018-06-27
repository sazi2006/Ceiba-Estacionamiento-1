import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { VehiculosComponent } from './vehiculos/vehiculos.component';
import { NavbarComponent } from './navbar/navbar.component';
import { IngresoComponent } from './ingreso/ingreso.component';
import { SalidaComponent } from './salida/salida.component';

@NgModule({
  declarations: [
    AppComponent,
    VehiculosComponent,
    NavbarComponent,
    IngresoComponent,
    SalidaComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
