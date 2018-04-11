import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { JobMySuffix } from './job-my-suffix.model';
import { JobMySuffixPopupService } from './job-my-suffix-popup.service';
import { JobMySuffixService } from './job-my-suffix.service';

@Component({
    selector: 'jhi-job-my-suffix-dialog',
    templateUrl: './job-my-suffix-dialog.component.html'
})
export class JobMySuffixDialogComponent implements OnInit {

    job: JobMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jobService: JobMySuffixService,
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
        if (this.job.id !== undefined) {
            this.subscribeToSaveResponse(
                this.jobService.update(this.job));
        } else {
            this.subscribeToSaveResponse(
                this.jobService.create(this.job));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<JobMySuffix>>) {
        result.subscribe((res: HttpResponse<JobMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: JobMySuffix) {
        this.eventManager.broadcast({ name: 'jobListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-job-my-suffix-popup',
    template: ''
})
export class JobMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private jobPopupService: JobMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.jobPopupService
                    .open(JobMySuffixDialogComponent as Component, params['id']);
            } else {
                this.jobPopupService
                    .open(JobMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
