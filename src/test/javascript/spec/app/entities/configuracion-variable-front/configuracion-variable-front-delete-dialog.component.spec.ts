/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AplicacionTestModule } from '../../../test.module';
import { ConfiguracionVariableFrontDeleteDialogComponent } from 'app/entities/configuracion-variable-front/configuracion-variable-front-delete-dialog.component';
import { ConfiguracionVariableFrontService } from 'app/entities/configuracion-variable-front/configuracion-variable-front.service';

describe('Component Tests', () => {
    describe('ConfiguracionVariableFront Management Delete Component', () => {
        let comp: ConfiguracionVariableFrontDeleteDialogComponent;
        let fixture: ComponentFixture<ConfiguracionVariableFrontDeleteDialogComponent>;
        let service: ConfiguracionVariableFrontService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AplicacionTestModule],
                declarations: [ConfiguracionVariableFrontDeleteDialogComponent]
            })
                .overrideTemplate(ConfiguracionVariableFrontDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConfiguracionVariableFrontDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracionVariableFrontService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
