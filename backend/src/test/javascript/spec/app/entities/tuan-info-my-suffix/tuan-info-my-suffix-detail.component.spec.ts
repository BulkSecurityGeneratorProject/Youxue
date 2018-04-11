/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { BackendTestModule } from '../../../test.module';
import { TuanInfoMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix/tuan-info-my-suffix-detail.component';
import { TuanInfoMySuffixService } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix/tuan-info-my-suffix.service';
import { TuanInfoMySuffix } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix/tuan-info-my-suffix.model';

describe('Component Tests', () => {

    describe('TuanInfoMySuffix Management Detail Component', () => {
        let comp: TuanInfoMySuffixDetailComponent;
        let fixture: ComponentFixture<TuanInfoMySuffixDetailComponent>;
        let service: TuanInfoMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [TuanInfoMySuffixDetailComponent],
                providers: [
                    TuanInfoMySuffixService
                ]
            })
            .overrideTemplate(TuanInfoMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TuanInfoMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TuanInfoMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TuanInfoMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tuanInfo).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
