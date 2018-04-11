/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { BackendTestModule } from '../../../test.module';
import { DaySechudlerInfoMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix-delete-dialog.component';
import { DaySechudlerInfoMySuffixService } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.service';

describe('Component Tests', () => {

    describe('DaySechudlerInfoMySuffix Management Delete Component', () => {
        let comp: DaySechudlerInfoMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<DaySechudlerInfoMySuffixDeleteDialogComponent>;
        let service: DaySechudlerInfoMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [DaySechudlerInfoMySuffixDeleteDialogComponent],
                providers: [
                    DaySechudlerInfoMySuffixService
                ]
            })
            .overrideTemplate(DaySechudlerInfoMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DaySechudlerInfoMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DaySechudlerInfoMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
