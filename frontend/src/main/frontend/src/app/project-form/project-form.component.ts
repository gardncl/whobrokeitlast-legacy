import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Project} from "../models/project";

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css']
})
export class ProjectFormComponent {

  constructor() { }

  @Input()
  newProject = new Project("");

  @Output()
  add: EventEmitter<Project> = new EventEmitter();

  submitted = false;

  onSubmit() { this.submitted = true; }

  addProject() {
    this.add.emit(this.newProject);
    this.newProject = new Project("");
  }

  clearProject() {
    this.newProject = new Project("");
  }

  get diagnostic() { return JSON.stringify(this.newProject); }
}
