import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { MessageCommentMySuffix } from './message-comment-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MessageCommentMySuffix>;

@Injectable()
export class MessageCommentMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/message-comments';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(messageComment: MessageCommentMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(messageComment);
        return this.http.post<MessageCommentMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(messageComment: MessageCommentMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(messageComment);
        return this.http.put<MessageCommentMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MessageCommentMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MessageCommentMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<MessageCommentMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MessageCommentMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MessageCommentMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MessageCommentMySuffix[]>): HttpResponse<MessageCommentMySuffix[]> {
        const jsonResponse: MessageCommentMySuffix[] = res.body;
        const body: MessageCommentMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MessageCommentMySuffix.
     */
    private convertItemFromServer(messageComment: MessageCommentMySuffix): MessageCommentMySuffix {
        const copy: MessageCommentMySuffix = Object.assign({}, messageComment);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(messageComment.createDate);
        copy.updateDate = this.dateUtils
            .convertDateTimeFromServer(messageComment.updateDate);
        return copy;
    }

    /**
     * Convert a MessageCommentMySuffix to a JSON which can be sent to the server.
     */
    private convert(messageComment: MessageCommentMySuffix): MessageCommentMySuffix {
        const copy: MessageCommentMySuffix = Object.assign({}, messageComment);

        copy.createDate = this.dateUtils.toDate(messageComment.createDate);

        copy.updateDate = this.dateUtils.toDate(messageComment.updateDate);
        return copy;
    }
}
