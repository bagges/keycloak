package org.keycloak.services.clientregistration;

import org.keycloak.events.EventBuilder;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.ErrorResponseException;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class ClientRegistrationService {

    private EventBuilder event;

    @Context
    private KeycloakSession session;

    public ClientRegistrationService(EventBuilder event) {
        this.event = event;
    }

    @Path("{provider}")
    public Object getProvider(@PathParam("provider") String providerId) {
        checkSsl();

        ClientRegistrationProvider provider = session.getProvider(ClientRegistrationProvider.class, providerId);
        provider.setEvent(event);
        provider.setAuth(new ClientRegAuth(session, event));
        return provider;
    }

    private void checkSsl() {
        if (!session.getContext().getUri().getBaseUri().getScheme().equals("https")) {
            if (session.getContext().getRealm().getSslRequired().isRequired(session.getContext().getConnection())) {
                throw new ErrorResponseException("invalid_request", "HTTPS required", Response.Status.FORBIDDEN);
            }
        }
    }

}
