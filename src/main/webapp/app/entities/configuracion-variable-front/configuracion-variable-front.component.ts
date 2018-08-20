import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';
import { Principal } from 'app/core';
import { ConfiguracionVariableFrontService } from './configuracion-variable-front.service';
import { IVariableFront } from 'app/shared/model/variable-front.model';
import { VariableFrontService } from 'app/entities/variable-front';

@Component({
    selector: 'jhi-configuracion-variable-front',
    templateUrl: './configuracion-variable-front.component.html'
})
export class ConfiguracionVariableFrontComponent implements OnInit, OnDestroy {
    configuracionVariables: IConfiguracionVariableFront[];
    variables: IVariableFront[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private configuracionVariableService: ConfiguracionVariableFrontService,
        private variableService: VariableFrontService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) { }

    loadAll() {
        this.configuracionVariableService.query().subscribe(
            (res: HttpResponse<IConfiguracionVariableFront[]>) => {
                this.configuracionVariables = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.variableService.query().subscribe(
            (res: HttpResponse<IVariableFront[]>) => {
                this.variables = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });

        this.registerChangeInConfiguracionVariables();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IConfiguracionVariableFront) {
        return item.id;
    }

    registerChangeInConfiguracionVariables() {
        this.eventSubscriber = this.eventManager.subscribe('configuracionVariableListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    nombreVariable(id: number): string {

        const a = this.variables.find(cuak => cuak.id === id);

        if (a) {
            return a.nombre;
        } else {
            throw `No hay una variable con id: ` + id;
        }
    }

}
