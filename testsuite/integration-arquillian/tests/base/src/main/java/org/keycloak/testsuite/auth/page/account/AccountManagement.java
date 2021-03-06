/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.testsuite.auth.page.account;

import javax.ws.rs.core.UriBuilder;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.testsuite.auth.page.AuthRealm;
import static org.keycloak.testsuite.util.WaitUtils.waitGuiForElementPresent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author <a href="mailto:pmensik@redhat.com">Petr Mensik</a>
 * @author tkyjovsk
 */
public class AccountManagement extends AuthRealm {

    @Override
    public UriBuilder createUriBuilder() {
        return super.createUriBuilder()
                .path("account");
    }

    @FindBy(xpath = "//a[@id='referer']")
    private WebElement backToRefererLink;

    @FindBy(linkText = "Sign Out")
    private WebElement signOutLink;

    @FindBy(linkText = "Account")
    private WebElement accountLink;

    @FindBy(linkText = "Password")
    private WebElement passwordLink;

    @FindBy(linkText = "Authenticator")
    private WebElement authenticatorLink;

    @FindBy(linkText = "Sessions")
    private WebElement sessionsLink;

    @FindBy(linkText = "Applications")
    private WebElement applicationsLink;

    @FindByJQuery("button[value='Save']")
    private WebElement save;

    @FindBy(xpath = "//div[@id='kc-error-message']/p")
    private WebElement error;

    public String getErrorMessage() {
        waitGuiForElementPresent(error, "Error message should be visible");
        return error.getText();
    }
    
    public void backToReferer() {
        backToRefererLink.click();
    }

    public void signOut() {
        signOutLink.click();
    }

    public void account() {
        accountLink.click();
    }

    public void password() {
        passwordLink.click();
    }

    public void authenticator() {
        authenticatorLink.click();
    }

    public void sessions() {
        sessionsLink.click();
    }

    public void applications() {
        applicationsLink.click();
    }

    public void save() {
        save.click();
    }
    
    public RealmResource realmResource() {
        return keycloak().realm(getAuthRealm());
    }
    
    public void waitForAccountLinkPresent() {
        waitGuiForElementPresent(accountLink, "account link should be present");
    }
}
