import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConfiguracionVariableFront } from 'app/shared/model/configuracion-variable-front.model';
import { ConfiguracionVariableFrontService } from './configuracion-variable-front.service';

@Component({
    selector: 'jhi-configuracion-variable-front-delete-dialog',
    templateUrl: './configuracion-variable-front-delete-dialog.component.html'
})
export class ConfiguracionVariableFrontDeleteDialogComponent {
    configuracionVariable: IConfiguracionVariableFront;

    constructor(
        private configuracionVariableService: ConfiguracionVariableFrontService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configuracionVariableService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'configuracionVariableListModification',
                content: 'Deleted an configuracionVariable'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-configuracion-variable-front-delete-popup',
    template: ''
})
export class ConfiguracionVariableFrontDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ configuracionVariable }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConfiguracionVariableFrontDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.configuracionVariable = configuracionVariable;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
