import { BaseEntity } from './../../shared';

export class TuanMemberMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public tuanId?: number,
        public memberType?: string,
        public memberDescrption?: string,
        public yearsOld?: number,
        public sex?: string,
        public fromCity?: string,
        public joinTime?: any,
        public email?: string,
        public phoneNumber?: string,
        public creator?: string,
        public createDate?: any,
        public updateDate?: any,
        public tuanInfoId?: number,
    ) {
    }
}
