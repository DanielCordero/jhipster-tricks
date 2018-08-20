import { IConfiguracionVariableFront } from 'app/shared/model//configuracion-variable-front.model';

export interface IVariableFront {
    id?: number;
    nombre?: string;
    descripcion?: string;
    campo?: string;
    cuak?: number;
    configuracionVariables?: IConfiguracionVariableFront[];
}

export class VariableFront implements IVariableFront {
    constructor(
        public id?: number,
        public nombre?: string,
        public descripcion?: string,
        public campo?: string,
        public cuak?: number,
        public configuracionVariables?: IConfiguracionVariableFront[]
    ) {}
}
