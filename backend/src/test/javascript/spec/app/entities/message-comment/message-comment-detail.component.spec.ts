/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { BackendTestModule } from '../../../test.module';
import { MessageCommentDetailComponent } from '../../../../../../main/webapp/app/entities/message-comment/message-comment-detail.component';
import { MessageCommentService } from '../../../../../../main/webapp/app/entities/message-comment/message-comment.service';
import { MessageComment } from '../../../../../../main/webapp/app/entities/message-comment/message-comment.model';

describe('Component Tests', () => {

    describe('MessageComment Management Detail Component', () => {
        let comp: MessageCommentDetailComponent;
        let fixture: ComponentFixture<MessageCommentDetailComponent>;
        let service: MessageCommentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [MessageCommentDetailComponent],
                providers: [
                    MessageCommentService
                ]
            })
            .overrideTemplate(MessageCommentDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MessageCommentDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MessageCommentService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new MessageComment(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.messageComment).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
