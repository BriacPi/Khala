import {Component, OnInit} from 'angular2/core';
import {UserInfo} from './user-info.ts';
import {UserInfoService} from './user-info.service.ts';



@Component({
    selector: 'user-info',
    templateUrl: 'js/myprofile/user-info.component.html',
    styleUrls: ['js/myprofile/user-info.component.css'],
    providers: [UserInfoService]
})

export class UserInfoComponent implements OnInit {
    constructor (private _userInfoService: UserInfoService) {}
    errorMessage: string;
    userInfo:UserInfo;
    ngOnInit() { this.getUserInfo(); }
    getUserInfo() {
        this._userInfoService.getUserInfo()
            .subscribe(
                uInfo => this.userInfo = uInfo,
                error =>  this.errorMessage = <any>error);
    }
}