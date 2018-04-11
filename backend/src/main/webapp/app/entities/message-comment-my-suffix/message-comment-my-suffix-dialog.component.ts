import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MessageCommentMySuffix } from './message-comment-my-suffix.model';
import { MessageCommentMySuffixPopupService } from './message-comment-my-suffix-popup.service';
import { MessageCommentMySuffixService } from './message-comment-my-suffix.service';
import { MessageMySuffix, MessageMySuffixService } from '../message-my-suffix';

@Component({
    selector: 'jhi-message-comment-my-suffix-dialog',
    templateUrl: './message-comment-my-suffix-dialog.component.html'
})
export class MessageCommentMySuffixDialogComponent implements OnInit {

    messageComment: MessageCommentMySuffix;
    isSaving: boolean;

    messages: MessageMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private messageCommentService: MessageCommentMySuffixService,
        private messageService: MessageMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.messageService.query()
            .subscribe((res: HttpResponse<MessageMySuffix[]>) => { this.messages = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.messageComment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.messageCommentService.update(this.messageComment));
        } else {
            this.subscribeToSaveResponse(
                this.messageCommentService.create(this.messageComment));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MessageCommentMySuffix>>) {
        result.subscribe((res: HttpResponse<MessageCommentMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MessageCommentMySuffix) {
        this.eventManager.broadcast({ name: 'messageCommentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackMessageById(index: number, item: MessageMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-message-comment-my-suffix-popup',
    template: ''
})
export class MessageCommentMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private messageCommentPopupService: MessageCommentMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.messageCommentPopupService
                    .open(MessageCommentMySuffixDialogComponent as Component, params['id']);
            } else {
                this.messageCommentPopupService
                    .open(MessageCommentMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
