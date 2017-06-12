import {Injectable} from "@angular/core";
import {Project} from "app/models/project";
import {environment} from "../../environments/environment";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {ProjectPagination} from "../models/project-pagination";

const API_URL = environment.apiUrl + '/projects';

@Injectable()
export class ProjectDataService {


  constructor(private _http: Http) { }

  getProjectsPagination(offset: number = 0, limit: number = 20): Observable<ProjectPagination> {
    return this._http
      .get(`${API_URL}?page=${offset}&size=${limit}`)
      .map(this.extractProjectList)
      .catch(this.handleError);
  }

  getProjectById(id: number): Observable<Project> {
    return this._http
      .get(`${API_URL}/${id}`)
      .map(this.extractData)
      .catch(this.handleError);
  }

  addProject(project: Project): Observable<Project> {
    let headers = new Headers({ 'Content-Type': 'application/json; charset=UTF-8' });
    let options = new RequestOptions({ headers: headers });
    return this._http.post(`${API_URL}`, JSON.stringify(project), options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return new Observable();
  }

  private extractData(response: Response) {
    let body = response.json();
    delete body._links;
    return body || { };
  }

  private extractProjectList(response: Response) {
    let body = response.json()._embedded.projects;
    body.forEach(body => delete body._links);
    var projects = [];
    for (let i = 0; i < response.json().page.size; i++ ) {
      let project = new Project(body[i].projectTitle);
      projects.push(project);
    }
    let returnValue = new ProjectPagination();
    returnValue.count = response.json().page.totalElements;
    returnValue.projects = projects;
    return returnValue;
  }



}
