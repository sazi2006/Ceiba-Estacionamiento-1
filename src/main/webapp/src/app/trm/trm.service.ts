import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class TrmService {

  constructor(private http:HttpClient) {}

  private obtenerTRMURL = '/obtener/trm';

  public obtenerTRM() {
    return this.http.get(this.obtenerTRMURL);
  }

}