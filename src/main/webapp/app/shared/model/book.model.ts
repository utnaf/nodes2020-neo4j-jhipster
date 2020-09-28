import { IType } from 'app/shared/model/type.model';
import { IAuthor } from 'app/shared/model/author.model';

export interface IBook {
  id?: string;
  title?: string;
  year?: number;
  type?: IType;
  author?: IAuthor;
}

export class Book implements IBook {
  constructor(public id?: string, public title?: string, public year?: number, public type?: IType, public author?: IAuthor) {}
}
