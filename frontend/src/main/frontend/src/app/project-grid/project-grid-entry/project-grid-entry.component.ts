import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-project-grid-entry',
  templateUrl: './project-grid-entry.component.html',
  styleUrls: ['./project-grid-entry.component.css']
})
export class ProjectGridEntryComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  private params: any;

  agInit(params: any): void {
    this.params = params;
  }

}
