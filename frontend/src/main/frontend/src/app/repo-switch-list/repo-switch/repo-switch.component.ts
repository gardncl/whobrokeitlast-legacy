import {Component, Input, OnInit} from '@angular/core';
import {Repository} from "../../models/repository";
import {RepositoryDataService} from "../../services/repository-data.service";

@Component({
  selector: 'app-repo-switch',
  templateUrl: './repo-switch.component.html',
  styleUrls: ['./repo-switch.component.css']
})
export class RepoSwitchComponent implements OnInit {


  constructor(private _repositoryDataService: RepositoryDataService) {
  }

  ngOnInit() {
  }

  @Input() repository: Repository;

  onSwitchFlip(obj: string) {
    this._repositoryDataService.repositorySwitch(this.repository).subscribe();
  }

}
