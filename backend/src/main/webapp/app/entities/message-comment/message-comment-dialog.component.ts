import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MessageComment } from './message-comment.model';
import { MessageCommentPopupService } from './message-comment-popup.service';
import { MessageCommentService } from './message-comment.service';

@Component({
    selector: 'jhi-message-comment-dialog',
    templateUrl: './message-comment-dialog.component.html'
})
export class MessageCommentDialogComponent implements OnInit {

    messageComment: MessageComment;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private messageCommentService: MessageCommentService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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

    private subscribeToSaveResponse(result: Observable<HttpResponse<MessageComment>>) {
        result.subscribe((res: HttpResponse<MessageComment>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MessageComment) {
        this.eventManager.broadcast({ name: 'messageCommentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-message-comment-popup',
    template: ''
})
export class MessageCommentPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private messageCommentPopupService: MessageCommentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.messageCommentPopupService
                    .open(MessageCommentDialogComponent as Component, params['id']);
            } else {
                this.messageCommentPopupService
                    .open(MessageCommentDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
