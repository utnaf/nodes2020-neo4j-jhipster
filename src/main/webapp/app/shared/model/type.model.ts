export interface IType {
  id?: string;
  name?: string;
}

export class Type implements IType {
  constructor(public id?: string, public name?: string) {}
}
