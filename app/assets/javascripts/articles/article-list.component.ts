import {Component, OnInit} from 'angular2/core';
import {Article} from './article.ts';
import {ArticleService} from './article.service.ts';
import {ArticleComponent} from './article.component.ts';



@Component({
    selector: 'article-list',
    templateUrl: 'js/articles/article-list.component.html',
    styleUrls: ['js/articles/article-list.component.css'],
    providers: [ArticleService],
    directives:[ArticleComponent]
})

export class ArticleListComponent implements OnInit {
    constructor (private _articleService: ArticleService) {}
    errorMessage: string;
    articles:Article[];
    ngOnInit() { this.getAllArticles(); }
    getAllArticles() {
        this._articleService.getAllArticles()
            .subscribe(
                articles => this.articles = articles,
                error =>  this.errorMessage = <any>error);
    }
}