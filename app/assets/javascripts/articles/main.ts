import {bootstrap} from 'angular2/platform/browser';
import {ArticleService} from './article.service.ts';
import {AppComponent} from './component.ts';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

bootstrap(AppComponent, [
    ArticleService
]);