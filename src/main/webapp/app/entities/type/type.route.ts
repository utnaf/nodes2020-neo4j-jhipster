import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IType, Type } from 'app/shared/model/type.model';
import { TypeService } from './type.service';
import { TypeComponent } from './type.component';
import { TypeDetailComponent } from './type-detail.component';
import { TypeUpdateComponent } from './type-update.component';

@Injectable({ providedIn: 'root' })
export class TypeResolve implements Resolve<IType> {
  constructor(private service: TypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((type: HttpResponse<Type>) => {
          if (type.body) {
            return of(type.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Type());
  }
}

export const typeRoute: Routes = [
  {
    path: '',
    component: TypeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'nodes2020App.type.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeDetailComponent,
    resolve: {
      type: TypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'nodes2020App.type.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeUpdateComponent,
    resolve: {
      type: TypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'nodes2020App.type.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeUpdateComponent,
    resolve: {
      type: TypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'nodes2020App.type.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
