import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ProjectDataService } from "./services/project-data.service";
import { ProjectListHeaderComponent } from './project-list-header/project-list-header.component';
import { ProjectFormComponent } from './project-form/project-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ProjectListHeaderComponent,
    ProjectFormComponent
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
