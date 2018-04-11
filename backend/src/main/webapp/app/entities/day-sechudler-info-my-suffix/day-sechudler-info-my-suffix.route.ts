import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { DaySechudlerInfoMySuffixComponent } from './day-sechudler-info-my-suffix.component';
import { DaySechudlerInfoMySuffixDetailComponent } from './day-sechudler-info-my-suffix-detail.component';
import { DaySechudlerInfoMySuffixPopupComponent } from './day-sechudler-info-my-suffix-dialog.component';
import { DaySechudlerInfoMySuffixDeletePopupComponent } from './day-sechudler-info-my-suffix-delete-dialog.component';

export const daySechudlerInfoRoute: Routes = [
    {
        path: 'day-sechudler-info-my-suffix',
        component: DaySechudlerInfoMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.daySechudlerInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'day-sechudler-info-my-suffix/:id',
        component: DaySechudlerInfoMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.daySechudlerInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const daySechudlerInfoPopupRoute: Routes = [
    {
        path: 'day-sechudler-info-my-suffix-new',
        component: DaySechudlerInfoMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.daySechudlerInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'day-sechudler-info-my-suffix/:id/edit',
        component: DaySechudlerInfoMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.daySechudlerInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'day-sechudler-info-my-suffix/:id/delete',
        component: DaySechudlerInfoMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'backendApp.daySechudlerInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
