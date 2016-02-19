import {Injectable}     from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Article}        from './article.ts';
import {Observable}     from 'rxjs/Observable';


@Injectable()
export class LikeService {
    constructor (private http: Http) {}

    private _hasLikedUrl = '/api/has-liked/';
    private _likeUnlikeUrl = '/api/likes-or-unlikes-article/';

    hasLiked (articleID:string) {
        return this.http.get(this._hasLikedUrl+articleID)
            .map(res => <boolean> res.json().hasLiked)
            .do(data => console.log(data)) // eyeball results in the console
            .catch(this.handleError);
    }
    likesUnlikes (articleID:string) {
        return this.http.get(this._likeUnlikeUrl+articleID)
            .map(res => <boolean> res.json().messages)
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