import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TuanMemberMySuffix } from './tuan-member-my-suffix.model';
import { TuanMemberMySuffixPopupService } from './tuan-member-my-suffix-popup.service';
import { TuanMemberMySuffixService } from './tuan-member-my-suffix.service';

@Component({
    selector: 'jhi-tuan-member-my-suffix-delete-dialog',
    templateUrl: './tuan-member-my-suffix-delete-dialog.component.html'
})
export class TuanMemberMySuffixDeleteDialogComponent {

    tuanMember: TuanMemberMySuffix;

    constructor(
        private tuanMemberService: TuanMemberMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tuanMemberService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tuanMemberListModification',
                content: 'Deleted an tuanMember'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tuan-member-my-suffix-delete-popup',
    template: ''
})
export class TuanMemberMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tuanMemberPopupService: TuanMemberMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tuanMemberPopupService
                .open(TuanMemberMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
