/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BackendTestModule } from '../../../test.module';
import { MessageCommentMySuffixComponent } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix.component';
import { MessageCommentMySuffixService } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix.service';
import { MessageCommentMySuffix } from '../../../../../../main/webapp/app/entities/message-comment-my-suffix/message-comment-my-suffix.model';

describe('Component Tests', () => {

    describe('MessageCommentMySuffix Management Component', () => {
        let comp: MessageCommentMySuffixComponent;
        let fixture: ComponentFixture<MessageCommentMySuffixComponent>;
        let service: MessageCommentMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [MessageCommentMySuffixComponent],
                providers: [
                    MessageCommentMySuffixService
                ]
            })
            .overrideTemplate(MessageCommentMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MessageCommentMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MessageCommentMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MessageCommentMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.messageComments[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
