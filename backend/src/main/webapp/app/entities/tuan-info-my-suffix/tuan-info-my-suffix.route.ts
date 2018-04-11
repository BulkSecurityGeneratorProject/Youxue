import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TuanInfoMySuffixComponent } from './tuan-info-my-suffix.component';
import { TuanInfoMySuffixDetailComponent } from './tuan-info-my-suffix-detail.component';
import { TuanInfoMySuffixPopupComponent } from './tuan-info-my-suffix-dialog.component';
import { TuanInfoMySuffixDeletePopupComponent } from './tuan-info-my-suffix-delete-dialog.component';

export const tuanInfoRoute: Routes = [
    {
        path: 'tuan-info-my-suffix',
        component: TuanInfoMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tuan-info-my-suffix/:id',
        component: TuanInfoMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tuanInfoPopupRoute: Routes = [
    {
        path: 'tuan-info-my-suffix-new',
        component: TuanInfoMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tuan-info-my-suffix/:id/edit',
        component: TuanInfoMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tuan-info-my-suffix/:id/delete',
        component: TuanInfoMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.tuanInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
