/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { BackendTestModule } from '../../../test.module';
import { MessageCommentMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix-delete-dialog.component';
import { MessageCommentMySuffixService } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix.service';

describe('Component Tests', () => {

    describe('MessageCommentMySuffix Management Delete Component', () => {
        let comp: MessageCommentMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<MessageCommentMySuffixDeleteDialogComponent>;
        let service: MessageCommentMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [MessageCommentMySuffixDeleteDialogComponent],
                providers: [
                    MessageCommentMySuffixService
                ]
            })
            .overrideTemplate(MessageCommentMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MessageCommentMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MessageCommentMySuffixService);
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
