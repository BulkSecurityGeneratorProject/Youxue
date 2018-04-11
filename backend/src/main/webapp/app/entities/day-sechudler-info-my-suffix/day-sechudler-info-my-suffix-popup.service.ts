import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { DaySechudlerInfoMySuffix } from './day-sechudler-info-my-suffix.model';
import { DaySechudlerInfoMySuffixService } from './day-sechudler-info-my-suffix.service';

@Injectable()
export class DaySechudlerInfoMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private daySechudlerInfoService: DaySechudlerInfoMySuffixService

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
                this.daySechudlerInfoService.find(id)
                    .subscribe((daySechudlerInfoResponse: HttpResponse<DaySechudlerInfoMySuffix>) => {
                        const daySechudlerInfo: DaySechudlerInfoMySuffix = daySechudlerInfoResponse.body;
                        daySechudlerInfo.time = this.datePipe
                            .transform(daySechudlerInfo.time, 'yyyy-MM-ddTHH:mm:ss');
                        daySechudlerInfo.createDate = this.datePipe
                            .transform(daySechudlerInfo.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        daySechudlerInfo.updateDate = this.datePipe
                            .transform(daySechudlerInfo.updateDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.daySechudlerInfoModalRef(component, daySechudlerInfo);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.daySechudlerInfoModalRef(component, new DaySechudlerInfoMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    daySechudlerInfoModalRef(component: Component, daySechudlerInfo: DaySechudlerInfoMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.daySechudlerInfo = daySechudlerInfo;
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
