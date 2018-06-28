import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { TrmService } from './trm.service';



@Component({
  selector: 'app-trm',
  templateUrl: './trm.component.html',
  styleUrls: ['./trm.component.css']
})
export class TrmComponent implements OnInit {

    trm: String;
    errorObtMessage: String;
    
    constructor(private router: Router, private trmService: TrmService) {
    
    }
    
    ngOnInit() {
        this.obtenerTRM();
    };
    
    obtenerTRM(): void {
      this.trmService.obtenerTRM()
        .subscribe( data => {
            if(data['estado'] != undefined && data['estado'] == true) {
                this.trm = data['contenido']['valor']
            }else if(data['estado'] != undefined && data['estado'] == false){
                this.errorObtMessage = data['mensaje'];
            }
        })
    };

}
