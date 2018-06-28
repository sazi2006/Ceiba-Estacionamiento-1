import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Vehiculo } from '../modelos/vehiculo.model';
import { VehiculosService } from '../vehiculos/vehiculos.service';

@Component({
  templateUrl: './salida.component.html',
  host: {
      class:'col-lg-4 col-md-6 col-sm-12'
  }
})
export class SalidaComponent {

  vehiculo: Vehiculo = new Vehiculo();
  errorMessage: String;
  successMessage: String;
  montoPagar: String;
        
  constructor(private router: Router, private vehiculosService: VehiculosService) { }
  
  registrarSalida(): void {
      this.successMessage = null;
      this.errorMessage = null;
      this.vehiculosService.registrarSalida(this.vehiculo.placa)
          .subscribe( data => {
              if(data['estado'] != undefined && data['estado'] == true) {
                  this.successMessage = data['mensaje'];
                  this.montoPagar = data['contenido'];
              }else if(data['estado'] != undefined && data['estado'] == false){
                  this.errorMessage = data['mensaje'];
              }
          })
  }

}
