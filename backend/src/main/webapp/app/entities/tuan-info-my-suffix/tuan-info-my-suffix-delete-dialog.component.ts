import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TuanInfoMySuffix } from './tuan-info-my-suffix.model';
import { TuanInfoMySuffixPopupService } from './tuan-info-my-suffix-popup.service';
import { TuanInfoMySuffixService } from './tuan-info-my-suffix.service';

@Component({
    selector: 'jhi-tuan-info-my-suffix-delete-dialog',
    templateUrl: './tuan-info-my-suffix-delete-dialog.component.html'
})
export class TuanInfoMySuffixDeleteDialogComponent {

    tuanInfo: TuanInfoMySuffix;

    constructor(
        private tuanInfoService: TuanInfoMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tuanInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tuanInfoListModification',
                content: 'Deleted an tuanInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tuan-info-my-suffix-delete-popup',
    template: ''
})
export class TuanInfoMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tuanInfoPopupService: TuanInfoMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tuanInfoPopupService
                .open(TuanInfoMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
