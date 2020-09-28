import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Nodes2020SharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [Nodes2020SharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class Nodes2020HomeModule {}
