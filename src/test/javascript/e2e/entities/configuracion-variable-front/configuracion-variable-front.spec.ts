import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ConfiguracionVariableComponentsPage, ConfiguracionVariableUpdatePage } from './configuracion-variable-front.page-object';

describe('ConfiguracionVariable e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let configuracionVariableUpdatePage: ConfiguracionVariableUpdatePage;
    let configuracionVariableComponentsPage: ConfiguracionVariableComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load ConfiguracionVariables', async () => {
        await navBarPage.goToEntity('configuracion-variable-front');
        configuracionVariableComponentsPage = new ConfiguracionVariableComponentsPage();
        expect(await configuracionVariableComponentsPage.getTitle()).toMatch(/aplicacionApp.configuracionVariable.home.title/);
    });

    it('should load create ConfiguracionVariable page', async () => {
        await configuracionVariableComponentsPage.clickOnCreateButton();
        configuracionVariableUpdatePage = new ConfiguracionVariableUpdatePage();
        expect(await configuracionVariableUpdatePage.getPageTitle()).toMatch(/aplicacionApp.configuracionVariable.home.createOrEditLabel/);
        await configuracionVariableUpdatePage.cancel();
    });

    it('should create and save ConfiguracionVariables', async () => {
        await configuracionVariableComponentsPage.clickOnCreateButton();
        await configuracionVariableUpdatePage.setFechaInput('2000-12-31');
        expect(await configuracionVariableUpdatePage.getFechaInput()).toMatch('2000-12-31');
        await configuracionVariableUpdatePage.setVecesInput('5');
        expect(await configuracionVariableUpdatePage.getVecesInput()).toMatch('5');
        await configuracionVariableUpdatePage.setOtrocampoInput('5');
        expect(await configuracionVariableUpdatePage.getOtrocampoInput()).toMatch('5');
        await configuracionVariableUpdatePage.setCampoadicionalInput('campoadicional');
        expect(await configuracionVariableUpdatePage.getCampoadicionalInput()).toMatch('campoadicional');
        await configuracionVariableUpdatePage.variableSelectLastOption();
        await configuracionVariableUpdatePage.save();
        expect(await configuracionVariableUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
