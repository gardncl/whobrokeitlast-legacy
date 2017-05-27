import {Project} from "./project";
export class Build {
  id: number;
  project: Project;
  emailOnPass: boolean;
  emailOnFailure: boolean;
}
