import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { IngresoComponent } from './ingreso/ingreso.component';
import { SalidaComponent } from './salida/salida.component';

const routes: Routes = [
    { path: '', redirectTo: '/registrar_ingreso', pathMatch: 'full' },
    { path: 'registrar_ingreso', component: IngresoComponent },
    { path: 'registrar_salida', component: SalidaComponent }
];

@NgModule({
    imports: [
      RouterModule.forRoot(routes)
    ],
    exports: [
      RouterModule
    ],
    declarations: []
})

export class AppRoutingModule { }

