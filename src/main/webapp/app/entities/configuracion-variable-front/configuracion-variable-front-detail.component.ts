import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { IConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';
import { VariableFrontService } from 'app/entities/variable-front';
import { IVariableFront } from 'app/shared/model/variable-front.model';
import { JhiAlertService } from 'ng-jhipster';

@Component({
    selector: 'jhi-configuracion-variable-front-detail',
    templateUrl: './configuracion-variable-front-detail.component.html'
})
export class ConfiguracionVariableFrontDetailComponent implements OnInit {
    configuracionVariable: IConfiguracionVariableFront;

    variables: IVariableFront[];

    constructor(private activatedRoute: ActivatedRoute,
        private variableService: VariableFrontService,
        private jhiAlertService: JhiAlertService,
    ) { }

    ngOnInit() {
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    previousState() {
        window.history.back();
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
