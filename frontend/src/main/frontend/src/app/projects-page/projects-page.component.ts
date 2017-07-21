import { Component, OnInit } from '@angular/core';
import {Repository} from "../models/repository";
import {RepositoryDataService} from "../services/repository-data.service";

@Component({
  selector: 'app-projects-page',
  templateUrl: './projects-page.component.html',
  styleUrls: ['./projects-page.component.css']
})
export class ProjectsPageComponent implements OnInit {

  currentUser: string = "gardncl";
  repositories: Repository[];

  constructor(private _repositoryDataService: RepositoryDataService) { }

  ngOnInit() {
    this._repositoryDataService.getRepositories(this.currentUser)
      .subscribe(responseRepoData => this.repositories = responseRepoData);
  }

}
