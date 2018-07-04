import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { Vehiculo } from '../modelos/vehiculo.model';
import { VehiculosService } from '../vehiculos/vehiculos.service';

const MENSAJE_ERROR_CAMPO_VACIO = "Debe rellenar los campos solicitados";
const MENSAJE_ERROR_NO_NUMERICO = "El cilindraje debe ser numérico";

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
      
      if(this.vehiculo.tipo == undefined || this.vehiculo.tipo == "") {
          this.errorMessage = MENSAJE_ERROR_CAMPO_VACIO;
          return;
      }else if(this.vehiculo.placa == undefined || this.vehiculo.placa == "") {
          this.errorMessage = MENSAJE_ERROR_CAMPO_VACIO;
          return;
      }else if(this.vehiculo.tipo == "Moto" && this.vehiculo.cilindrada == undefined) {
          this.errorMessage = MENSAJE_ERROR_CAMPO_VACIO;
          return;
      }else if(this.vehiculo.tipo == "Moto" && !this.isNumber(this.vehiculo.cilindrada)) {
          this.errorMessage = MENSAJE_ERROR_NO_NUMERICO;
          return;
      }
      this.vehiculosService.registrarIngreso(this.vehiculo)
          .subscribe( data => {
              
              if(data['estado'] != undefined && data['estado'] == true) {
                  this.successMessage = data['mensaje'];
              }else if(data['estado'] != undefined && data['estado'] == false){
                  this.errorMessage = data['mensaje'];
              }
          })
  }
  IHV103
  isNumber(n) {
      return !isNaN(parseFloat(n)) && isFinite(n);
  }
  
  selectchange(args){ 
      console.log(args.target.options[args.target.selectedIndex].text);
  }
  

}
