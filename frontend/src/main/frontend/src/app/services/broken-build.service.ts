import { Injectable } from '@angular/core';
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {environment} from "../../environments/environment";
import {Repository} from "../models/repository";
import {Developer} from "../models/developer";
import {Observable} from "rxjs/Observable";

@Injectable()
export class BrokenBuildDataService {

  constructor(private _http: Http) { }

  getLastBreak(repo: Repository): Observable<Developer> {
    let headers = new Headers({ 'Content-Type': 'application/json; charset=UTF-8' });
    let options = new RequestOptions({ headers: headers });
    let url = `${environment.apiUrl}`.concat("/project/",repo.id.toString(),"/lastbreak");
    return this._http.get(url, options).map(this.extractDeveloper);
  }

  private extractDeveloper(response: Response): Developer {
    let body = response.json();
    console.log(body);
    let developer = new Developer();
    developer.id = body.id;
    developer.lastBreak = body.lastBreak;
    developer.username = body.userName;
    return developer;
  }


}
