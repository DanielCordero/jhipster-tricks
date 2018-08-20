import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';

type EntityResponseType = HttpResponse<IConfiguracionVariableFront>;
type EntityArrayResponseType = HttpResponse<IConfiguracionVariableFront[]>;

@Injectable({ providedIn: 'root' })
export class ConfiguracionVariableFrontService {
    private resourceUrl = SERVER_API_URL + 'api/configuracion-variables';

    constructor(private http: HttpClient) {}

    create(configuracionVariable: IConfiguracionVariableFront): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(configuracionVariable);
        return this.http
            .post<IConfiguracionVariableFront>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(configuracionVariable: IConfiguracionVariableFront): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(configuracionVariable);
        return this.http
            .put<IConfiguracionVariableFront>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IConfiguracionVariableFront>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IConfiguracionVariableFront[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(configuracionVariable: IConfiguracionVariableFront): IConfiguracionVariableFront {
        const copy: IConfiguracionVariableFront = Object.assign({}, configuracionVariable, {
            fecha:
                configuracionVariable.fecha != null && configuracionVariable.fecha.isValid()
                    ? configuracionVariable.fecha.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((configuracionVariable: IConfiguracionVariableFront) => {
            configuracionVariable.fecha = configuracionVariable.fecha != null ? moment(configuracionVariable.fecha) : null;
        });
        return res;
    }
}
