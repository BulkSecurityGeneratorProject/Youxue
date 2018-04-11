import { BaseEntity } from './../../shared';

export class MessageComment implements BaseEntity {
    constructor(
        public id?: number,
        public messageComment?: string,
        public messageId?: number,
    ) {
    }
}
