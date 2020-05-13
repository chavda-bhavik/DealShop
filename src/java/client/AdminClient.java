/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:AdminResource [admin]<br>
 * USAGE:
 * <pre>
 *        AdminClient client = new AdminClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author bhavik
 */
public class AdminClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/DealShop/webresources";

    public AdminClient(String token) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new RestFilter(token));
        webTarget = client.target(BASE_URI).path("admin");
    }
    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
            new javax.net.ssl.HostnameVerifier() {
                public boolean verify(String hostname,javax.net.ssl.SSLSession sslSession) {
                    if (hostname.equals("localhost")) {
                        return true;
                    }
                    return false;
                }
            }
        );
    }

    public void removeDealsCategory(String dcId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealsCategory/{0}", new Object[]{dcId})).request().delete();
    }

    public void removeInformation(String biId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessInfo/{0}", new Object[]{biId})).request().delete();
    }

    public void editBusinessType(Object requestEntity, String btId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessTypes/{0}", new Object[]{btId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void addBusinessCategory(Object requestEntity) throws ClientErrorException {
        webTarget.path("businessCategory").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getBusinessInfoList(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("businessInfo");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addOffer(Object requestEntity) throws ClientErrorException {
        webTarget.path("offer").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void addInformation(Object requestEntity) throws ClientErrorException {
        webTarget.path("businessInfo").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeBusinessCategory(String bcId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessCategory/{0}", new Object[]{bcId})).request().delete();
    }

    public <T> T getAllCities(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("city");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllDealsCategory(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("dealsCategory");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addState(Object requestEntity) throws ClientErrorException {
        webTarget.path("state").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void editLink(Object requestEntity, String linkId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessLinks/{0}", new Object[]{linkId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void editCity(Object requestEntity, String cityId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("city/{0}", new Object[]{cityId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeOffer(String oId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("offer/{0}", new Object[]{oId})).request().delete();
    }

    public <T> T getAllStates(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("state");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void editDealsCategory(Object requestEntity, String dcId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealsCategory/{0}", new Object[]{dcId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeBusinessType(String btId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessTypes/{0}", new Object[]{btId})).request().delete();
    }

    public void editBusinessCategory(Object requestEntity, String bcId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessCategory/{0}", new Object[]{bcId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeLink(String linkId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessLinks/{0}", new Object[]{linkId})).request().delete();
    }

    public <T> T getBusinessCategory(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("businessCategory");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void editOffer(Object requestEntity, String oId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("offer/{0}", new Object[]{oId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void addCity(Object requestEntity) throws ClientErrorException {
        webTarget.path("city").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void editInformation(Object requestEntity, String biId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessInfo/{0}", new Object[]{biId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getAllOffers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("offer");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllLinks(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("businessLinks");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void editState(Object requestEntity, String stateId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("state/{0}", new Object[]{stateId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeState(String stateId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("state/{0}", new Object[]{stateId})).request().delete();
    }

    public <T> T getAllBusinessTypes(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("businessTypes");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addDealsCategory(Object requestEntity) throws ClientErrorException {
        webTarget.path("dealsCategory").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeCity(String cityId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("city/{0}", new Object[]{cityId})).request().delete();
    }

    public void addLink(Object requestEntity) throws ClientErrorException {
        webTarget.path("businessLinks").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void addBusinessType(Object requestEntity) throws ClientErrorException {
        webTarget.path("businessTypes").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void close() {
        client.close();
    }
    
}
