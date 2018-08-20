import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';
import { ConfiguracionVariableFrontService } from './configuracion-variable-front.service';
import { ConfiguracionVariableFrontComponent } from './configuracion-variable-front.component';
import { ConfiguracionVariableFrontDetailComponent } from './configuracion-variable-front-detail.component';
import { ConfiguracionVariableFrontUpdateComponent } from './configuracion-variable-front-update.component';
import { ConfiguracionVariableFrontDeletePopupComponent } from './configuracion-variable-front-delete-dialog.component';
import { IConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';

@Injectable({ providedIn: 'root' })
export class ConfiguracionVariableFrontResolve implements Resolve<IConfiguracionVariableFront> {
    constructor(private service: ConfiguracionVariableFrontService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((configuracionVariable: HttpResponse<ConfiguracionVariableFront>) => configuracionVariable.body));
        }
        return of(new ConfiguracionVariableFront());
    }
}

export const configuracionVariableRoute: Routes = [
    {
        path: 'configuracion-variable-front',
        component: ConfiguracionVariableFrontComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.configuracionVariable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configuracion-variable-front/:id/view',
        component: ConfiguracionVariableFrontDetailComponent,
        resolve: {
            configuracionVariable: ConfiguracionVariableFrontResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.configuracionVariable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configuracion-variable-front/new',
        component: ConfiguracionVariableFrontUpdateComponent,
        resolve: {
            configuracionVariable: ConfiguracionVariableFrontResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.configuracionVariable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'configuracion-variable-front/:id/edit',
        component: ConfiguracionVariableFrontUpdateComponent,
        resolve: {
            configuracionVariable: ConfiguracionVariableFrontResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.configuracionVariable.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const configuracionVariablePopupRoute: Routes = [
    {
        path: 'configuracion-variable-front/:id/delete',
        component: ConfiguracionVariableFrontDeletePopupComponent,
        resolve: {
            configuracionVariable: ConfiguracionVariableFrontResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.configuracionVariable.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
