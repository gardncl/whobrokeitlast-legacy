import {Component, Input, OnInit} from '@angular/core';
import {Project} from "../../models/project";

@Component({
  selector: 'app-project-list-entry',
  templateUrl: './project-list-entry.component.html',
  styleUrls: ['./project-list-entry.component.css']
})
export class ProjectListEntryComponent implements OnInit {


  ngOnInit() {
  }

  @Input() project: Project;

  constructor() { }

}
