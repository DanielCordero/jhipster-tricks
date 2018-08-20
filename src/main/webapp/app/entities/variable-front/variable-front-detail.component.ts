import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVariableFront } from 'app/shared/model/variable-front.model';

@Component({
    selector: 'jhi-variable-front-detail',
    templateUrl: './variable-front-detail.component.html'
})
export class VariableFrontDetailComponent implements OnInit {
    variable: IVariableFront;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ variable }) => {
            this.variable = variable;
        });
    }

    previousState() {
        window.history.back();
    }
}
