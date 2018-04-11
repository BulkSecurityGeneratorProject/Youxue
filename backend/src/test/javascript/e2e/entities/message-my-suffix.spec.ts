import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('Message e2e test', () => {

    let navBarPage: NavBarPage;
    let messageDialogPage: MessageDialogPage;
    let messageComponentsPage: MessageComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Messages', () => {
        navBarPage.goToEntity('message-my-suffix');
        messageComponentsPage = new MessageComponentsPage();
        expect(messageComponentsPage.getTitle())
            .toMatch(/backendApp.message.home.title/);

    });

    it('should load create Message dialog', () => {
        messageComponentsPage.clickOnCreateButton();
        messageDialogPage = new MessageDialogPage();
        expect(messageDialogPage.getModalTitle())
            .toMatch(/backendApp.message.home.createOrEditLabel/);
        messageDialogPage.close();
    });

    it('should create and save Messages', () => {
        messageComponentsPage.clickOnCreateButton();
        messageDialogPage.setTuanIdInput('5');
        expect(messageDialogPage.getTuanIdInput()).toMatch('5');
        messageDialogPage.setBodyInput('body');
        expect(messageDialogPage.getBodyInput()).toMatch('body');
        messageDialogPage.setDayIdInput('5');
        expect(messageDialogPage.getDayIdInput()).toMatch('5');
        messageDialogPage.setTypeInput('type');
        expect(messageDialogPage.getTypeInput()).toMatch('type');
        messageDialogPage.setTitleInput('title');
        expect(messageDialogPage.getTitleInput()).toMatch('title');
        messageDialogPage.setPig1Input('pig1');
        expect(messageDialogPage.getPig1Input()).toMatch('pig1');
        messageDialogPage.setPig2Input('pig2');
        expect(messageDialogPage.getPig2Input()).toMatch('pig2');
        messageDialogPage.setPig3Input('pig3');
        expect(messageDialogPage.getPig3Input()).toMatch('pig3');
        messageDialogPage.setVoiceInput('voice');
        expect(messageDialogPage.getVoiceInput()).toMatch('voice');
        messageDialogPage.setVudioInput('vudio');
        expect(messageDialogPage.getVudioInput()).toMatch('vudio');
        messageDialogPage.getDeployToQuanInput().isSelected().then((selected) => {
            if (selected) {
                messageDialogPage.getDeployToQuanInput().click();
                expect(messageDialogPage.getDeployToQuanInput().isSelected()).toBeFalsy();
            } else {
                messageDialogPage.getDeployToQuanInput().click();
                expect(messageDialogPage.getDeployToQuanInput().isSelected()).toBeTruthy();
            }
        });
        messageDialogPage.setCreatorInput('creator');
        expect(messageDialogPage.getCreatorInput()).toMatch('creator');
        messageDialogPage.setCreateDateInput(12310020012301);
        expect(messageDialogPage.getCreateDateInput()).toMatch('2001-12-31T02:30');
        messageDialogPage.setUpdateDateInput(12310020012301);
        expect(messageDialogPage.getUpdateDateInput()).toMatch('2001-12-31T02:30');
        messageDialogPage.tuanInfoSelectLastOption();
        messageDialogPage.save();
        expect(messageDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class MessageComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-message-my-suffix div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class MessageDialogPage {
    modalTitle = element(by.css('h4#myMessageLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    tuanIdInput = element(by.css('input#field_tuanId'));
    bodyInput = element(by.css('input#field_body'));
    dayIdInput = element(by.css('input#field_dayId'));
    typeInput = element(by.css('input#field_type'));
    titleInput = element(by.css('input#field_title'));
    pig1Input = element(by.css('input#field_pig1'));
    pig2Input = element(by.css('input#field_pig2'));
    pig3Input = element(by.css('input#field_pig3'));
    voiceInput = element(by.css('input#field_voice'));
    vudioInput = element(by.css('input#field_vudio'));
    deployToQuanInput = element(by.css('input#field_deployToQuan'));
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

    setBodyInput = function(body) {
        this.bodyInput.sendKeys(body);
    };

    getBodyInput = function() {
        return this.bodyInput.getAttribute('value');
    };

    setDayIdInput = function(dayId) {
        this.dayIdInput.sendKeys(dayId);
    };

    getDayIdInput = function() {
        return this.dayIdInput.getAttribute('value');
    };

    setTypeInput = function(type) {
        this.typeInput.sendKeys(type);
    };

    getTypeInput = function() {
        return this.typeInput.getAttribute('value');
    };

    setTitleInput = function(title) {
        this.titleInput.sendKeys(title);
    };

    getTitleInput = function() {
        return this.titleInput.getAttribute('value');
    };

    setPig1Input = function(pig1) {
        this.pig1Input.sendKeys(pig1);
    };

    getPig1Input = function() {
        return this.pig1Input.getAttribute('value');
    };

    setPig2Input = function(pig2) {
        this.pig2Input.sendKeys(pig2);
    };

    getPig2Input = function() {
        return this.pig2Input.getAttribute('value');
    };

    setPig3Input = function(pig3) {
        this.pig3Input.sendKeys(pig3);
    };

    getPig3Input = function() {
        return this.pig3Input.getAttribute('value');
    };

    setVoiceInput = function(voice) {
        this.voiceInput.sendKeys(voice);
    };

    getVoiceInput = function() {
        return this.voiceInput.getAttribute('value');
    };

    setVudioInput = function(vudio) {
        this.vudioInput.sendKeys(vudio);
    };

    getVudioInput = function() {
        return this.vudioInput.getAttribute('value');
    };

    getDeployToQuanInput = function() {
        return this.deployToQuanInput;
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
