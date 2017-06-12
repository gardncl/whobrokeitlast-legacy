import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ProjectDataService } from "./services/project-data.service";
import { ProjectListHeaderComponent } from './project-list-header/project-list-header.component';
import { ProjectFormComponent } from './project-form/project-form.component';
import { ApplicationHeaderComponent } from './application-header/application-header.component';
import { PaginationComponentComponent } from './pagination-component/pagination-component.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectListEntryComponent } from './project-list/project-list-entry/project-list-entry.component';
import { LoaderComponent } from './shared/loader/loader.component';

@NgModule({
  declarations: [
    AppComponent,
    ProjectListHeaderComponent,
    ProjectFormComponent,
    ApplicationHeaderComponent,
    PaginationComponentComponent,
    ProjectListComponent,
    ProjectListEntryComponent,
    LoaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [ProjectDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
