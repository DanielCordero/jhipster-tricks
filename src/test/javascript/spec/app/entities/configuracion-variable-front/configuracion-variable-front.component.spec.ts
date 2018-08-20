/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AplicacionTestModule } from '../../../test.module';
import { ConfiguracionVariableFrontComponent } from 'app/entities/configuracion-variable-front/configuracion-variable-front.component';
import { ConfiguracionVariableFrontService } from 'app/entities/configuracion-variable-front/configuracion-variable-front.service';
import { ConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';

describe('Component Tests', () => {
    describe('ConfiguracionVariableFront Management Component', () => {
        let comp: ConfiguracionVariableFrontComponent;
        let fixture: ComponentFixture<ConfiguracionVariableFrontComponent>;
        let service: ConfiguracionVariableFrontService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AplicacionTestModule],
                declarations: [ConfiguracionVariableFrontComponent],
                providers: []
            })
                .overrideTemplate(ConfiguracionVariableFrontComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConfiguracionVariableFrontComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracionVariableFrontService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ConfiguracionVariableFront(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.configuracionVariables[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
