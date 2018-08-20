/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AplicacionTestModule } from '../../../test.module';
import { ConfiguracionVariableFrontUpdateComponent } from 'app/entities/configuracion-variable-front/configuracion-variable-front-update.component';
import { ConfiguracionVariableFrontService } from 'app/entities/configuracion-variable-front/configuracion-variable-front.service';
import { ConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';

describe('Component Tests', () => {
    describe('ConfiguracionVariableFront Management Update Component', () => {
        let comp: ConfiguracionVariableFrontUpdateComponent;
        let fixture: ComponentFixture<ConfiguracionVariableFrontUpdateComponent>;
        let service: ConfiguracionVariableFrontService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AplicacionTestModule],
                declarations: [ConfiguracionVariableFrontUpdateComponent]
            })
                .overrideTemplate(ConfiguracionVariableFrontUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConfiguracionVariableFrontUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracionVariableFrontService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ConfiguracionVariableFront(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.configuracionVariable = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ConfiguracionVariableFront();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.configuracionVariable = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
