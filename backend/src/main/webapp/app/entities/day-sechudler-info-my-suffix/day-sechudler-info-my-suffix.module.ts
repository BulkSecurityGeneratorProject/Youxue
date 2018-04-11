import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BackendSharedModule } from '../../shared';
import {
    DaySechudlerInfoMySuffixService,
    DaySechudlerInfoMySuffixPopupService,
    DaySechudlerInfoMySuffixComponent,
    DaySechudlerInfoMySuffixDetailComponent,
    DaySechudlerInfoMySuffixDialogComponent,
    DaySechudlerInfoMySuffixPopupComponent,
    DaySechudlerInfoMySuffixDeletePopupComponent,
    DaySechudlerInfoMySuffixDeleteDialogComponent,
    daySechudlerInfoRoute,
    daySechudlerInfoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...daySechudlerInfoRoute,
    ...daySechudlerInfoPopupRoute,
];

@NgModule({
    imports: [
        BackendSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DaySechudlerInfoMySuffixComponent,
        DaySechudlerInfoMySuffixDetailComponent,
        DaySechudlerInfoMySuffixDialogComponent,
        DaySechudlerInfoMySuffixDeleteDialogComponent,
        DaySechudlerInfoMySuffixPopupComponent,
        DaySechudlerInfoMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        DaySechudlerInfoMySuffixComponent,
        DaySechudlerInfoMySuffixDialogComponent,
        DaySechudlerInfoMySuffixPopupComponent,
        DaySechudlerInfoMySuffixDeleteDialogComponent,
        DaySechudlerInfoMySuffixDeletePopupComponent,
    ],
    providers: [
        DaySechudlerInfoMySuffixService,
        DaySechudlerInfoMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendDaySechudlerInfoMySuffixModule {}
