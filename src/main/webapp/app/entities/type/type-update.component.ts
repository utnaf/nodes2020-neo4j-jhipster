import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IType, Type } from 'app/shared/model/type.model';
import { TypeService } from './type.service';

@Component({
  selector: 'jhi-type-update',
  templateUrl: './type-update.component.html',
})
export class TypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
  });

  constructor(protected typeService: TypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ type }) => {
      this.updateForm(type);
    });
  }

  updateForm(type: IType): void {
    this.editForm.patchValue({
      id: type.id,
      name: type.name,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const type = this.createFromForm();
    if (type.id !== undefined) {
      this.subscribeToSaveResponse(this.typeService.update(type));
    } else {
      this.subscribeToSaveResponse(this.typeService.create(type));
    }
  }

  private createFromForm(): IType {
    return {
      ...new Type(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IType>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
