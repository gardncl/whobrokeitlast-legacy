import {Component, Input, OnInit} from '@angular/core';
import {ProjectDataService} from "../services/project-data.service";
import {Project} from "../models/project";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  constructor(private _projectDataService: ProjectDataService) { }

  projects: Project[];
  count: number = 0;
  offset: number = 0;
  limit: number = 5;
  loading: boolean = false;
  failed: boolean = false;

  ngOnInit() {
    this.getProjects(this.offset, this.limit);
    console.log(this.projects);
  }

  getProjects(offset: number, limit: number) {
    this.projects = [];
    this.loading = true;
    this.failed = false;
    this._projectDataService.getProjectsPagination(offset, limit).subscribe(result => {
      this.projects = result.projects;
      this.count = result.count;
      this.loading = false;
    }, () => {
      this.loading = false;
      this.failed = true;
    });
  }

  onPageChange(offset) {
    this.offset = offset;
    this.getProjects(this.offset, this.limit);
  }

}
