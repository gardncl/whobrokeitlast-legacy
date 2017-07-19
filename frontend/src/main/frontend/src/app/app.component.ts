import { Component, OnInit } from '@angular/core';
import { Build } from './models/build';
import { Project } from './models/project';
import { Developer } from './models/developer';
import { ProjectDataService } from './services/project-data.service';
import { RepositoryDataService } from './services/repository-data.service';
import {Repository} from "./models/repository";
import {BrokenBuildDataService} from "./services/broken-build.service";

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
  lastBreakDev: Developer;

  constructor(private _projectDataService: ProjectDataService,
              private _repositoryDataService: RepositoryDataService,
              private _brokenBuildDataService: BrokenBuildDataService) {}

  ngOnInit() {
    this._repositoryDataService.getRepositories("gardncl")
      .subscribe(responseRepoData => this.repositories = responseRepoData);

    this._projectDataService.getProjectById(this.repositories[0].id)
      .subscribe(responseProjectData => this.observableProject = responseProjectData);
    this._brokenBuildDataService.getLastBreak(this.repositories[0])
      .subscribe(responseData => this.lastBreakDev = responseData);
    console.log("dev: ");
    console.log(this.lastBreakDev);
  }

  loadData() {
    this._projectDataService.getProjectById(this.repositories[0].id)
      .subscribe(responseProjectData => this.observableProject = responseProjectData);
    this._brokenBuildDataService.getLastBreak(this.repositories[0])
      .subscribe(responseData => this.lastBreakDev = responseData);
  }

  logData() {
    console.log(this.lastBreakDev.username);
  }

  onAddProject(project: Project) {
    this._projectDataService.addProject(project,this.currentUser)
      .subscribe(responseProjectData => this.observableProject = responseProjectData);
    this.newProject = new Project("");
  }

  get diagnostic() { return JSON.stringify(this.observableProject); }
}
