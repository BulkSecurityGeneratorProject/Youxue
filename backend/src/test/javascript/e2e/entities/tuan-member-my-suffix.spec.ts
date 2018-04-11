import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('TuanMember e2e test', () => {

    let navBarPage: NavBarPage;
    let tuanMemberDialogPage: TuanMemberDialogPage;
    let tuanMemberComponentsPage: TuanMemberComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load TuanMembers', () => {
        navBarPage.goToEntity('tuan-member-my-suffix');
        tuanMemberComponentsPage = new TuanMemberComponentsPage();
        expect(tuanMemberComponentsPage.getTitle())
            .toMatch(/backendApp.tuanMember.home.title/);

    });

    it('should load create TuanMember dialog', () => {
        tuanMemberComponentsPage.clickOnCreateButton();
        tuanMemberDialogPage = new TuanMemberDialogPage();
        expect(tuanMemberDialogPage.getModalTitle())
            .toMatch(/backendApp.tuanMember.home.createOrEditLabel/);
        tuanMemberDialogPage.close();
    });

    it('should create and save TuanMembers', () => {
        tuanMemberComponentsPage.clickOnCreateButton();
        tuanMemberDialogPage.setTuanIdInput('5');
        expect(tuanMemberDialogPage.getTuanIdInput()).toMatch('5');
        tuanMemberDialogPage.setMemberTypeInput('memberType');
        expect(tuanMemberDialogPage.getMemberTypeInput()).toMatch('memberType');
        tuanMemberDialogPage.setMemberDescrptionInput('memberDescrption');
        expect(tuanMemberDialogPage.getMemberDescrptionInput()).toMatch('memberDescrption');
        tuanMemberDialogPage.setYearsOldInput('5');
        expect(tuanMemberDialogPage.getYearsOldInput()).toMatch('5');
        tuanMemberDialogPage.setSexInput('sex');
        expect(tuanMemberDialogPage.getSexInput()).toMatch('sex');
        tuanMemberDialogPage.setFromCityInput('fromCity');
        expect(tuanMemberDialogPage.getFromCityInput()).toMatch('fromCity');
        tuanMemberDialogPage.setJoinTimeInput(12310020012301);
        expect(tuanMemberDialogPage.getJoinTimeInput()).toMatch('2001-12-31T02:30');
        tuanMemberDialogPage.setEmailInput('email');
        expect(tuanMemberDialogPage.getEmailInput()).toMatch('email');
        tuanMemberDialogPage.setPhoneNumberInput('phoneNumber');
        expect(tuanMemberDialogPage.getPhoneNumberInput()).toMatch('phoneNumber');
        tuanMemberDialogPage.setCreatorInput('creator');
        expect(tuanMemberDialogPage.getCreatorInput()).toMatch('creator');
        tuanMemberDialogPage.setCreateDateInput(12310020012301);
        expect(tuanMemberDialogPage.getCreateDateInput()).toMatch('2001-12-31T02:30');
        tuanMemberDialogPage.setUpdateDateInput(12310020012301);
        expect(tuanMemberDialogPage.getUpdateDateInput()).toMatch('2001-12-31T02:30');
        tuanMemberDialogPage.tuanInfoSelectLastOption();
        tuanMemberDialogPage.save();
        expect(tuanMemberDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class TuanMemberComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-tuan-member-my-suffix div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class TuanMemberDialogPage {
    modalTitle = element(by.css('h4#myTuanMemberLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    tuanIdInput = element(by.css('input#field_tuanId'));
    memberTypeInput = element(by.css('input#field_memberType'));
    memberDescrptionInput = element(by.css('input#field_memberDescrption'));
    yearsOldInput = element(by.css('input#field_yearsOld'));
    sexInput = element(by.css('input#field_sex'));
    fromCityInput = element(by.css('input#field_fromCity'));
    joinTimeInput = element(by.css('input#field_joinTime'));
    emailInput = element(by.css('input#field_email'));
    phoneNumberInput = element(by.css('input#field_phoneNumber'));
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

    setMemberTypeInput = function(memberType) {
        this.memberTypeInput.sendKeys(memberType);
    };

    getMemberTypeInput = function() {
        return this.memberTypeInput.getAttribute('value');
    };

    setMemberDescrptionInput = function(memberDescrption) {
        this.memberDescrptionInput.sendKeys(memberDescrption);
    };

    getMemberDescrptionInput = function() {
        return this.memberDescrptionInput.getAttribute('value');
    };

    setYearsOldInput = function(yearsOld) {
        this.yearsOldInput.sendKeys(yearsOld);
    };

    getYearsOldInput = function() {
        return this.yearsOldInput.getAttribute('value');
    };

    setSexInput = function(sex) {
        this.sexInput.sendKeys(sex);
    };

    getSexInput = function() {
        return this.sexInput.getAttribute('value');
    };

    setFromCityInput = function(fromCity) {
        this.fromCityInput.sendKeys(fromCity);
    };

    getFromCityInput = function() {
        return this.fromCityInput.getAttribute('value');
    };

    setJoinTimeInput = function(joinTime) {
        this.joinTimeInput.sendKeys(joinTime);
    };

    getJoinTimeInput = function() {
        return this.joinTimeInput.getAttribute('value');
    };

    setEmailInput = function(email) {
        this.emailInput.sendKeys(email);
    };

    getEmailInput = function() {
        return this.emailInput.getAttribute('value');
    };

    setPhoneNumberInput = function(phoneNumber) {
        this.phoneNumberInput.sendKeys(phoneNumber);
    };

    getPhoneNumberInput = function() {
        return this.phoneNumberInput.getAttribute('value');
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
