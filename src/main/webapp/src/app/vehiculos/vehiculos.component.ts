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
   
    vehiculoObt: Vehiculo = new Vehiculo();
    placa: String;
    errorObtMessage: String;

    constructor(private router: Router, private vehiculosService: VehiculosService) {
    
    }
    
    ngOnInit() {
    };
    
    obtenerVehiculo(): void {
      this.errorObtMessage = null;
      this.vehiculosService.obtenerVehiculo(this.placa)
        .subscribe( data => {
            console.log(data);
            if(data['estado'] != undefined && data['estado'] == true) {
                this.vehiculoObt.placa = data['contenido']['placa'];
                this.vehiculoObt.fechaIngreso = data['contenido']['fechaIngreso'];
                this.vehiculoObt.cilindrada = data['contenido']['cilindrada'] == undefined ? "N/A" : data['contenido']['cilindrada'];
                this.vehiculoObt.tipo = data['tipo'];
            }else if(data['estado'] != undefined && data['estado'] == false){
                
                this.errorObtMessage = data['mensaje'];
            }
        })
    };
  

}
