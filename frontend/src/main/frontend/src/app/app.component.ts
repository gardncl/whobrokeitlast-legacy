import { Component, OnInit } from '@angular/core';
import { Build } from './models/build';
import { Project } from './models/project';
import { Developer } from './models/developer';
import { ProjectDataService } from './services/project-data.service';
import { RepositoryDataService } from './services/repository-data.service';
import {Repository} from "./models/repository";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  newProject: Project = new Project("");
  observableProject: Project;
  projects: Project[];
  repositories: Repository[];
  currentUser: string = "gardncl";

  constructor(private _projectDataService: ProjectDataService,
              private _repositoryDataService: RepositoryDataService) {}

  ngOnInit() {
    this._projectDataService.getProjectById(1)
      .subscribe(responseProjectData => this.observableProject = responseProjectData);
    this._repositoryDataService.getRepositories("gardncl")
      .subscribe(responseRepoData => this.repositories = responseRepoData);
  }

  onAddProject(project: Project) {
    this._projectDataService.addProject(project,this.currentUser)
      .subscribe(responseProjectData => this.observableProject = responseProjectData);
    this.newProject = new Project("");
  }

  onSwitchFlip(obj: string, owner: string) {
    console.log(obj + " "+ owner);
  }

  get diagnostic() { return JSON.stringify(this.observableProject); }
}
