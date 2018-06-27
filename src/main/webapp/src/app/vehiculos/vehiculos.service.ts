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
    private userUrl = '/api';

  public obtenerVehiculo() {
    return this.http.get<Vehiculo>(this.userUrl);
  }

  public registrarSalida(vehiculo) {
    return this.http.delete(this.userUrl + "/"+ vehiculo.placa);
  }

  public registrarIngreso(vehiculo) {
    return this.http.post<Vehiculo>(this.userUrl, vehiculo);
  }

}