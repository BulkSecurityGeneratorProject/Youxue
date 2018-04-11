import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MessageCommentMySuffixComponent } from './message-comment-my-suffix.component';
import { MessageCommentMySuffixDetailComponent } from './message-comment-my-suffix-detail.component';
import { MessageCommentMySuffixPopupComponent } from './message-comment-my-suffix-dialog.component';
import { MessageCommentMySuffixDeletePopupComponent } from './message-comment-my-suffix-delete-dialog.component';

export const messageCommentRoute: Routes = [
    {
        path: 'message-comment-my-suffix',
        component: MessageCommentMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'message-comment-my-suffix/:id',
        component: MessageCommentMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const messageCommentPopupRoute: Routes = [
    {
        path: 'message-comment-my-suffix-new',
        component: MessageCommentMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'message-comment-my-suffix/:id/edit',
        component: MessageCommentMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'message-comment-my-suffix/:id/delete',
        component: MessageCommentMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
