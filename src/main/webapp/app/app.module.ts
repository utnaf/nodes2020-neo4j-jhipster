import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { Nodes2020SharedModule } from 'app/shared/shared.module';
import { Nodes2020CoreModule } from 'app/core/core.module';
import { Nodes2020AppRoutingModule } from './app-routing.module';
import { Nodes2020HomeModule } from './home/home.module';
import { Nodes2020EntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    Nodes2020SharedModule,
    Nodes2020CoreModule,
    Nodes2020HomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    Nodes2020EntityModule,
    Nodes2020AppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class Nodes2020AppModule {}
