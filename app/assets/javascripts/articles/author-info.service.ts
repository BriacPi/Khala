import {Injectable}     from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {AuthorInfo}        from './author-info.ts';
import {Observable}     from 'rxjs/Observable';



@Injectable()
export class AuthorInfoService {
    constructor (private http: Http) {}

    private _authorInfoUrl = '/api/author-mini/';

    getAuthorInfo (articleID:string) {
        return this.http.get(this._authorInfoUrl+articleID)
            .map(res => <AuthorInfo[]> res.json())
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