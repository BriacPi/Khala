import {Component} from 'angular2/core';
import {UserInfoComponent} from './user-info.component.ts';
import {HTTP_PROVIDERS}    from 'angular2/http';
import {UserInfoService} from './user-info.service.ts';
@Component({
    selector: 'my-app',
    template: `
    <div class="row">
        <div class="col-sm-3">
            <user-info >userInfo</user-info>
        </div>
        <div class="col-sm-6"><h1> LOL </h1></div>
        <div class="col-sm-3"><h1> LOL </h1></div>
    </div>
    `,
    styleUrls: ['js/myprofile/component.css'],
    directives: [UserInfoComponent],
    providers: [HTTP_PROVIDERS]
})

export class AppComponent {}


