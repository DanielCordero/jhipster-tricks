import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { VariableFront } from 'app/shared/model/variable-front.model';
import { VariableFrontService } from './variable-front.service';
import { VariableFrontComponent } from './variable-front.component';
import { VariableFrontDetailComponent } from './variable-front-detail.component';
import { VariableFrontUpdateComponent } from './variable-front-update.component';
import { VariableFrontDeletePopupComponent } from './variable-front-delete-dialog.component';
import { IVariableFront } from 'app/shared/model/variable-front.model';

@Injectable({ providedIn: 'root' })
export class VariableFrontResolve implements Resolve<IVariableFront> {
    constructor(private service: VariableFrontService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((variable: HttpResponse<VariableFront>) => variable.body));
        }
        return of(new VariableFront());
    }
}

export const variableRoute: Routes = [
    {
        path: 'variable-front',
        component: VariableFrontComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'aplicacionApp.variable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variable-front/:id/view',
        component: VariableFrontDetailComponent,
        resolve: {
            variable: VariableFrontResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.variable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variable-front/new',
        component: VariableFrontUpdateComponent,
        resolve: {
            variable: VariableFrontResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.variable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variable-front/:id/edit',
        component: VariableFrontUpdateComponent,
        resolve: {
            variable: VariableFrontResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.variable.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const variablePopupRoute: Routes = [
    {
        path: 'variable-front/:id/delete',
        component: VariableFrontDeletePopupComponent,
        resolve: {
            variable: VariableFrontResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'aplicacionApp.variable.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
