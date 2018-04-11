import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('TuanInfo e2e test', () => {

    let navBarPage: NavBarPage;
    let tuanInfoDialogPage: TuanInfoDialogPage;
    let tuanInfoComponentsPage: TuanInfoComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load TuanInfos', () => {
        navBarPage.goToEntity('tuan-info-my-suffix');
        tuanInfoComponentsPage = new TuanInfoComponentsPage();
        expect(tuanInfoComponentsPage.getTitle())
            .toMatch(/backendApp.tuanInfo.home.title/);

    });

    it('should load create TuanInfo dialog', () => {
        tuanInfoComponentsPage.clickOnCreateButton();
        tuanInfoDialogPage = new TuanInfoDialogPage();
        expect(tuanInfoDialogPage.getModalTitle())
            .toMatch(/backendApp.tuanInfo.home.createOrEditLabel/);
        tuanInfoDialogPage.close();
    });

    it('should create and save TuanInfos', () => {
        tuanInfoComponentsPage.clickOnCreateButton();
        tuanInfoDialogPage.setLeaderInput('leader');
        expect(tuanInfoDialogPage.getLeaderInput()).toMatch('leader');
        tuanInfoDialogPage.setTuanNameInput('tuanName');
        expect(tuanInfoDialogPage.getTuanNameInput()).toMatch('tuanName');
        tuanInfoDialogPage.setTuanDescrptionInput('tuanDescrption');
        expect(tuanInfoDialogPage.getTuanDescrptionInput()).toMatch('tuanDescrption');
        tuanInfoDialogPage.setCityInput('city');
        expect(tuanInfoDialogPage.getCityInput()).toMatch('city');
        tuanInfoDialogPage.setCountryInput('country');
        expect(tuanInfoDialogPage.getCountryInput()).toMatch('country');
        tuanInfoDialogPage.setTeamMemberCountInput('5');
        expect(tuanInfoDialogPage.getTeamMemberCountInput()).toMatch('5');
        tuanInfoDialogPage.setTuanStartTimeInput(12310020012301);
        expect(tuanInfoDialogPage.getTuanStartTimeInput()).toMatch('2001-12-31T02:30');
        tuanInfoDialogPage.setTuanEndTimeInput(12310020012301);
        expect(tuanInfoDialogPage.getTuanEndTimeInput()).toMatch('2001-12-31T02:30');
        tuanInfoDialogPage.setTuanCreatTimeInput(12310020012301);
        expect(tuanInfoDialogPage.getTuanCreatTimeInput()).toMatch('2001-12-31T02:30');
        tuanInfoDialogPage.setCreatorInput('creator');
        expect(tuanInfoDialogPage.getCreatorInput()).toMatch('creator');
        tuanInfoDialogPage.setCreateDateInput(12310020012301);
        expect(tuanInfoDialogPage.getCreateDateInput()).toMatch('2001-12-31T02:30');
        tuanInfoDialogPage.setUpdateDateInput(12310020012301);
        expect(tuanInfoDialogPage.getUpdateDateInput()).toMatch('2001-12-31T02:30');
        tuanInfoDialogPage.save();
        expect(tuanInfoDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class TuanInfoComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-tuan-info-my-suffix div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class TuanInfoDialogPage {
    modalTitle = element(by.css('h4#myTuanInfoLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    leaderInput = element(by.css('input#field_leader'));
    tuanNameInput = element(by.css('input#field_tuanName'));
    tuanDescrptionInput = element(by.css('input#field_tuanDescrption'));
    cityInput = element(by.css('input#field_city'));
    countryInput = element(by.css('input#field_country'));
    teamMemberCountInput = element(by.css('input#field_teamMemberCount'));
    tuanStartTimeInput = element(by.css('input#field_tuanStartTime'));
    tuanEndTimeInput = element(by.css('input#field_tuanEndTime'));
    tuanCreatTimeInput = element(by.css('input#field_tuanCreatTime'));
    creatorInput = element(by.css('input#field_creator'));
    createDateInput = element(by.css('input#field_createDate'));
    updateDateInput = element(by.css('input#field_updateDate'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setLeaderInput = function(leader) {
        this.leaderInput.sendKeys(leader);
    };

    getLeaderInput = function() {
        return this.leaderInput.getAttribute('value');
    };

    setTuanNameInput = function(tuanName) {
        this.tuanNameInput.sendKeys(tuanName);
    };

    getTuanNameInput = function() {
        return this.tuanNameInput.getAttribute('value');
    };

    setTuanDescrptionInput = function(tuanDescrption) {
        this.tuanDescrptionInput.sendKeys(tuanDescrption);
    };

    getTuanDescrptionInput = function() {
        return this.tuanDescrptionInput.getAttribute('value');
    };

    setCityInput = function(city) {
        this.cityInput.sendKeys(city);
    };

    getCityInput = function() {
        return this.cityInput.getAttribute('value');
    };

    setCountryInput = function(country) {
        this.countryInput.sendKeys(country);
    };

    getCountryInput = function() {
        return this.countryInput.getAttribute('value');
    };

    setTeamMemberCountInput = function(teamMemberCount) {
        this.teamMemberCountInput.sendKeys(teamMemberCount);
    };

    getTeamMemberCountInput = function() {
        return this.teamMemberCountInput.getAttribute('value');
    };

    setTuanStartTimeInput = function(tuanStartTime) {
        this.tuanStartTimeInput.sendKeys(tuanStartTime);
    };

    getTuanStartTimeInput = function() {
        return this.tuanStartTimeInput.getAttribute('value');
    };

    setTuanEndTimeInput = function(tuanEndTime) {
        this.tuanEndTimeInput.sendKeys(tuanEndTime);
    };

    getTuanEndTimeInput = function() {
        return this.tuanEndTimeInput.getAttribute('value');
    };

    setTuanCreatTimeInput = function(tuanCreatTime) {
        this.tuanCreatTimeInput.sendKeys(tuanCreatTime);
    };

    getTuanCreatTimeInput = function() {
        return this.tuanCreatTimeInput.getAttribute('value');
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
