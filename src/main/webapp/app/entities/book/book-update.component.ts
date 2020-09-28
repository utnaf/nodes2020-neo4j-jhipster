import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IBook, Book } from 'app/shared/model/book.model';
import { BookService } from './book.service';
import { IType } from 'app/shared/model/type.model';
import { TypeService } from 'app/entities/type/type.service';
import { IAuthor } from 'app/shared/model/author.model';
import { AuthorService } from 'app/entities/author/author.service';

type SelectableEntity = IType | IAuthor;

@Component({
  selector: 'jhi-book-update',
  templateUrl: './book-update.component.html',
})
export class BookUpdateComponent implements OnInit {
  isSaving = false;
  types: IType[] = [];
  authors: IAuthor[] = [];

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.required]],
    year: [null, [Validators.min(1200)]],
    type: [],
    author: [],
  });

  constructor(
    protected bookService: BookService,
    protected typeService: TypeService,
    protected authorService: AuthorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ book }) => {
      this.updateForm(book);

      this.typeService
        .query({ filter: 'book-is-null' })
        .pipe(
          map((res: HttpResponse<IType[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IType[]) => {
          if (!book.type || !book.type.id) {
            this.types = resBody;
          } else {
            this.typeService
              .find(book.type.id)
              .pipe(
                map((subRes: HttpResponse<IType>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IType[]) => (this.types = concatRes));
          }
        });

      this.authorService.query().subscribe((res: HttpResponse<IAuthor[]>) => (this.authors = res.body || []));
    });
  }

  updateForm(book: IBook): void {
    this.editForm.patchValue({
      id: book.id,
      title: book.title,
      year: book.year,
      type: book.type,
      author: book.author,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const book = this.createFromForm();
    if (book.id !== undefined) {
      this.subscribeToSaveResponse(this.bookService.update(book));
    } else {
      this.subscribeToSaveResponse(this.bookService.create(book));
    }
  }

  private createFromForm(): IBook {
    return {
      ...new Book(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      year: this.editForm.get(['year'])!.value,
      type: this.editForm.get(['type'])!.value,
      author: this.editForm.get(['author'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBook>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
