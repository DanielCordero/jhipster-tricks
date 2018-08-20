/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AplicacionTestModule } from '../../../test.module';
import { VariableFrontDetailComponent } from 'app/entities/variable-front/variable-front-detail.component';
import { VariableFront } from 'app/shared/model/variable-front.model';

describe('Component Tests', () => {
    describe('VariableFront Management Detail Component', () => {
        let comp: VariableFrontDetailComponent;
        let fixture: ComponentFixture<VariableFrontDetailComponent>;
        const route = ({ data: of({ variable: new VariableFront(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AplicacionTestModule],
                declarations: [VariableFrontDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VariableFrontDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VariableFrontDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.variable).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
