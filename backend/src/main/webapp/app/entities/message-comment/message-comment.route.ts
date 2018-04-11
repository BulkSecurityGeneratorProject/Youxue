import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MessageCommentComponent } from './message-comment.component';
import { MessageCommentDetailComponent } from './message-comment-detail.component';
import { MessageCommentPopupComponent } from './message-comment-dialog.component';
import { MessageCommentDeletePopupComponent } from './message-comment-delete-dialog.component';

export const messageCommentRoute: Routes = [
    {
        path: 'message-comment',
        component: MessageCommentComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'message-comment/:id',
        component: MessageCommentDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const messageCommentPopupRoute: Routes = [
    {
        path: 'message-comment-new',
        component: MessageCommentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'message-comment/:id/edit',
        component: MessageCommentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'message-comment/:id/delete',
        component: MessageCommentDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.messageComment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
