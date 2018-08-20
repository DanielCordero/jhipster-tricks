import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVariableFront } from 'app/shared/model/variable-front.model';

type EntityResponseType = HttpResponse<IVariableFront>;
type EntityArrayResponseType = HttpResponse<IVariableFront[]>;

@Injectable({ providedIn: 'root' })
export class VariableFrontService {
    private resourceUrl = SERVER_API_URL + 'api/variables';

    constructor(private http: HttpClient) {}

    create(variable: IVariableFront): Observable<EntityResponseType> {
        return this.http.post<IVariableFront>(this.resourceUrl, variable, { observe: 'response' });
    }

    update(variable: IVariableFront): Observable<EntityResponseType> {
        return this.http.put<IVariableFront>(this.resourceUrl, variable, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVariableFront>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVariableFront[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
