import {Component} from 'angular2/core';
import {ArticleListComponent} from './article-list.component.ts';
import {HTTP_PROVIDERS}    from 'angular2/http';
import {ArticleService} from './article.service.ts';
@Component({
    selector: 'my-app',
    template: `
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <article-list>
                Top Articles
            </article-list>
        </div>
        <div class="col-sm-2"></div>
    </div>
    `,
    styleUrls: ['js/articles/component.css'],
    directives: [ArticleListComponent],
    providers: [HTTP_PROVIDERS]
}


export class AppComponent {}


