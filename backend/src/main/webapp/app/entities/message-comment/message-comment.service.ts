import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { MessageComment } from './message-comment.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MessageComment>;

@Injectable()
export class MessageCommentService {

    private resourceUrl =  SERVER_API_URL + 'api/message-comments';

    constructor(private http: HttpClient) { }

    create(messageComment: MessageComment): Observable<EntityResponseType> {
        const copy = this.convert(messageComment);
        return this.http.post<MessageComment>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(messageComment: MessageComment): Observable<EntityResponseType> {
        const copy = this.convert(messageComment);
        return this.http.put<MessageComment>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MessageComment>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MessageComment[]>> {
        const options = createRequestOption(req);
        return this.http.get<MessageComment[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MessageComment[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MessageComment = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MessageComment[]>): HttpResponse<MessageComment[]> {
        const jsonResponse: MessageComment[] = res.body;
        const body: MessageComment[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MessageComment.
     */
    private convertItemFromServer(messageComment: MessageComment): MessageComment {
        const copy: MessageComment = Object.assign({}, messageComment);
        return copy;
    }

    /**
     * Convert a MessageComment to a JSON which can be sent to the server.
     */
    private convert(messageComment: MessageComment): MessageComment {
        const copy: MessageComment = Object.assign({}, messageComment);
        return copy;
    }
}
