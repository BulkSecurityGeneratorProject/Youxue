import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MessageCommentMySuffix } from './message-comment-my-suffix.model';
import { MessageCommentMySuffixPopupService } from './message-comment-my-suffix-popup.service';
import { MessageCommentMySuffixService } from './message-comment-my-suffix.service';

@Component({
    selector: 'jhi-message-comment-my-suffix-delete-dialog',
    templateUrl: './message-comment-my-suffix-delete-dialog.component.html'
})
export class MessageCommentMySuffixDeleteDialogComponent {

    messageComment: MessageCommentMySuffix;

    constructor(
        private messageCommentService: MessageCommentMySuffixService,
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
    selector: 'jhi-message-comment-my-suffix-delete-popup',
    template: ''
})
export class MessageCommentMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private messageCommentPopupService: MessageCommentMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.messageCommentPopupService
                .open(MessageCommentMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
