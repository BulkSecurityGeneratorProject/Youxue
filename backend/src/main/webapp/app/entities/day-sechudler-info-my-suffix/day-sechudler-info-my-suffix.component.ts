import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { DaySechudlerInfoMySuffix } from './day-sechudler-info-my-suffix.model';
import { DaySechudlerInfoMySuffixService } from './day-sechudler-info-my-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-day-sechudler-info-my-suffix',
    templateUrl: './day-sechudler-info-my-suffix.component.html'
})
export class DaySechudlerInfoMySuffixComponent implements OnInit, OnDestroy {
daySechudlerInfos: DaySechudlerInfoMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private daySechudlerInfoService: DaySechudlerInfoMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.daySechudlerInfoService.query().subscribe(
            (res: HttpResponse<DaySechudlerInfoMySuffix[]>) => {
                this.daySechudlerInfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInDaySechudlerInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: DaySechudlerInfoMySuffix) {
        return item.id;
    }
    registerChangeInDaySechudlerInfos() {
        this.eventSubscriber = this.eventManager.subscribe('daySechudlerInfoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
