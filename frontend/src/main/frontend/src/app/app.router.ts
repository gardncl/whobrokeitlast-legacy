import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {ProjectsPageComponent} from './projects-page/projects-page.component';
import {HomePageComponent} from './home-page/home-page.component';

export const router: Routes = [
  { path: '', component: HomePageComponent},
  { path: 'projects', component: ProjectsPageComponent}
];

export const routes: ModuleWithProviders = RouterModule.forRoot(router);
