import { browser, element, by } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';

describe('MessageComment e2e test', () => {

    let navBarPage: NavBarPage;
    let messageCommentDialogPage: MessageCommentDialogPage;
    let messageCommentComponentsPage: MessageCommentComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load MessageComments', () => {
        navBarPage.goToEntity('message-comment-my-suffix');
        messageCommentComponentsPage = new MessageCommentComponentsPage();
        expect(messageCommentComponentsPage.getTitle())
            .toMatch(/backendApp.messageComment.home.title/);

    });

    it('should load create MessageComment dialog', () => {
        messageCommentComponentsPage.clickOnCreateButton();
        messageCommentDialogPage = new MessageCommentDialogPage();
        expect(messageCommentDialogPage.getModalTitle())
            .toMatch(/backendApp.messageComment.home.createOrEditLabel/);
        messageCommentDialogPage.close();
    });

    it('should create and save MessageComments', () => {
        messageCommentComponentsPage.clickOnCreateButton();
        messageCommentDialogPage.setMessageBelongIdInput('5');
        expect(messageCommentDialogPage.getMessageBelongIdInput()).toMatch('5');
        messageCommentDialogPage.setCreatorInput('creator');
        expect(messageCommentDialogPage.getCreatorInput()).toMatch('creator');
        messageCommentDialogPage.setCreateDateInput(12310020012301);
        expect(messageCommentDialogPage.getCreateDateInput()).toMatch('2001-12-31T02:30');
        messageCommentDialogPage.setUpdateDateInput(12310020012301);
        expect(messageCommentDialogPage.getUpdateDateInput()).toMatch('2001-12-31T02:30');
        messageCommentDialogPage.setBodyInput('body');
        expect(messageCommentDialogPage.getBodyInput()).toMatch('body');
        messageCommentDialogPage.messageSelectLastOption();
        messageCommentDialogPage.save();
        expect(messageCommentDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class MessageCommentComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-message-comment-my-suffix div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class MessageCommentDialogPage {
    modalTitle = element(by.css('h4#myMessageCommentLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    messageBelongIdInput = element(by.css('input#field_messageBelongId'));
    creatorInput = element(by.css('input#field_creator'));
    createDateInput = element(by.css('input#field_createDate'));
    updateDateInput = element(by.css('input#field_updateDate'));
    bodyInput = element(by.css('input#field_body'));
    messageSelect = element(by.css('select#field_message'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setMessageBelongIdInput = function(messageBelongId) {
        this.messageBelongIdInput.sendKeys(messageBelongId);
    };

    getMessageBelongIdInput = function() {
        return this.messageBelongIdInput.getAttribute('value');
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

    setBodyInput = function(body) {
        this.bodyInput.sendKeys(body);
    };

    getBodyInput = function() {
        return this.bodyInput.getAttribute('value');
    };

    messageSelectLastOption = function() {
        this.messageSelect.all(by.tagName('option')).last().click();
    };

    messageSelectOption = function(option) {
        this.messageSelect.sendKeys(option);
    };

    getMessageSelect = function() {
        return this.messageSelect;
    };

    getMessageSelectedOption = function() {
        return this.messageSelect.element(by.css('option:checked')).getText();
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
