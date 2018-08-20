import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AplicacionSharedModule } from 'app/shared';
import {
    ConfiguracionVariableFrontComponent,
    ConfiguracionVariableFrontDetailComponent,
    ConfiguracionVariableFrontUpdateComponent,
    ConfiguracionVariableFrontDeletePopupComponent,
    ConfiguracionVariableFrontDeleteDialogComponent,
    configuracionVariableRoute,
    configuracionVariablePopupRoute
} from './';

const ENTITY_STATES = [...configuracionVariableRoute, ...configuracionVariablePopupRoute];

@NgModule({
    imports: [AplicacionSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConfiguracionVariableFrontComponent,
        ConfiguracionVariableFrontDetailComponent,
        ConfiguracionVariableFrontUpdateComponent,
        ConfiguracionVariableFrontDeleteDialogComponent,
        ConfiguracionVariableFrontDeletePopupComponent
    ],
    entryComponents: [
        ConfiguracionVariableFrontComponent,
        ConfiguracionVariableFrontUpdateComponent,
        ConfiguracionVariableFrontDeleteDialogComponent,
        ConfiguracionVariableFrontDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacionConfiguracionVariableFrontModule {}
