import {Component, Input, OnInit} from '@angular/core';
import {Repository} from "../models/repository";

@Component({
  selector: 'app-repo-switch-list',
  templateUrl: './repo-switch-list.component.html',
  styleUrls: ['./repo-switch-list.component.css']
})
export class RepoSwitchListComponent implements OnInit {

  @Input() repositories: Repository[];

  constructor() { }

  ngOnInit() {
  }

}
