import { BaseEntity } from './../../shared';

export class DaySechudlerInfoMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public tuanId?: number,
        public time?: any,
        public weather?: string,
        public dayName?: string,
        public mood?: string,
        public amSchedule?: string,
        public pmSchedule?: string,
        public creator?: string,
        public createDate?: any,
        public updateDate?: any,
        public tuanInfoId?: number,
    ) {
    }
}
