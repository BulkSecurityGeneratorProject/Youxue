import { BaseEntity } from './../../shared';

export class MessageCommentMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public messageBelongId?: number,
        public creator?: string,
        public createDate?: any,
        public updateDate?: any,
        public body?: string,
        public messageId?: number,
    ) {
    }
}
