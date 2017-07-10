import {Developer} from "./developer";
import {Build} from "./build";
export class Project {
  id: number;
  projectTitle: string;
  developers: Developer[];
  build: Build;

  constructor(projectTitle: string) {
    this.projectTitle = projectTitle;
  }


}
