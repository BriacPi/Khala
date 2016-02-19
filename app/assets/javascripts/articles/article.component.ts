import {Component, OnInit} from 'angular2/core';
import {Article} from './article.ts';
import {AuthorInfo} from './author-info.ts';
import {AuthorInfoService} from './author-info.service.ts';
import {LikeService} from './like.service.ts';


@Component({
    selector: 'article',
    templateUrl: 'js/articles/article.component.html',
    providers: [AuthorInfoService,LikeService],
    inputs: ['article']
})

export class ArticleComponent implements OnInit {
    article:Article;
    constructor (private _authorInfoService: AuthorInfoService) {}
    constructor (private _likeService: LikeService) {}

    errorMessage: string;
    authorInfo:AuthorInfo;
    hasLiked:boolean;

    ngOnInit() {
        this.getAuthorInfo(this.article._id);

    }

    getAuthorInfo() {
        this._authorInfoService.getAuthorInfo(this.article._id)
            .subscribe(
                info => this.authorInfo = info,
                error =>  this.errorMessage = <any>error);
    };
    getHasLiked(){
        this._likeService.hasLiked(this.article._id)
            .subscribe(
                like => this.hasLiked = like,
                error =>  this.errorMessage = <any>error);
    };
    changeHasLike(){
        this._likeService.likesUnlikes(this.article._id)
            .subscribe(
                like => this.hasLiked = like,
                error =>  this.errorMessage = <any>error);
    };
}



