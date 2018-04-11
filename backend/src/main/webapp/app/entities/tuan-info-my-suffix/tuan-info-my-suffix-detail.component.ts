import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TuanInfoMySuffix } from './tuan-info-my-suffix.model';
import { TuanInfoMySuffixService } from './tuan-info-my-suffix.service';

@Component({
    selector: 'jhi-tuan-info-my-suffix-detail',
    templateUrl: './tuan-info-my-suffix-detail.component.html'
})
export class TuanInfoMySuffixDetailComponent implements OnInit, OnDestroy {

    tuanInfo: TuanInfoMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tuanInfoService: TuanInfoMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTuanInfos();
    }

    load(id) {
        this.tuanInfoService.find(id)
            .subscribe((tuanInfoResponse: HttpResponse<TuanInfoMySuffix>) => {
                this.tuanInfo = tuanInfoResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTuanInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tuanInfoListModification',
            (response) => this.load(this.tuanInfo.id)
        );
    }
}
