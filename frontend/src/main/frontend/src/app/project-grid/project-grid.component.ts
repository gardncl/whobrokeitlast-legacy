import { Component, OnInit } from '@angular/core';
import {ProjectGridEntryComponent} from "./project-grid-entry/project-grid-entry.component";
import {Grid, GridOptions} from "ag-grid";

@Component({
  selector: 'app-project-grid',
  templateUrl: './project-grid.component.html',
  styleUrls: ['./project-grid.component.css']
})
export class ProjectGridComponent implements OnInit {
  private gridOptions: GridOptions;

  constructor() {
    this.gridOptions = <GridOptions>{};
    this.gridOptions.columnDefs = [
      {
        headerName: "ID",
        field: "id",
        width: 100
      },
      {
        headerName: "Value",
        field: "value",
        cellRendererFramework: ProjectGridEntryComponent,
        width: 100
      },

    ];
    this.gridOptions.rowData = [
      {id: 5, value: 10},
      {id: 10, value: 15},
      {id: 15, value: 20}
    ]
  }

  ngOnInit() {
  }



}
