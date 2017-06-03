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
  title = 'app works!';

  newProject: Project = new Project("");
  observableProject: Project;
  projects: Project[];
  errorMessage: string;

  constructor(private projectDataService: ProjectDataService) {}

  ngOnInit() {
    this.projectDataService.getProjectById(1)
      .subscribe(responseProjectData => this.observableProject = responseProjectData);

    this.projectDataService.getProjects()
      .subscribe(responseProjectData => this.projects = responseProjectData);


  }

  getProject() {
    this.projectDataService.getProjectById(1).subscribe(
      project => this.newProject = project,
      error => this.errorMessage = <any>error
    );
  }

  onAddProject(project: Project) {
    this.projectDataService.addProject(project);
    this.newProject = new Project("");
  }

  get getProjects() {
    return this.projectDataService.getProjects();
  }

  get diagnostic() { return JSON.stringify(this.observableProject); }
}
