export class Repository {
  title: string;
  url: string;
  id: number;
  tracked: boolean;

  constructor(title: string, url: string, id: number, tracked: boolean) {
    this.title = title;
    this.url = url;
    this.id = id;
    this.tracked = tracked;
  }
}
