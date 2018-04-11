import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TuanMemberMySuffix } from './tuan-member-my-suffix.model';
import { TuanMemberMySuffixService } from './tuan-member-my-suffix.service';

@Component({
    selector: 'jhi-tuan-member-my-suffix-detail',
    templateUrl: './tuan-member-my-suffix-detail.component.html'
})
export class TuanMemberMySuffixDetailComponent implements OnInit, OnDestroy {

    tuanMember: TuanMemberMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tuanMemberService: TuanMemberMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTuanMembers();
    }

    load(id) {
        this.tuanMemberService.find(id)
            .subscribe((tuanMemberResponse: HttpResponse<TuanMemberMySuffix>) => {
                this.tuanMember = tuanMemberResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTuanMembers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tuanMemberListModification',
            (response) => this.load(this.tuanMember.id)
        );
    }
}
