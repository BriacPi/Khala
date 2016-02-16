import {Injectable}     from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {UserInfo}       from './user-info.ts';
import {Observable}     from 'rxjs/Observable';


@Injectable()
export class UserInfoService {
    constructor (private http: Http) {}

    private _userInfoUrl = '/api/user-info-minimal';

    getUserInfo () {
        return this.http.get(this._userInfoUrl)
            .map(res => <UserInfo[]> res.json())
            .do(data => console.log(data)) // eyeball results in the console
            .catch(this.handleError);
    }
    private handleError (error: Response) {
        // in a real world app, we may send the server to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}