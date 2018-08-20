import { element, by, ElementFinder } from 'protractor';

export class VariableComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-variable-front div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class VariableUpdatePage {
    pageTitle = element(by.id('jhi-variable-front-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nombreInput = element(by.id('field_nombre'));
    descripcionInput = element(by.id('field_descripcion'));
    campoInput = element(by.id('field_campo'));
    cuakInput = element(by.id('field_cuak'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNombreInput(nombre) {
        await this.nombreInput.sendKeys(nombre);
    }

    async getNombreInput() {
        return this.nombreInput.getAttribute('value');
    }

    async setDescripcionInput(descripcion) {
        await this.descripcionInput.sendKeys(descripcion);
    }

    async getDescripcionInput() {
        return this.descripcionInput.getAttribute('value');
    }

    async setCampoInput(campo) {
        await this.campoInput.sendKeys(campo);
    }

    async getCampoInput() {
        return this.campoInput.getAttribute('value');
    }

    async setCuakInput(cuak) {
        await this.cuakInput.sendKeys(cuak);
    }

    async getCuakInput() {
        return this.cuakInput.getAttribute('value');
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
