import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MessageComment } from './message-comment.model';
import { MessageCommentService } from './message-comment.service';

@Component({
    selector: 'jhi-message-comment-detail',
    templateUrl: './message-comment-detail.component.html'
})
export class MessageCommentDetailComponent implements OnInit, OnDestroy {

    messageComment: MessageComment;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private messageCommentService: MessageCommentService,
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
            .subscribe((messageCommentResponse: HttpResponse<MessageComment>) => {
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
