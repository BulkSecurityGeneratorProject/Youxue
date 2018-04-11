import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { DaySechudlerInfoMySuffix } from './day-sechudler-info-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<DaySechudlerInfoMySuffix>;

@Injectable()
export class DaySechudlerInfoMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/day-sechudler-infos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(daySechudlerInfo: DaySechudlerInfoMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(daySechudlerInfo);
        return this.http.post<DaySechudlerInfoMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(daySechudlerInfo: DaySechudlerInfoMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(daySechudlerInfo);
        return this.http.put<DaySechudlerInfoMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<DaySechudlerInfoMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<DaySechudlerInfoMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<DaySechudlerInfoMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DaySechudlerInfoMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: DaySechudlerInfoMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<DaySechudlerInfoMySuffix[]>): HttpResponse<DaySechudlerInfoMySuffix[]> {
        const jsonResponse: DaySechudlerInfoMySuffix[] = res.body;
        const body: DaySechudlerInfoMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to DaySechudlerInfoMySuffix.
     */
    private convertItemFromServer(daySechudlerInfo: DaySechudlerInfoMySuffix): DaySechudlerInfoMySuffix {
        const copy: DaySechudlerInfoMySuffix = Object.assign({}, daySechudlerInfo);
        copy.time = this.dateUtils
            .convertDateTimeFromServer(daySechudlerInfo.time);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(daySechudlerInfo.createDate);
        copy.updateDate = this.dateUtils
            .convertDateTimeFromServer(daySechudlerInfo.updateDate);
        return copy;
    }

    /**
     * Convert a DaySechudlerInfoMySuffix to a JSON which can be sent to the server.
     */
    private convert(daySechudlerInfo: DaySechudlerInfoMySuffix): DaySechudlerInfoMySuffix {
        const copy: DaySechudlerInfoMySuffix = Object.assign({}, daySechudlerInfo);

        copy.time = this.dateUtils.toDate(daySechudlerInfo.time);

        copy.createDate = this.dateUtils.toDate(daySechudlerInfo.createDate);

        copy.updateDate = this.dateUtils.toDate(daySechudlerInfo.updateDate);
        return copy;
    }
}
