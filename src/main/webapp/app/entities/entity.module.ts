import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'book',
        loadChildren: () => import('./book/book.module').then(m => m.Nodes2020BookModule),
      },
      {
        path: 'type',
        loadChildren: () => import('./type/type.module').then(m => m.Nodes2020TypeModule),
      },
      {
        path: 'author',
        loadChildren: () => import('./author/author.module').then(m => m.Nodes2020AuthorModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class Nodes2020EntityModule {}
