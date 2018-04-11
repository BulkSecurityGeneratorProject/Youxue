/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { BackendTestModule } from '../../../test.module';
import { MessageCommentMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix-detail.component';
import { MessageCommentMySuffixService } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix.service';
import { MessageCommentMySuffix } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix.model';

describe('Component Tests', () => {

    describe('MessageCommentMySuffix Management Detail Component', () => {
        let comp: MessageCommentMySuffixDetailComponent;
        let fixture: ComponentFixture<MessageCommentMySuffixDetailComponent>;
        let service: MessageCommentMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [MessageCommentMySuffixDetailComponent],
                providers: [
                    MessageCommentMySuffixService
                ]
            })
            .overrideTemplate(MessageCommentMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MessageCommentMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MessageCommentMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new MessageCommentMySuffix(123)
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
