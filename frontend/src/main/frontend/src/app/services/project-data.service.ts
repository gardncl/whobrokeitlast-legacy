import { Injectable } from '@angular/core';
import {Project} from "app/models/project";
import {Build} from "../models/build";
import {Developer} from "../models/developer";
import {environment} from "../../environments/environment";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import 'rxjs/add/operator/catch';

const API_URL = environment.apiUrl + '/projects';

@Injectable()
export class ProjectDataService {
  private projectUrl = '/api/projects';
  constructor(private http: Http) { }

  getProjects(): Observable<Project[]> {
    return this.http
      .get(`${API_URL}`)
      .map((response: Response) => response.json())
      .catch(this.handleError);
  }

  getProjectById(id: number): Observable<Project> {
    return this.http
      .get(`${API_URL}/${id}`)
      .map((response: Response) => response.json())
      .catch(this.handleError);
  }

  addProject(project: Project): Project {
    //TODO: WIRE IN API TO MAKE THIS CALL REAL
    console.log(JSON.parse(this.getProjectById(1)['_body']));
    return project;
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return Observable.throw(error);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }

}
