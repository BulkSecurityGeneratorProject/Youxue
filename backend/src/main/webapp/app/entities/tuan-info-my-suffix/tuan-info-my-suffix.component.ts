import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TuanInfoMySuffix } from './tuan-info-my-suffix.model';
import { TuanInfoMySuffixService } from './tuan-info-my-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-tuan-info-my-suffix',
    templateUrl: './tuan-info-my-suffix.component.html'
})
export class TuanInfoMySuffixComponent implements OnInit, OnDestroy {
tuanInfos: TuanInfoMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tuanInfoService: TuanInfoMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.tuanInfoService.query().subscribe(
            (res: HttpResponse<TuanInfoMySuffix[]>) => {
                this.tuanInfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTuanInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TuanInfoMySuffix) {
        return item.id;
    }
    registerChangeInTuanInfos() {
        this.eventSubscriber = this.eventManager.subscribe('tuanInfoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
