import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MessageComment } from './message-comment.model';
import { MessageCommentService } from './message-comment.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-message-comment',
    templateUrl: './message-comment.component.html'
})
export class MessageCommentComponent implements OnInit, OnDestroy {
messageComments: MessageComment[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private messageCommentService: MessageCommentService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.messageCommentService.query().subscribe(
            (res: HttpResponse<MessageComment[]>) => {
                this.messageComments = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInMessageComments();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: MessageComment) {
        return item.id;
    }
    registerChangeInMessageComments() {
        this.eventSubscriber = this.eventManager.subscribe('messageCommentListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
