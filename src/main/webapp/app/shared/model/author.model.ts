import { IBook } from 'app/shared/model/book.model';

export interface IAuthor {
  id?: string;
  name?: string;
  wrotes?: IBook[];
}

export class Author implements IAuthor {
  constructor(public id?: string, public name?: string, public wrotes?: IBook[]) {}
}
