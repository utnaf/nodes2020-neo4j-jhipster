import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IType } from 'app/shared/model/type.model';
import { TypeService } from './type.service';

@Component({
  templateUrl: './type-delete-dialog.component.html',
})
export class TypeDeleteDialogComponent {
  type?: IType;

  constructor(protected typeService: TypeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.typeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('typeListModification');
      this.activeModal.close();
    });
  }
}
