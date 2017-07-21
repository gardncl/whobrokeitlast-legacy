import {Project} from "./project";
export class Developer {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  lastBreak: Date;
  numberOfBrokenBuilds: number;
  projects: Project[];
  email: string;
}
