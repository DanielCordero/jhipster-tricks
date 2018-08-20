/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AplicacionTestModule } from '../../../test.module';
import { VariableFrontUpdateComponent } from 'app/entities/variable-front/variable-front-update.component';
import { VariableFrontService } from 'app/entities/variable-front/variable-front.service';
import { VariableFront } from 'app/shared/model/variable-front.model';

describe('Component Tests', () => {
    describe('VariableFront Management Update Component', () => {
        let comp: VariableFrontUpdateComponent;
        let fixture: ComponentFixture<VariableFrontUpdateComponent>;
        let service: VariableFrontService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AplicacionTestModule],
                declarations: [VariableFrontUpdateComponent]
            })
                .overrideTemplate(VariableFrontUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VariableFrontUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VariableFrontService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new VariableFront(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.variable = entity;
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
                    const entity = new VariableFront();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.variable = entity;
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
