import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BackendSharedModule } from '../../shared';
import {
    TuanMemberMySuffixService,
    TuanMemberMySuffixPopupService,
    TuanMemberMySuffixComponent,
    TuanMemberMySuffixDetailComponent,
    TuanMemberMySuffixDialogComponent,
    TuanMemberMySuffixPopupComponent,
    TuanMemberMySuffixDeletePopupComponent,
    TuanMemberMySuffixDeleteDialogComponent,
    tuanMemberRoute,
    tuanMemberPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tuanMemberRoute,
    ...tuanMemberPopupRoute,
];

@NgModule({
    imports: [
        BackendSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TuanMemberMySuffixComponent,
        TuanMemberMySuffixDetailComponent,
        TuanMemberMySuffixDialogComponent,
        TuanMemberMySuffixDeleteDialogComponent,
        TuanMemberMySuffixPopupComponent,
        TuanMemberMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TuanMemberMySuffixComponent,
        TuanMemberMySuffixDialogComponent,
        TuanMemberMySuffixPopupComponent,
        TuanMemberMySuffixDeleteDialogComponent,
        TuanMemberMySuffixDeletePopupComponent,
    ],
    providers: [
        TuanMemberMySuffixService,
        TuanMemberMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendTuanMemberMySuffixModule {}
