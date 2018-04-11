import { BaseEntity } from './../../shared';

export class TuanInfoMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public leader?: string,
        public tuanName?: string,
        public tuanDescrption?: string,
        public city?: string,
        public country?: string,
        public teamMemberCount?: number,
        public tuanStartTime?: any,
        public tuanEndTime?: any,
        public tuanCreatTime?: any,
        public creator?: string,
        public createDate?: any,
        public updateDate?: any,
        public tuanmembers?: BaseEntity[],
        public daysechudlerinfos?: BaseEntity[],
        public messages?: BaseEntity[],
    ) {
    }
}
