import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVariableFront } from 'app/shared/model/variable-front.model';
import { VariableFrontService } from './variable-front.service';

@Component({
    selector: 'jhi-variable-front-delete-dialog',
    templateUrl: './variable-front-delete-dialog.component.html'
})
export class VariableFrontDeleteDialogComponent {
    variable: IVariableFront;

    constructor(private variableService: VariableFrontService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.variableService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'variableListModification',
                content: 'Deleted an variable'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-variable-front-delete-popup',
    template: ''
})
export class VariableFrontDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ variable }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VariableFrontDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.variable = variable;
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
