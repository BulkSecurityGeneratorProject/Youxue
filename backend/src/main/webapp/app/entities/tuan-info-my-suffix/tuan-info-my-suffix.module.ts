import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BackendSharedModule } from '../../shared';
import {
    TuanInfoMySuffixService,
    TuanInfoMySuffixPopupService,
    TuanInfoMySuffixComponent,
    TuanInfoMySuffixDetailComponent,
    TuanInfoMySuffixDialogComponent,
    TuanInfoMySuffixPopupComponent,
    TuanInfoMySuffixDeletePopupComponent,
    TuanInfoMySuffixDeleteDialogComponent,
    tuanInfoRoute,
    tuanInfoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tuanInfoRoute,
    ...tuanInfoPopupRoute,
];

@NgModule({
    imports: [
        BackendSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TuanInfoMySuffixComponent,
        TuanInfoMySuffixDetailComponent,
        TuanInfoMySuffixDialogComponent,
        TuanInfoMySuffixDeleteDialogComponent,
        TuanInfoMySuffixPopupComponent,
        TuanInfoMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TuanInfoMySuffixComponent,
        TuanInfoMySuffixDialogComponent,
        TuanInfoMySuffixPopupComponent,
        TuanInfoMySuffixDeleteDialogComponent,
        TuanInfoMySuffixDeletePopupComponent,
    ],
    providers: [
        TuanInfoMySuffixService,
        TuanInfoMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendTuanInfoMySuffixModule {}
