import { Component, OnInit } from '@angular/core';
import { Build } from './models/build';
import { Project } from './models/project';
import { Developer } from './models/developer';
import { ProjectDataService } from './services/project-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  newProject: Project = new Project("");
  observableProject: Project;
  projects: Project[];

  constructor(private _projectDataService: ProjectDataService) {}

  ngOnInit() {
    this._projectDataService.getProjectById(1)
      .subscribe(responseProjectData => this.observableProject = responseProjectData);
  }

  onAddProject(project: Project) {
    this._projectDataService.addProject(project)
      .subscribe(responseProjectData => this.observableProject = responseProjectData);
    this.newProject = new Project("");
  }

  get diagnostic() { return JSON.stringify(this.observableProject); }
}
