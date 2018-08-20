/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AplicacionTestModule } from '../../../test.module';
import { VariableFrontDeleteDialogComponent } from 'app/entities/variable-front/variable-front-delete-dialog.component';
import { VariableFrontService } from 'app/entities/variable-front/variable-front.service';

describe('Component Tests', () => {
    describe('VariableFront Management Delete Component', () => {
        let comp: VariableFrontDeleteDialogComponent;
        let fixture: ComponentFixture<VariableFrontDeleteDialogComponent>;
        let service: VariableFrontService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AplicacionTestModule],
                declarations: [VariableFrontDeleteDialogComponent]
            })
                .overrideTemplate(VariableFrontDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VariableFrontDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VariableFrontService);
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
