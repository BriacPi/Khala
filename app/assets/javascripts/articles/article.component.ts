import {Component, OnInit} from 'angular2/core';
import {Article} from './article.ts';
import {AuthorInfo} from './author-info.ts';
import {AuthorInfoService} from './author-info.service.ts';



@Component({
    selector: 'article',
    templateUrl: 'js/articles/article.component.html',
    providers: [AuthorInfoService],
    inputs: ['article']
})

export class ArticleComponent implements OnInit {
    article:Article;
    constructor (private _authorInfoService: AuthorInfoService) {}
    errorMessage: string;
    authorInfo:AuthorInfo;

    ngOnInit() { this.getAuthorInfo(this.article._id); }

    getAuthorInfo() {
        this._authorInfoService.getAuthorInfo(this.article._id)
            .subscribe(
                info => this.authorInfo = info,
                error =>  this.errorMessage = <any>error);
    }
}



