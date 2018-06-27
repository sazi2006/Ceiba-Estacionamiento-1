import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Vehiculo } from '../modelos/vehiculo.model';
import { VehiculosService } from './vehiculos.service';

@Component({
  selector: 'app-vehiculos',
  templateUrl: './vehiculos.component.html',
  styleUrls: ['./vehiculos.component.css']
})
export class VehiculosComponent implements OnInit {
   
  vehiculo: Vehiculo;
  constructor(private router: Router, private vehiculosService: VehiculosService) { }

  ngOnInit() {
  }
  
  registrarSalida(vehiculo: Vehiculo): void {
      this.vehiculosService.registrarSalida(vehiculo)
          .subscribe( data => {
              this.vehiculo = vehiculo;
          })
  };

}
