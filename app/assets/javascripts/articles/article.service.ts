import {Injectable}     from 'angular2/core';
import {Http, Response} from 'angular2/http';
import {Article}        from './article.ts';
import {Observable}     from 'rxjs/Observable';


@Injectable()
export class ArticleService {
    constructor (private http: Http) {}

    private _allArticlesUrl = '/api/all-articles';

    getAllArticles () {
        return this.http.get(this._allArticlesUrl)
            .map(res => <Article[]> res.json().articles)
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