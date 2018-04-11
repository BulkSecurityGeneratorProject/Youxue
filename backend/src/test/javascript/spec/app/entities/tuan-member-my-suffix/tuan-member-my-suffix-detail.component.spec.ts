/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { BackendTestModule } from '../../../test.module';
import { TuanMemberMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix-detail.component';
import { TuanMemberMySuffixService } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix.service';
import { TuanMemberMySuffix } from '../../../../../../main/webapp/app/entities/tuan-member-my-suffix/tuan-member-my-suffix.model';

describe('Component Tests', () => {

    describe('TuanMemberMySuffix Management Detail Component', () => {
        let comp: TuanMemberMySuffixDetailComponent;
        let fixture: ComponentFixture<TuanMemberMySuffixDetailComponent>;
        let service: TuanMemberMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [TuanMemberMySuffixDetailComponent],
                providers: [
                    TuanMemberMySuffixService
                ]
            })
            .overrideTemplate(TuanMemberMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TuanMemberMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TuanMemberMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TuanMemberMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tuanMember).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
