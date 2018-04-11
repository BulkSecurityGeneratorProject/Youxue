import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TuanMemberMySuffixComponent } from './tuan-member-my-suffix.component';
import { TuanMemberMySuffixDetailComponent } from './tuan-member-my-suffix-detail.component';
import { TuanMemberMySuffixPopupComponent } from './tuan-member-my-suffix-dialog.component';
import { TuanMemberMySuffixDeletePopupComponent } from './tuan-member-my-suffix-delete-dialog.component';

export const tuanMemberRoute: Routes = [
    {
        path: 'tuan-member-my-suffix',
        component: TuanMemberMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanMember.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tuan-member-my-suffix/:id',
        component: TuanMemberMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanMember.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tuanMemberPopupRoute: Routes = [
    {
        path: 'tuan-member-my-suffix-new',
        component: TuanMemberMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanMember.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tuan-member-my-suffix/:id/edit',
        component: TuanMemberMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanMember.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tuan-member-my-suffix/:id/delete',
        component: TuanMemberMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanMember.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
