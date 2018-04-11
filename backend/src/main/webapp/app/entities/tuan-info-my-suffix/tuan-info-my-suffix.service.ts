import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { TuanInfoMySuffix } from './tuan-info-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TuanInfoMySuffix>;

@Injectable()
export class TuanInfoMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/tuan-infos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(tuanInfo: TuanInfoMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tuanInfo);
        return this.http.post<TuanInfoMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(tuanInfo: TuanInfoMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tuanInfo);
        return this.http.put<TuanInfoMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TuanInfoMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TuanInfoMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<TuanInfoMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TuanInfoMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TuanInfoMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TuanInfoMySuffix[]>): HttpResponse<TuanInfoMySuffix[]> {
        const jsonResponse: TuanInfoMySuffix[] = res.body;
        const body: TuanInfoMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TuanInfoMySuffix.
     */
    private convertItemFromServer(tuanInfo: TuanInfoMySuffix): TuanInfoMySuffix {
        const copy: TuanInfoMySuffix = Object.assign({}, tuanInfo);
        copy.tuanStartTime = this.dateUtils
            .convertDateTimeFromServer(tuanInfo.tuanStartTime);
        copy.tuanEndTime = this.dateUtils
            .convertDateTimeFromServer(tuanInfo.tuanEndTime);
        copy.tuanCreatTime = this.dateUtils
            .convertDateTimeFromServer(tuanInfo.tuanCreatTime);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(tuanInfo.createDate);
        copy.updateDate = this.dateUtils
            .convertDateTimeFromServer(tuanInfo.updateDate);
        return copy;
    }

    /**
     * Convert a TuanInfoMySuffix to a JSON which can be sent to the server.
     */
    private convert(tuanInfo: TuanInfoMySuffix): TuanInfoMySuffix {
        const copy: TuanInfoMySuffix = Object.assign({}, tuanInfo);

        copy.tuanStartTime = this.dateUtils.toDate(tuanInfo.tuanStartTime);

        copy.tuanEndTime = this.dateUtils.toDate(tuanInfo.tuanEndTime);

        copy.tuanCreatTime = this.dateUtils.toDate(tuanInfo.tuanCreatTime);

        copy.createDate = this.dateUtils.toDate(tuanInfo.createDate);

        copy.updateDate = this.dateUtils.toDate(tuanInfo.updateDate);
        return copy;
    }
}
