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
        navBarPage.goToEntity('message-comment');
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
        messageCommentDialogPage.setMessageCommentInput('messageComment');
        expect(messageCommentDialogPage.getMessageCommentInput()).toMatch('messageComment');
        messageCommentDialogPage.setMessageIdInput('5');
        expect(messageCommentDialogPage.getMessageIdInput()).toMatch('5');
        messageCommentDialogPage.save();
        expect(messageCommentDialogPage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class MessageCommentComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-message-comment div h2 span')).first();

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
    messageCommentInput = element(by.css('input#field_messageComment'));
    messageIdInput = element(by.css('input#field_messageId'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setMessageCommentInput = function(messageComment) {
        this.messageCommentInput.sendKeys(messageComment);
    };

    getMessageCommentInput = function() {
        return this.messageCommentInput.getAttribute('value');
    };

    setMessageIdInput = function(messageId) {
        this.messageIdInput.sendKeys(messageId);
    };

    getMessageIdInput = function() {
        return this.messageIdInput.getAttribute('value');
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
