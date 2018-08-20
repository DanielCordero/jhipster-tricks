import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AplicacionSharedModule } from 'app/shared';
import {
    VariableFrontComponent,
    VariableFrontDetailComponent,
    VariableFrontUpdateComponent,
    VariableFrontDeletePopupComponent,
    VariableFrontDeleteDialogComponent,
    variableRoute,
    variablePopupRoute
} from './';
import { VistaCuakComponent } from './vista-cuak/vista-cuak.component';

const ENTITY_STATES = [...variableRoute, ...variablePopupRoute];

@NgModule({
    imports: [AplicacionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VariableFrontComponent,
        VariableFrontDetailComponent,
        VariableFrontUpdateComponent,
        VariableFrontDeleteDialogComponent,
        VariableFrontDeletePopupComponent,
        VistaCuakComponent
    ],
    entryComponents: [
        VariableFrontComponent,
        VariableFrontUpdateComponent,
        VariableFrontDeleteDialogComponent,
        VariableFrontDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacionVariableFrontModule {}
