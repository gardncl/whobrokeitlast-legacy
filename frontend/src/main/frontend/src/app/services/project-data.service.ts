import {Injectable} from '@angular/core';
import {Project} from "app/models/project";
import {Build} from "../models/build";
import {Developer} from "../models/developer";
import {environment} from "../../environments/environment";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import 'rxjs/add/operator/catch';

const API_URL = environment.apiUrl + '/projects';

@Injectable()
export class ProjectDataService {


  constructor(private _http: Http) { }

  getProjects(): Observable<Project[]> {
    return this._http
      .get(`${API_URL}`)
      .map((response: Response) => response.json())
      .catch(this.handleError);
  }

  getProjectById(id: number): Observable<Project> {
    return this._http
      .get(`${API_URL}/${id}`)
      .map(this.extractData)
      .catch(this.handleError);
  }

  addProject(project: Project): Observable<Project> {
    console.log(JSON.stringify(project));
    let headers = new Headers({ 'Content-Type': 'application/json; charset=UTF-8' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(`${API_URL}`, JSON.stringify(project), options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return Observable.throw(error);
  }

  private extractData(response: Response) {
    let body = response.json();
    return body || { };
  }

}
