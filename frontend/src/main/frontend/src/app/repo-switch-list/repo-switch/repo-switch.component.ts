import {Component, Input, OnInit} from '@angular/core';
import {Repository} from "../../models/repository";

@Component({
  selector: 'app-repo-switch',
  templateUrl: './repo-switch.component.html',
  styleUrls: ['./repo-switch.component.css']
})
export class RepoSwitchComponent implements OnInit {

  ngOnInit() {
  }

  @Input() repository: Repository;

  onSwitchFlip(obj: string, owner: string) {
    console.log(obj + " "+ owner);
  }

}
