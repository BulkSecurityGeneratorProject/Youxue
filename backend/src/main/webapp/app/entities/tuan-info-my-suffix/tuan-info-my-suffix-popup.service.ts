import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { TuanInfoMySuffix } from './tuan-info-my-suffix.model';
import { TuanInfoMySuffixService } from './tuan-info-my-suffix.service';

@Injectable()
export class TuanInfoMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private tuanInfoService: TuanInfoMySuffixService

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
                this.tuanInfoService.find(id)
                    .subscribe((tuanInfoResponse: HttpResponse<TuanInfoMySuffix>) => {
                        const tuanInfo: TuanInfoMySuffix = tuanInfoResponse.body;
                        tuanInfo.tuanStartTime = this.datePipe
                            .transform(tuanInfo.tuanStartTime, 'yyyy-MM-ddTHH:mm:ss');
                        tuanInfo.tuanEndTime = this.datePipe
                            .transform(tuanInfo.tuanEndTime, 'yyyy-MM-ddTHH:mm:ss');
                        tuanInfo.tuanCreatTime = this.datePipe
                            .transform(tuanInfo.tuanCreatTime, 'yyyy-MM-ddTHH:mm:ss');
                        tuanInfo.createDate = this.datePipe
                            .transform(tuanInfo.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        tuanInfo.updateDate = this.datePipe
                            .transform(tuanInfo.updateDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.tuanInfoModalRef(component, tuanInfo);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.tuanInfoModalRef(component, new TuanInfoMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    tuanInfoModalRef(component: Component, tuanInfo: TuanInfoMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tuanInfo = tuanInfo;
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
