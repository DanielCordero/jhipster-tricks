/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AplicacionTestModule } from '../../../test.module';
import { ConfiguracionVariableFrontDetailComponent } from 'app/entities/configuracion-variable-front/configuracion-variable-front-detail.component';
import { ConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';

describe('Component Tests', () => {
    describe('ConfiguracionVariableFront Management Detail Component', () => {
        let comp: ConfiguracionVariableFrontDetailComponent;
        let fixture: ComponentFixture<ConfiguracionVariableFrontDetailComponent>;
        const route = ({ data: of({ configuracionVariable: new ConfiguracionVariableFront(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AplicacionTestModule],
                declarations: [ConfiguracionVariableFrontDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConfiguracionVariableFrontDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConfiguracionVariableFrontDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.configuracionVariable).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
