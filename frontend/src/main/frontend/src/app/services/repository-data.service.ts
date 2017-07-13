import {Injectable} from "@angular/core";
import {environment} from "../../environments/environment";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {Repository} from "../models/repository";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

@Injectable()
export class RepositoryDataService {

  constructor(private _http: Http) { }

  getRepositories(username: string): Observable<Array<Repository>> {
    return this._http.get(`${environment.apiUrl}/user/${username}`)
      .map(this.extractRepositoryList)
      .catch(this.handleError);
  }

  private extractRepositoryList(response: Response): Array<Repository> {
    console.log(response.json());
    let body = response.json();
    let repositoryList = new Array<Repository>();
    for(let result of body){
      let repo = new Repository(result.projectTitle,result.url);
      repositoryList.push(repo);
    }
    return repositoryList;
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return new Observable();
  }
}
