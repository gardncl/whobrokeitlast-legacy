import { Injectable } from '@angular/core';
import {Project} from "app/models/project";
import {Build} from "../models/build";
import {Developer} from "../models/developer";

@Injectable()
export class ProjectDataService {

  constructor() { }

  getProjects(): Project[] {
    let projects: Project[] = [];
    projects.push(new Project(""));

    return projects;
  }

  getProjectById(id: number): Project {
    let project: Project = new Project("");
    project.id = id;
    project.build = new Build;
    project.developers = [];
    project.developers.push(new Developer);
    project.projectTitle = "Who Broke It Last";
    return project;
  }

  addProject(project: Project): Project {
    //TODO: WIRE IN API TO MAKE THIS CALL REAL
    console.log(project);
    return project;
  }

}
