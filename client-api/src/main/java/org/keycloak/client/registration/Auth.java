package org.keycloak.client.registration;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.keycloak.common.util.Base64;
import org.keycloak.representations.idm.ClientRepresentation;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public abstract class Auth {

    public abstract void addAuth(HttpRequest request);

    public static Auth token(String token) {
        return new BearerTokenAuth(token);
    }

    public static Auth token(ClientRepresentation client) {
        return new BearerTokenAuth(client.getRegistrationAccessToken());
    }

    public static Auth client(String clientId, String clientSecret) {
        return new BasicAuth(clientId, clientSecret);
    }

    private static class BearerTokenAuth extends Auth {

        private String token;

        public BearerTokenAuth(String token) {
            this.token = token;
        }

        @Override
        public void addAuth(HttpRequest request) {
            request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }
    }

    private static class BasicAuth extends Auth {

        private String username;
        private String password;

        public BasicAuth(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public void addAuth(HttpRequest request) {
            String val = Base64.encodeBytes((username + ":" + password).getBytes());
            request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + val);
        }
    }

}
