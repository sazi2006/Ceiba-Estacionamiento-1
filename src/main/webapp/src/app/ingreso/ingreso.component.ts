import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Vehiculo } from '../modelos/vehiculo.model';
import { VehiculosService } from '../vehiculos/vehiculos.service';

@Component({
  templateUrl: './ingreso.component.html',
  host: {
      class:'col-lg-4 col-md-6 col-sm-12'
  }
})
export class IngresoComponent {

  vehiculo: Vehiculo = new Vehiculo();
  errorMessage: String;
  successMessage: String;

  constructor(private router: Router, private vehiculosService: VehiculosService) { }
  
  registrarIngreso(): void {
      this.successMessage = null;
      this.errorMessage = null;
      this.vehiculosService.registrarIngreso(this.vehiculo)
          .subscribe( data => {
              //alert("Se registro el ingreso");
              if(data['estado'] != undefined && data['estado'] == true) {
                  this.successMessage = data['mensaje'];
              }else if(data['estado'] != undefined && data['estado'] == false){
                  this.errorMessage = data['mensaje'];
              }
          })
  }
  

}
