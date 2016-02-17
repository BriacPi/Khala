import {Component} from 'angular2/core';
import {ArticleListComponent} from './article-list.component.ts';
import {HTTP_PROVIDERS}    from 'angular2/http';
import {ArticleService} from './article.service.ts';
@Component({
    selector: 'my-app',
    template: `
    <div class="row">
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <article-list>
                Top Articles
            </article-list>
        </div>
    </div>
    `,
    styleUrls: ['js/articles/component.css'],
    directives: [ArticleListComponent],
    providers: [HTTP_PROVIDERS]
}


export class AppComponent {}


