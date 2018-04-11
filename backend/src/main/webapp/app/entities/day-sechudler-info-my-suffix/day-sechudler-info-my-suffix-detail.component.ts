import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { DaySechudlerInfoMySuffix } from './day-sechudler-info-my-suffix.model';
import { DaySechudlerInfoMySuffixService } from './day-sechudler-info-my-suffix.service';

@Component({
    selector: 'jhi-day-sechudler-info-my-suffix-detail',
    templateUrl: './day-sechudler-info-my-suffix-detail.component.html'
})
export class DaySechudlerInfoMySuffixDetailComponent implements OnInit, OnDestroy {

    daySechudlerInfo: DaySechudlerInfoMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private daySechudlerInfoService: DaySechudlerInfoMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDaySechudlerInfos();
    }

    load(id) {
        this.daySechudlerInfoService.find(id)
            .subscribe((daySechudlerInfoResponse: HttpResponse<DaySechudlerInfoMySuffix>) => {
                this.daySechudlerInfo = daySechudlerInfoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDaySechudlerInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'daySechudlerInfoListModification',
            (response) => this.load(this.daySechudlerInfo.id)
        );
    }
}
