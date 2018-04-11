import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MessageComment } from './message-comment.model';
import { MessageCommentPopupService } from './message-comment-popup.service';
import { MessageCommentService } from './message-comment.service';

@Component({
    selector: 'jhi-message-comment-delete-dialog',
    templateUrl: './message-comment-delete-dialog.component.html'
})
export class MessageCommentDeleteDialogComponent {

    messageComment: MessageComment;

    constructor(
        private messageCommentService: MessageCommentService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.messageCommentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'messageCommentListModification',
                content: 'Deleted an messageComment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-message-comment-delete-popup',
    template: ''
})
export class MessageCommentDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private messageCommentPopupService: MessageCommentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.messageCommentPopupService
                .open(MessageCommentDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
