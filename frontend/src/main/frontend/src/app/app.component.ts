import { Component } from '@angular/core';
import { Build } from './models/build';
import { Project } from './models/project';
import { Developer } from './models/developer';
import { ProjectDataService } from './services/project-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';

  newProject: Project = new Project("");

  constructor(private projectDataService: ProjectDataService) {

  }

  onAddProject(project: Project) {
    this.projectDataService.addProject(project);
    this.newProject = new Project("");
    console.log(this.projectDataService.getProjectById(1));
  }

  get projects() {
    return this.projectDataService.getProjects();
  }
}
