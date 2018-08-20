import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';
import { ConfiguracionVariableFrontService } from './configuracion-variable-front.service';
import { IVariableFront } from 'app/shared/model/variable-front.model';
import { VariableFrontService } from 'app/entities/variable-front';

@Component({
    selector: 'jhi-configuracion-variable-front-update',
    templateUrl: './configuracion-variable-front-update.component.html'
})
export class ConfiguracionVariableFrontUpdateComponent implements OnInit {
    private _configuracionVariable: IConfiguracionVariableFront;
    isSaving: boolean;

    variables: IVariableFront[];
    fechaDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private configuracionVariableService: ConfiguracionVariableFrontService,
        private variableService: VariableFrontService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ configuracionVariable }) => {
            this.configuracionVariable = configuracionVariable;
        });
        this.variableService.query().subscribe(
            (res: HttpResponse<IVariableFront[]>) => {
                this.variables = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.configuracionVariable.id !== undefined) {
            this.subscribeToSaveResponse(this.configuracionVariableService.update(this.configuracionVariable));
        } else {
            this.subscribeToSaveResponse(this.configuracionVariableService.create(this.configuracionVariable));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConfiguracionVariableFront>>) {
        result.subscribe(
            (res: HttpResponse<IConfiguracionVariableFront>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackVariableById(index: number, item: IVariableFront) {
        return item.id;
    }
    get configuracionVariable() {
        return this._configuracionVariable;
    }

    set configuracionVariable(configuracionVariable: IConfiguracionVariableFront) {
        this._configuracionVariable = configuracionVariable;
    }
}
