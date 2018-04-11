/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BackendTestModule } from '../../../test.module';
import { DaySechudlerInfoMySuffixComponent } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.component';
import { DaySechudlerInfoMySuffixService } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.service';
import { DaySechudlerInfoMySuffix } from '../../../../../../main/webapp/app/entities/day-sechudler-info-my-suffix/day-sechudler-info-my-suffix.model';

describe('Component Tests', () => {

    describe('DaySechudlerInfoMySuffix Management Component', () => {
        let comp: DaySechudlerInfoMySuffixComponent;
        let fixture: ComponentFixture<DaySechudlerInfoMySuffixComponent>;
        let service: DaySechudlerInfoMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [DaySechudlerInfoMySuffixComponent],
                providers: [
                    DaySechudlerInfoMySuffixService
                ]
            })
            .overrideTemplate(DaySechudlerInfoMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DaySechudlerInfoMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DaySechudlerInfoMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new DaySechudlerInfoMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.daySechudlerInfos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
