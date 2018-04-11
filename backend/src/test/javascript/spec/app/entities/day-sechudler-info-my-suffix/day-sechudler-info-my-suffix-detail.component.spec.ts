/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { BackendTestModule } from '../../../test.module';
import { DaySechudlerInfoMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix-detail.component';
import { DaySechudlerInfoMySuffixService } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.service';
import { DaySechudlerInfoMySuffix } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.model';

describe('Component Tests', () => {

    describe('DaySechudlerInfoMySuffix Management Detail Component', () => {
        let comp: DaySechudlerInfoMySuffixDetailComponent;
        let fixture: ComponentFixture<DaySechudlerInfoMySuffixDetailComponent>;
        let service: DaySechudlerInfoMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [DaySechudlerInfoMySuffixDetailComponent],
                providers: [
                    DaySechudlerInfoMySuffixService
                ]
            })
            .overrideTemplate(DaySechudlerInfoMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DaySechudlerInfoMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DaySechudlerInfoMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new DaySechudlerInfoMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.daySechudlerInfo).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
