import { Moment } from 'moment';

export interface IConfiguracionVariableFront {
    id?: number;
    fecha?: Moment;
    veces?: number;
    otrocampo?: number;
    campoadicional?: string;
    variableId?: number;
}

export class ConfiguracionVariableFront implements IConfiguracionVariableFront {
    constructor(
        public id?: number,
        public fecha?: Moment,
        public veces?: number,
        public otrocampo?: number,
        public campoadicional?: string,
        public variableId?: number
    ) {}
}
