import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Vehiculo } from '../modelos/vehiculo.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class VehiculosService {

  constructor(private http:HttpClient) {}

  //private userUrl = 'http://localhost:8080/user-portal/user';
  private retirarVehiculoURL = '/registrar-salida/vehiculo';

  public obtenerVehiculo() {
    return this.http.get<Vehiculo>(this.retirarVehiculoURL);
  }

  public registrarSalida(placa) {
    let fechaSalida = new Date().toISOString();
    return this.http.delete(this.retirarVehiculoURL + "/"+ placa + "/" + fechaSalida);
  }

  public registrarIngreso(vehiculo) {
    let veh = {};
    let url = "";
    if(vehiculo.tipo == "Moto") {
        veh = {
                "placa": vehiculo.placa,
                "fechaIngreso": vehiculo.fechaIngreso,
                "cilindrada": vehiculo.cilindrada
        };
        url = "/registrar-ingreso/moto";
    }else if(vehiculo.tipo == "Carro"){
        veh = {
                "placa": vehiculo.placa,
                "fechaIngreso": vehiculo.fechaIngreso
        };
        url = "/registrar-ingreso/carro";
    }
      
      
    return this.http.post(url, veh);
  }

}