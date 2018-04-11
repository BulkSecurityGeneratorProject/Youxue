import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { DaySechudlerInfoMySuffix } from './day-sechudler-info-my-suffix.model';
import { DaySechudlerInfoMySuffixPopupService } from './day-sechudler-info-my-suffix-popup.service';
import { DaySechudlerInfoMySuffixService } from './day-sechudler-info-my-suffix.service';
import { TuanInfoMySuffix, TuanInfoMySuffixService } from '../tuan-info-my-suffix';

@Component({
    selector: 'jhi-day-sechudler-info-my-suffix-dialog',
    templateUrl: './day-sechudler-info-my-suffix-dialog.component.html'
})
export class DaySechudlerInfoMySuffixDialogComponent implements OnInit {

    daySechudlerInfo: DaySechudlerInfoMySuffix;
    isSaving: boolean;

    tuaninfos: TuanInfoMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private daySechudlerInfoService: DaySechudlerInfoMySuffixService,
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
        if (this.daySechudlerInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.daySechudlerInfoService.update(this.daySechudlerInfo));
        } else {
            this.subscribeToSaveResponse(
                this.daySechudlerInfoService.create(this.daySechudlerInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<DaySechudlerInfoMySuffix>>) {
        result.subscribe((res: HttpResponse<DaySechudlerInfoMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: DaySechudlerInfoMySuffix) {
        this.eventManager.broadcast({ name: 'daySechudlerInfoListModification', content: 'OK'});
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
    selector: 'jhi-day-sechudler-info-my-suffix-popup',
    template: ''
})
export class DaySechudlerInfoMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private daySechudlerInfoPopupService: DaySechudlerInfoMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.daySechudlerInfoPopupService
                    .open(DaySechudlerInfoMySuffixDialogComponent as Component, params['id']);
            } else {
                this.daySechudlerInfoPopupService
                    .open(DaySechudlerInfoMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
