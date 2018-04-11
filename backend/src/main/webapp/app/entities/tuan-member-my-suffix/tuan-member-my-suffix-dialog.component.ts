import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TuanMemberMySuffix } from './tuan-member-my-suffix.model';
import { TuanMemberMySuffixPopupService } from './tuan-member-my-suffix-popup.service';
import { TuanMemberMySuffixService } from './tuan-member-my-suffix.service';
import { TuanInfoMySuffix, TuanInfoMySuffixService } from '../tuan-info-my-suffix';

@Component({
    selector: 'jhi-tuan-member-my-suffix-dialog',
    templateUrl: './tuan-member-my-suffix-dialog.component.html'
})
export class TuanMemberMySuffixDialogComponent implements OnInit {

    tuanMember: TuanMemberMySuffix;
    isSaving: boolean;

    tuaninfos: TuanInfoMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private tuanMemberService: TuanMemberMySuffixService,
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
        if (this.tuanMember.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tuanMemberService.update(this.tuanMember));
        } else {
            this.subscribeToSaveResponse(
                this.tuanMemberService.create(this.tuanMember));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TuanMemberMySuffix>>) {
        result.subscribe((res: HttpResponse<TuanMemberMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TuanMemberMySuffix) {
        this.eventManager.broadcast({ name: 'tuanMemberListModification', content: 'OK'});
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
    selector: 'jhi-tuan-member-my-suffix-popup',
    template: ''
})
export class TuanMemberMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tuanMemberPopupService: TuanMemberMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tuanMemberPopupService
                    .open(TuanMemberMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tuanMemberPopupService
                    .open(TuanMemberMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
