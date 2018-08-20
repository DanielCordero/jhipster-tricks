import { element, by, ElementFinder } from 'protractor';

export class ConfiguracionVariableComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-configuracion-variable-front div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class ConfiguracionVariableUpdatePage {
    pageTitle = element(by.id('jhi-configuracion-variable-front-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    fechaInput = element(by.id('field_fecha'));
    vecesInput = element(by.id('field_veces'));
    otrocampoInput = element(by.id('field_otrocampo'));
    campoadicionalInput = element(by.id('field_campoadicional'));
    variableSelect = element(by.id('field_variable'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setFechaInput(fecha) {
        await this.fechaInput.sendKeys(fecha);
    }

    async getFechaInput() {
        return this.fechaInput.getAttribute('value');
    }

    async setVecesInput(veces) {
        await this.vecesInput.sendKeys(veces);
    }

    async getVecesInput() {
        return this.vecesInput.getAttribute('value');
    }

    async setOtrocampoInput(otrocampo) {
        await this.otrocampoInput.sendKeys(otrocampo);
    }

    async getOtrocampoInput() {
        return this.otrocampoInput.getAttribute('value');
    }

    async setCampoadicionalInput(campoadicional) {
        await this.campoadicionalInput.sendKeys(campoadicional);
    }

    async getCampoadicionalInput() {
        return this.campoadicionalInput.getAttribute('value');
    }

    async variableSelectLastOption() {
        await this.variableSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async variableSelectOption(option) {
        await this.variableSelect.sendKeys(option);
    }

    getVariableSelect(): ElementFinder {
        return this.variableSelect;
    }

    async getVariableSelectedOption() {
        return this.variableSelect.element(by.css('option:checked')).getText();
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
