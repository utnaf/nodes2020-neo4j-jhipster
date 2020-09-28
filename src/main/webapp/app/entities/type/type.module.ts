import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Nodes2020SharedModule } from 'app/shared/shared.module';
import { TypeComponent } from './type.component';
import { TypeDetailComponent } from './type-detail.component';
import { TypeUpdateComponent } from './type-update.component';
import { TypeDeleteDialogComponent } from './type-delete-dialog.component';
import { typeRoute } from './type.route';

@NgModule({
  imports: [Nodes2020SharedModule, RouterModule.forChild(typeRoute)],
  declarations: [TypeComponent, TypeDetailComponent, TypeUpdateComponent, TypeDeleteDialogComponent],
  entryComponents: [TypeDeleteDialogComponent],
})
export class Nodes2020TypeModule {}
