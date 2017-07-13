import {Url} from "url";
export class Repository {
  owner: string;
  url: string;

  constructor(owner: string, url: string) {
    this.owner = owner;
    this.url = url;
  }
}
