import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MessageMySuffix } from './message-my-suffix.model';
import { MessageMySuffixPopupService } from './message-my-suffix-popup.service';
import { MessageMySuffixService } from './message-my-suffix.service';
import { TuanInfoMySuffix, TuanInfoMySuffixService } from '../tuan-info-my-suffix';

@Component({
    selector: 'jhi-message-my-suffix-dialog',
    templateUrl: './message-my-suffix-dialog.component.html'
})
export class MessageMySuffixDialogComponent implements OnInit {

    message: MessageMySuffix;
    isSaving: boolean;

    tuaninfos: TuanInfoMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private messageService: MessageMySuffixService,
        private tuanInfoService: TuanInfoMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.tuanInfoService.query()
            .subscribe((res: HttpResponse<TuanInfoMySuffix[]>) => { this.tuaninfos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.message.id !== undefined) {
            this.subscribeToSaveResponse(
                this.messageService.update(this.message));
        } else {
            this.subscribeToSaveResponse(
                this.messageService.create(this.message));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MessageMySuffix>>) {
        result.subscribe((res: HttpResponse<MessageMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MessageMySuffix) {
        this.eventManager.broadcast({ name: 'messageListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTuanInfoById(index: number, item: TuanInfoMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-message-my-suffix-popup',
    template: ''
})
export class MessageMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private messagePopupService: MessageMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.messagePopupService
                    .open(MessageMySuffixDialogComponent as Component, params['id']);
            } else {
                this.messagePopupService
                    .open(MessageMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
