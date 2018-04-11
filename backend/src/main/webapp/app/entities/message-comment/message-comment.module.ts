import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BackendSharedModule } from '../../shared';
import {
    MessageCommentService,
    MessageCommentPopupService,
    MessageCommentComponent,
    MessageCommentDetailComponent,
    MessageCommentDialogComponent,
    MessageCommentPopupComponent,
    MessageCommentDeletePopupComponent,
    MessageCommentDeleteDialogComponent,
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
        MessageCommentComponent,
        MessageCommentDetailComponent,
        MessageCommentDialogComponent,
        MessageCommentDeleteDialogComponent,
        MessageCommentPopupComponent,
        MessageCommentDeletePopupComponent,
    ],
    entryComponents: [
        MessageCommentComponent,
        MessageCommentDialogComponent,
        MessageCommentPopupComponent,
        MessageCommentDeleteDialogComponent,
        MessageCommentDeletePopupComponent,
    ],
    providers: [
        MessageCommentService,
        MessageCommentPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BackendMessageCommentModule {}
