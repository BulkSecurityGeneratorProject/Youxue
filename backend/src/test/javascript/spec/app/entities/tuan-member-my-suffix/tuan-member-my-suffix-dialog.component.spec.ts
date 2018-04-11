/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { BackendTestModule } from '../../../test.module';
import { TuanMemberMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix-dialog.component';
import { TuanMemberMySuffixService } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix.service';
import { TuanMemberMySuffix } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix.model';
import { TuanInfoMySuffixService } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix';

describe('Component Tests', () => {

    describe('TuanMemberMySuffix Management Dialog Component', () => {
        let comp: TuanMemberMySuffixDialogComponent;
        let fixture: ComponentFixture<TuanMemberMySuffixDialogComponent>;
        let service: TuanMemberMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [TuanMemberMySuffixDialogComponent],
                providers: [
                    TuanInfoMySuffixService,
                    TuanMemberMySuffixService
                ]
            })
            .overrideTemplate(TuanMemberMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TuanMemberMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TuanMemberMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TuanMemberMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.tuanMember = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'tuanMemberListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TuanMemberMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.tuanMember = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'tuanMemberListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
