import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MessageCommentMySuffix } from './message-comment-my-suffix.model';
import { MessageCommentMySuffixService } from './message-comment-my-suffix.service';

@Component({
    selector: 'jhi-message-comment-my-suffix-detail',
    templateUrl: './message-comment-my-suffix-detail.component.html'
})
export class MessageCommentMySuffixDetailComponent implements OnInit, OnDestroy {

    messageComment: MessageCommentMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private messageCommentService: MessageCommentMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMessageComments();
    }

    load(id) {
        this.messageCommentService.find(id)
            .subscribe((messageCommentResponse: HttpResponse<MessageCommentMySuffix>) => {
                this.messageComment = messageCommentResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMessageComments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'messageCommentListModification',
            (response) => this.load(this.messageComment.id)
        );
    }
}
