import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TuanMemberMySuffix } from './tuan-member-my-suffix.model';
import { TuanMemberMySuffixService } from './tuan-member-my-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-tuan-member-my-suffix',
    templateUrl: './tuan-member-my-suffix.component.html'
})
export class TuanMemberMySuffixComponent implements OnInit, OnDestroy {
tuanMembers: TuanMemberMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tuanMemberService: TuanMemberMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.tuanMemberService.query().subscribe(
            (res: HttpResponse<TuanMemberMySuffix[]>) => {
                this.tuanMembers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTuanMembers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TuanMemberMySuffix) {
        return item.id;
    }
    registerChangeInTuanMembers() {
        this.eventSubscriber = this.eventManager.subscribe('tuanMemberListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
