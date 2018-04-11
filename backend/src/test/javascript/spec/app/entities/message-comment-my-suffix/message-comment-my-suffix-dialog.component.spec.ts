/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { BackendTestModule } from '../../../test.module';
import { MessageCommentMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix-dialog.component';
import { MessageCommentMySuffixService } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix.service';
import { MessageCommentMySuffix } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix.model';
import { MessageMySuffixService } from '../../../../../../main/webapp/app/entities/message-my-suffix';

describe('Component Tests', () => {

    describe('MessageCommentMySuffix Management Dialog Component', () => {
        let comp: MessageCommentMySuffixDialogComponent;
        let fixture: ComponentFixture<MessageCommentMySuffixDialogComponent>;
        let service: MessageCommentMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [MessageCommentMySuffixDialogComponent],
                providers: [
                    MessageMySuffixService,
                    MessageCommentMySuffixService
                ]
            })
            .overrideTemplate(MessageCommentMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MessageCommentMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MessageCommentMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new MessageCommentMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.messageComment = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'messageCommentListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new MessageCommentMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.messageComment = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'messageCommentListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
