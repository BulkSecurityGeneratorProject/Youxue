/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BackendTestModule } from '../../../test.module';
import { MessageCommentComponent } from '../../../../../../main/webapp/app/entities/message-comment/message-comment.component';
import { MessageCommentService } from '../../../../../../main/webapp/app/entities/message-comment/message-comment.service';
import { MessageComment } from '../../../../../../main/webapp/app/entities/message-comment/message-comment.model';

describe('Component Tests', () => {

    describe('MessageComment Management Component', () => {
        let comp: MessageCommentComponent;
        let fixture: ComponentFixture<MessageCommentComponent>;
        let service: MessageCommentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [MessageCommentComponent],
                providers: [
                    MessageCommentService
                ]
            })
            .overrideTemplate(MessageCommentComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MessageCommentComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MessageCommentService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MessageComment(123)],
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
