import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MessageCommentMySuffix } from './message-comment-my-suffix.model';
import { MessageCommentMySuffixService } from './message-comment-my-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-message-comment-my-suffix',
    templateUrl: './message-comment-my-suffix.component.html'
})
export class MessageCommentMySuffixComponent implements OnInit, OnDestroy {
messageComments: MessageCommentMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private messageCommentService: MessageCommentMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.messageCommentService.query().subscribe(
            (res: HttpResponse<MessageCommentMySuffix[]>) => {
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

    trackId(index: number, item: MessageCommentMySuffix) {
        return item.id;
    }
    registerChangeInMessageComments() {
        this.eventSubscriber = this.eventManager.subscribe('messageCommentListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
