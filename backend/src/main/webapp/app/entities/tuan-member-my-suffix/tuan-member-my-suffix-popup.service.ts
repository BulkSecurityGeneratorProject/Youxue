import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { TuanMemberMySuffix } from './tuan-member-my-suffix.model';
import { TuanMemberMySuffixService } from './tuan-member-my-suffix.service';

@Injectable()
export class TuanMemberMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private tuanMemberService: TuanMemberMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.tuanMemberService.find(id)
                    .subscribe((tuanMemberResponse: HttpResponse<TuanMemberMySuffix>) => {
                        const tuanMember: TuanMemberMySuffix = tuanMemberResponse.body;
                        tuanMember.joinTime = this.datePipe
                            .transform(tuanMember.joinTime, 'yyyy-MM-ddTHH:mm:ss');
                        tuanMember.createDate = this.datePipe
                            .transform(tuanMember.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        tuanMember.updateDate = this.datePipe
                            .transform(tuanMember.updateDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.tuanMemberModalRef(component, tuanMember);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.tuanMemberModalRef(component, new TuanMemberMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    tuanMemberModalRef(component: Component, tuanMember: TuanMemberMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tuanMember = tuanMember;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
