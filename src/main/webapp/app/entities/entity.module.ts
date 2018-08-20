import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AplicacionVariableFrontModule } from './variable-front/variable-front.module';
import { AplicacionConfiguracionVariableFrontModule } from './configuracion-variable-front/configuracion-variable-front.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        AplicacionVariableFrontModule,
        AplicacionConfiguracionVariableFrontModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AplicacionEntityModule {}
