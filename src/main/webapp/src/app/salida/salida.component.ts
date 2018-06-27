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
    
  constructor(private router: Router, private vehiculosService: VehiculosService) { }
  
  registrarSalida(): void {
      this.vehiculosService.registrarIngreso(this.vehiculo)
          .subscribe( data => {
              alert("Se registro el ingreso");
          })
  }
  

}
