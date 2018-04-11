import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TuanInfoMySuffix } from './tuan-info-my-suffix.model';
import { TuanInfoMySuffixPopupService } from './tuan-info-my-suffix-popup.service';
import { TuanInfoMySuffixService } from './tuan-info-my-suffix.service';

@Component({
    selector: 'jhi-tuan-info-my-suffix-dialog',
    templateUrl: './tuan-info-my-suffix-dialog.component.html'
})
export class TuanInfoMySuffixDialogComponent implements OnInit {

    tuanInfo: TuanInfoMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tuanInfoService: TuanInfoMySuffixService,
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
        if (this.tuanInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tuanInfoService.update(this.tuanInfo));
        } else {
            this.subscribeToSaveResponse(
                this.tuanInfoService.create(this.tuanInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TuanInfoMySuffix>>) {
        result.subscribe((res: HttpResponse<TuanInfoMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TuanInfoMySuffix) {
        this.eventManager.broadcast({ name: 'tuanInfoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-tuan-info-my-suffix-popup',
    template: ''
})
export class TuanInfoMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tuanInfoPopupService: TuanInfoMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tuanInfoPopupService
                    .open(TuanInfoMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tuanInfoPopupService
                    .open(TuanInfoMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
