import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BackendSharedModule } from '../../shared';
import {
    MessageCommentMySuffixService,
    MessageCommentMySuffixPopupService,
    MessageCommentMySuffixComponent,
    MessageCommentMySuffixDetailComponent,
    MessageCommentMySuffixDialogComponent,
    MessageCommentMySuffixPopupComponent,
    MessageCommentMySuffixDeletePopupComponent,
    MessageCommentMySuffixDeleteDialogComponent,
    messageCommentRoute,
    messageCommentPopupRoute,
} from './';

const ENTITY_STATES = [
    ...messageCommentRoute,
    ...messageCommentPopupRoute,
];

@NgModule({
    imports: [
        BackendSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MessageCommentMySuffixComponent,
        MessageCommentMySuffixDetailComponent,
        MessageCommentMySuffixDialogComponent,
        MessageCommentMySuffixDeleteDialogComponent,
        MessageCommentMySuffixPopupComponent,
        MessageCommentMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        MessageCommentMySuffixComponent,
        MessageCommentMySuffixDialogComponent,
        MessageCommentMySuffixPopupComponent,
        MessageCommentMySuffixDeleteDialogComponent,
        MessageCommentMySuffixDeletePopupComponent,
    ],
    providers: [
        MessageCommentMySuffixService,
        MessageCommentMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendMessageCommentMySuffixModule {}
