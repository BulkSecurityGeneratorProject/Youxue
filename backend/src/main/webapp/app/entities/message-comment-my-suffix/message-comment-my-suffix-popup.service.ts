import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { MessageCommentMySuffix } from './message-comment-my-suffix.model';
import { MessageCommentMySuffixService } from './message-comment-my-suffix.service';

@Injectable()
export class MessageCommentMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private messageCommentService: MessageCommentMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.messageCommentService.find(id)
                    .subscribe((messageCommentResponse: HttpResponse<MessageCommentMySuffix>) => {
                        const messageComment: MessageCommentMySuffix = messageCommentResponse.body;
                        messageComment.createDate = this.datePipe
                            .transform(messageComment.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        messageComment.updateDate = this.datePipe
                            .transform(messageComment.updateDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.messageCommentModalRef(component, messageComment);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.messageCommentModalRef(component, new MessageCommentMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    messageCommentModalRef(component: Component, messageComment: MessageCommentMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.messageComment = messageComment;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
