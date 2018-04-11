import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('DaySechudlerInfo e2e test', () => {

    let navBarPage: NavBarPage;
    let daySechudlerInfoDialogPage: DaySechudlerInfoDialogPage;
    let daySechudlerInfoComponentsPage: DaySechudlerInfoComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load DaySechudlerInfos', () => {
        navBarPage.goToEntity('day-sechudler-info-my-suffix');
        daySechudlerInfoComponentsPage = new DaySechudlerInfoComponentsPage();
        expect(daySechudlerInfoComponentsPage.getTitle())
            .toMatch(/backendApp.daySechudlerInfo.home.title/);

    });

    it('should load create DaySechudlerInfo dialog', () => {
        daySechudlerInfoComponentsPage.clickOnCreateButton();
        daySechudlerInfoDialogPage = new DaySechudlerInfoDialogPage();
        expect(daySechudlerInfoDialogPage.getModalTitle())
            .toMatch(/backendApp.daySechudlerInfo.home.createOrEditLabel/);
        daySechudlerInfoDialogPage.close();
    });

    it('should create and save DaySechudlerInfos', () => {
        daySechudlerInfoComponentsPage.clickOnCreateButton();
        daySechudlerInfoDialogPage.setTuanIdInput('5');
        expect(daySechudlerInfoDialogPage.getTuanIdInput()).toMatch('5');
        daySechudlerInfoDialogPage.setTimeInput(12310020012301);
        expect(daySechudlerInfoDialogPage.getTimeInput()).toMatch('2001-12-31T02:30');
        daySechudlerInfoDialogPage.setWeatherInput('weather');
        expect(daySechudlerInfoDialogPage.getWeatherInput()).toMatch('weather');
        daySechudlerInfoDialogPage.setDayNameInput('dayName');
        expect(daySechudlerInfoDialogPage.getDayNameInput()).toMatch('dayName');
        daySechudlerInfoDialogPage.setMoodInput('mood');
        expect(daySechudlerInfoDialogPage.getMoodInput()).toMatch('mood');
        daySechudlerInfoDialogPage.setAmScheduleInput('amSchedule');
        expect(daySechudlerInfoDialogPage.getAmScheduleInput()).toMatch('amSchedule');
        daySechudlerInfoDialogPage.setPmScheduleInput('pmSchedule');
        expect(daySechudlerInfoDialogPage.getPmScheduleInput()).toMatch('pmSchedule');
        daySechudlerInfoDialogPage.setCreatorInput('creator');
        expect(daySechudlerInfoDialogPage.getCreatorInput()).toMatch('creator');
        daySechudlerInfoDialogPage.setCreateDateInput(12310020012301);
        expect(daySechudlerInfoDialogPage.getCreateDateInput()).toMatch('2001-12-31T02:30');
        daySechudlerInfoDialogPage.setUpdateDateInput(12310020012301);
        expect(daySechudlerInfoDialogPage.getUpdateDateInput()).toMatch('2001-12-31T02:30');
        daySechudlerInfoDialogPage.tuanInfoSelectLastOption();
        daySechudlerInfoDialogPage.save();
        expect(daySechudlerInfoDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class DaySechudlerInfoComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-day-sechudler-info-my-suffix div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DaySechudlerInfoDialogPage {
    modalTitle = element(by.css('h4#myDaySechudlerInfoLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    tuanIdInput = element(by.css('input#field_tuanId'));
    timeInput = element(by.css('input#field_time'));
    weatherInput = element(by.css('input#field_weather'));
    dayNameInput = element(by.css('input#field_dayName'));
    moodInput = element(by.css('input#field_mood'));
    amScheduleInput = element(by.css('input#field_amSchedule'));
    pmScheduleInput = element(by.css('input#field_pmSchedule'));
    creatorInput = element(by.css('input#field_creator'));
    createDateInput = element(by.css('input#field_createDate'));
    updateDateInput = element(by.css('input#field_updateDate'));
    tuanInfoSelect = element(by.css('select#field_tuanInfo'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setTuanIdInput = function(tuanId) {
        this.tuanIdInput.sendKeys(tuanId);
    };

    getTuanIdInput = function() {
        return this.tuanIdInput.getAttribute('value');
    };

    setTimeInput = function(time) {
        this.timeInput.sendKeys(time);
    };

    getTimeInput = function() {
        return this.timeInput.getAttribute('value');
    };

    setWeatherInput = function(weather) {
        this.weatherInput.sendKeys(weather);
    };

    getWeatherInput = function() {
        return this.weatherInput.getAttribute('value');
    };

    setDayNameInput = function(dayName) {
        this.dayNameInput.sendKeys(dayName);
    };

    getDayNameInput = function() {
        return this.dayNameInput.getAttribute('value');
    };

    setMoodInput = function(mood) {
        this.moodInput.sendKeys(mood);
    };

    getMoodInput = function() {
        return this.moodInput.getAttribute('value');
    };

    setAmScheduleInput = function(amSchedule) {
        this.amScheduleInput.sendKeys(amSchedule);
    };

    getAmScheduleInput = function() {
        return this.amScheduleInput.getAttribute('value');
    };

    setPmScheduleInput = function(pmSchedule) {
        this.pmScheduleInput.sendKeys(pmSchedule);
    };

    getPmScheduleInput = function() {
        return this.pmScheduleInput.getAttribute('value');
    };

    setCreatorInput = function(creator) {
        this.creatorInput.sendKeys(creator);
    };

    getCreatorInput = function() {
        return this.creatorInput.getAttribute('value');
    };

    setCreateDateInput = function(createDate) {
        this.createDateInput.sendKeys(createDate);
    };

    getCreateDateInput = function() {
        return this.createDateInput.getAttribute('value');
    };

    setUpdateDateInput = function(updateDate) {
        this.updateDateInput.sendKeys(updateDate);
    };

    getUpdateDateInput = function() {
        return this.updateDateInput.getAttribute('value');
    };

    tuanInfoSelectLastOption = function() {
        this.tuanInfoSelect.all(by.tagName('option')).last().click();
    };

    tuanInfoSelectOption = function(option) {
        this.tuanInfoSelect.sendKeys(option);
    };

    getTuanInfoSelect = function() {
        return this.tuanInfoSelect;
    };

    getTuanInfoSelectedOption = function() {
        return this.tuanInfoSelect.element(by.css('option:checked')).getText();
    };

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
