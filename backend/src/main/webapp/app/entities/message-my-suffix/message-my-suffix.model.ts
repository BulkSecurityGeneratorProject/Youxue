import { BaseEntity } from './../../shared';

export class MessageMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public tuanId?: number,
        public body?: string,
        public dayId?: number,
        public type?: string,
        public title?: string,
        public pig1?: string,
        public pig2?: string,
        public pig3?: string,
        public voice?: string,
        public vudio?: string,
        public deployToQuan?: boolean,
        public creator?: string,
        public createDate?: any,
        public updateDate?: any,
        public tuanInfoId?: number,
        public comments?: BaseEntity[],
    ) {
        this.deployToQuan = false;
    }
}
