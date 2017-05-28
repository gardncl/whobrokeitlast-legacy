import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Project} from "../models/project";

@Component({
  selector: 'app-project-list-header',
  templateUrl: './project-list-header.component.html',
  styleUrls: ['./project-list-header.component.css']
})
export class ProjectListHeaderComponent {

  newProject: Project = new Project("");

  @Input()
  project: Project;

  @Output()
  add: EventEmitter<Project> = new EventEmitter();

  constructor() { }

  addProject(project: Project) {
    this.add.emit(project);
  }

}
