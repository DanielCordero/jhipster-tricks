import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IVariableFront } from 'app/shared/model/variable-front.model';
import { VariableFrontService } from './variable-front.service';

@Component({
    selector: 'jhi-variable-front-update',
    templateUrl: './variable-front-update.component.html'
})
export class VariableFrontUpdateComponent implements OnInit {
    private _variable: IVariableFront;
    isSaving: boolean;

    constructor(private variableService: VariableFrontService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ variable }) => {
            this.variable = variable;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.variable.id !== undefined) {
            this.subscribeToSaveResponse(this.variableService.update(this.variable));
        } else {
            this.subscribeToSaveResponse(this.variableService.create(this.variable));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVariableFront>>) {
        result.subscribe((res: HttpResponse<IVariableFront>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get variable() {
        return this._variable;
    }

    set variable(variable: IVariableFront) {
        this._variable = variable;
    }
}
