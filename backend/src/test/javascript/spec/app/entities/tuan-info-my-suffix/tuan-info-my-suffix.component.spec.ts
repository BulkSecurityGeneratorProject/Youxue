/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BackendTestModule } from '../../../test.module';
import { TuanInfoMySuffixComponent } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix/tuan-info-my-suffix.component';
import { TuanInfoMySuffixService } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix/tuan-info-my-suffix.service';
import { TuanInfoMySuffix } from '../../../../../../main/webapp/app/entities/tuan-info-my-suffix/tuan-info-my-suffix.model';

describe('Component Tests', () => {

    describe('TuanInfoMySuffix Management Component', () => {
        let comp: TuanInfoMySuffixComponent;
        let fixture: ComponentFixture<TuanInfoMySuffixComponent>;
        let service: TuanInfoMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BackendTestModule],
                declarations: [TuanInfoMySuffixComponent],
                providers: [
                    TuanInfoMySuffixService
                ]
            })
            .overrideTemplate(TuanInfoMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TuanInfoMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TuanInfoMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TuanInfoMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tuanInfos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
