import { Component, OnInit } from '@angular/core';
import {ProjectGridEntryComponent} from "./project-grid-entry/project-grid-entry.component";
import {Grid, GridOptions, GridApi, ColumnApi} from "ag-grid";
import {Project} from "../models/project";
import {ProjectDataService} from "../services/project-data.service";

@Component({
  selector: 'app-project-grid',
  templateUrl: './project-grid.component.html',
  styleUrls: ['./project-grid.component.css']
})
export class ProjectGridComponent implements OnInit {

  private gridOptions: GridOptions;
  projects: Project[];
  count: number = 0;
  offset: number = 0;
  limit: number = 10;
  loading: boolean = false;
  failed: boolean = false;
  private rowData: any[];
  private columnDefs: any[];

  constructor(private _projectDataService: ProjectDataService) {
    this.gridOptions = <GridOptions>{};
    this.columnDefs = [
      {
        headerName: "Title",
        field: "projectTitle",
        cellRendererFramework: ProjectGridEntryComponent
      },

    ];
  }

  ngOnInit() {
    this.getProjects(this.offset, this.limit);
  }

  getProjects(offset: number, limit: number) {
    this.projects = [];
    this.loading = true;
    this.failed = false;
    this._projectDataService.getProjectsPagination(offset, limit).subscribe(result => {
      this.projects = result.projects;
      this.count = result.count;
      this.rowData = result.projects;
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
