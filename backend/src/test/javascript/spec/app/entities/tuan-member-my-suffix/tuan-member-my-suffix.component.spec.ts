/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BackendTestModule } from '../../../test.module';
import { TuanMemberMySuffixComponent } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix.component';
import { TuanMemberMySuffixService } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix.service';
import { TuanMemberMySuffix } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix.model';

describe('Component Tests', () => {

    describe('TuanMemberMySuffix Management Component', () => {
        let comp: TuanMemberMySuffixComponent;
        let fixture: ComponentFixture<TuanMemberMySuffixComponent>;
        let service: TuanMemberMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [TuanMemberMySuffixComponent],
                providers: [
                    TuanMemberMySuffixService
                ]
            })
            .overrideTemplate(TuanMemberMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TuanMemberMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TuanMemberMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TuanMemberMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tuanMembers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
