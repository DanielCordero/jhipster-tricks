import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { VariableComponentsPage, VariableUpdatePage } from './variable-front.page-object';

describe('Variable e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let variableUpdatePage: VariableUpdatePage;
    let variableComponentsPage: VariableComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Variables', async () => {
        await navBarPage.goToEntity('variable-front');
        variableComponentsPage = new VariableComponentsPage();
        expect(await variableComponentsPage.getTitle()).toMatch(/aplicacionApp.variable.home.title/);
    });

    it('should load create Variable page', async () => {
        await variableComponentsPage.clickOnCreateButton();
        variableUpdatePage = new VariableUpdatePage();
        expect(await variableUpdatePage.getPageTitle()).toMatch(/aplicacionApp.variable.home.createOrEditLabel/);
        await variableUpdatePage.cancel();
    });

    it('should create and save Variables', async () => {
        await variableComponentsPage.clickOnCreateButton();
        await variableUpdatePage.setNombreInput('nombre');
        expect(await variableUpdatePage.getNombreInput()).toMatch('nombre');
        await variableUpdatePage.setDescripcionInput('descripcion');
        expect(await variableUpdatePage.getDescripcionInput()).toMatch('descripcion');
        await variableUpdatePage.setCampoInput('campo');
        expect(await variableUpdatePage.getCampoInput()).toMatch('campo');
        await variableUpdatePage.setCuakInput('5');
        expect(await variableUpdatePage.getCuakInput()).toMatch('5');
        await variableUpdatePage.save();
        expect(await variableUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
