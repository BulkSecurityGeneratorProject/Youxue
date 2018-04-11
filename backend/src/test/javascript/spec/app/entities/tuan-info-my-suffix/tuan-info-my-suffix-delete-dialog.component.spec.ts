/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { BackendTestModule } from '../../../test.module';
import { TuanInfoMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix/tuan-info-my-suffix-delete-dialog.component';
import { TuanInfoMySuffixService } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix/tuan-info-my-suffix.service';

describe('Component Tests', () => {

    describe('TuanInfoMySuffix Management Delete Component', () => {
        let comp: TuanInfoMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TuanInfoMySuffixDeleteDialogComponent>;
        let service: TuanInfoMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [TuanInfoMySuffixDeleteDialogComponent],
                providers: [
                    TuanInfoMySuffixService
                ]
            })
            .overrideTemplate(TuanInfoMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TuanInfoMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TuanInfoMySuffixService);
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
