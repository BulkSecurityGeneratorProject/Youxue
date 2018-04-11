/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { BackendTestModule } from '../../../test.module';
import { DaySechudlerInfoMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix-dialog.component';
import { DaySechudlerInfoMySuffixService } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.service';
import { DaySechudlerInfoMySuffix } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.model';
import { TuanInfoMySuffixService } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix';

describe('Component Tests', () => {

    describe('DaySechudlerInfoMySuffix Management Dialog Component', () => {
        let comp: DaySechudlerInfoMySuffixDialogComponent;
        let fixture: ComponentFixture<DaySechudlerInfoMySuffixDialogComponent>;
        let service: DaySechudlerInfoMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [DaySechudlerInfoMySuffixDialogComponent],
                providers: [
                    TuanInfoMySuffixService,
                    DaySechudlerInfoMySuffixService
                ]
            })
            .overrideTemplate(DaySechudlerInfoMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DaySechudlerInfoMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DaySechudlerInfoMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new DaySechudlerInfoMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.daySechudlerInfo = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'daySechudlerInfoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new DaySechudlerInfoMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.daySechudlerInfo = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'daySechudlerInfoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
