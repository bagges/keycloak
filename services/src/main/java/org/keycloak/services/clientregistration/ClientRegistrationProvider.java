package org.keycloak.services.clientregistration;

import org.keycloak.events.EventBuilder;
import org.keycloak.models.RealmModel;
import org.keycloak.provider.Provider;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public interface ClientRegistrationProvider extends Provider {

    void setAuth(ClientRegAuth auth);

    void setEvent(EventBuilder event);

}
