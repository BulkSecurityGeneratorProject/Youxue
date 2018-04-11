import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { TuanMemberMySuffix } from './tuan-member-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TuanMemberMySuffix>;

@Injectable()
export class TuanMemberMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/tuan-members';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(tuanMember: TuanMemberMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tuanMember);
        return this.http.post<TuanMemberMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(tuanMember: TuanMemberMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tuanMember);
        return this.http.put<TuanMemberMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TuanMemberMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TuanMemberMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<TuanMemberMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TuanMemberMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TuanMemberMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TuanMemberMySuffix[]>): HttpResponse<TuanMemberMySuffix[]> {
        const jsonResponse: TuanMemberMySuffix[] = res.body;
        const body: TuanMemberMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TuanMemberMySuffix.
     */
    private convertItemFromServer(tuanMember: TuanMemberMySuffix): TuanMemberMySuffix {
        const copy: TuanMemberMySuffix = Object.assign({}, tuanMember);
        copy.joinTime = this.dateUtils
            .convertDateTimeFromServer(tuanMember.joinTime);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(tuanMember.createDate);
        copy.updateDate = this.dateUtils
            .convertDateTimeFromServer(tuanMember.updateDate);
        return copy;
    }

    /**
     * Convert a TuanMemberMySuffix to a JSON which can be sent to the server.
     */
    private convert(tuanMember: TuanMemberMySuffix): TuanMemberMySuffix {
        const copy: TuanMemberMySuffix = Object.assign({}, tuanMember);

        copy.joinTime = this.dateUtils.toDate(tuanMember.joinTime);

        copy.createDate = this.dateUtils.toDate(tuanMember.createDate);

        copy.updateDate = this.dateUtils.toDate(tuanMember.updateDate);
        return copy;
    }
}
