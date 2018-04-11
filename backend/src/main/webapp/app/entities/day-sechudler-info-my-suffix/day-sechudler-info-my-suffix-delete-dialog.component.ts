import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DaySechudlerInfoMySuffix } from './day-sechudler-info-my-suffix.model';
import { DaySechudlerInfoMySuffixPopupService } from './day-sechudler-info-my-suffix-popup.service';
import { DaySechudlerInfoMySuffixService } from './day-sechudler-info-my-suffix.service';

@Component({
    selector: 'jhi-day-sechudler-info-my-suffix-delete-dialog',
    templateUrl: './day-sechudler-info-my-suffix-delete-dialog.component.html'
})
export class DaySechudlerInfoMySuffixDeleteDialogComponent {

    daySechudlerInfo: DaySechudlerInfoMySuffix;

    constructor(
        private daySechudlerInfoService: DaySechudlerInfoMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.daySechudlerInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'daySechudlerInfoListModification',
                content: 'Deleted an daySechudlerInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-day-sechudler-info-my-suffix-delete-popup',
    template: ''
})
export class DaySechudlerInfoMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private daySechudlerInfoPopupService: DaySechudlerInfoMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.daySechudlerInfoPopupService
                .open(DaySechudlerInfoMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
