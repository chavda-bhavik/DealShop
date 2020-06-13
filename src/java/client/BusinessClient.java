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
 * Jersey REST client generated for REST resource:BusinessResource
 * [business]<br>
 * USAGE:
 * <pre>
 *        BusinessClient client = new BusinessClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author bhavik
 */
public class BusinessClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/DealShop/webresources";

    public BusinessClient(String token) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new RestFilter(token));
        webTarget = client.target(BASE_URI).path("business");
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

    public void addDealDetails(Object requestEntity, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealDetails/{0}", new Object[]{did})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getSoldDealsUsages(Class<T> responseType, String businessId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("dealUsage/{0}", new Object[]{businessId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T addUsage(Class<T> responseType, String usageId, String secretCode) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addUsage/{0}/{1}", new Object[]{usageId, secretCode})).request().post(null, responseType);
    }

    public void submitDeal(String dealId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("submitdeal/{0}", new Object[]{dealId})).request().post(null);
    }

    public void removeDealPhoto(String photoid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealphotos/{0}", new Object[]{photoid})).request().delete();
    }

    public void setBusinessInfo(Object requestEntity, String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("info/{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeDealMenu(String menuid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealMenu/{0}", new Object[]{menuid})).request().delete();
    }

    public void addBusinessDeal(Object requestEntity, String bid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deals/{0}", new Object[]{bid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getDealsPayments(Class<T> responseType, String did) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("dealsPayments/{0}", new Object[]{did}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeBusinessPhoto(String bid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("businessphotos/{0}", new Object[]{bid})).request().delete();
    }

    public <T> T getAllDeals(Class<T> responseType, String businessid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("deals/{0}", new Object[]{businessid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void editDealMenu(Object requestEntity) throws ClientErrorException {
        webTarget.path("dealMenu").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void registerBusiness(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void changeDealUsage(String dealid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealUsage/{0}", new Object[]{dealid})).request().post(null);
    }

    public void editDeal(Object requestEntity) throws ClientErrorException {
        webTarget.path("deals").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeDeal(String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deals/{0}", new Object[]{did})).request().delete();
    }

    public <T> T getBusinessRedeems(Class<T> responseType, String businessId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("redeems/{0}", new Object[]{businessId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void setBusinessLinks(Object requestEntity, String bid, String linkId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("links/{0}/{1}", new Object[]{bid, linkId})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void editBusiness(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void setBusinessPhotos(Object requestEntity, String bid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("photos/{0}", new Object[]{bid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void removeDealDetails(String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealDetails/{0}", new Object[]{did})).request().delete();
    }

    public void editDealDetails(Object requestEntity, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealDetails/{0}", new Object[]{did})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void setDealPhotos(Object requestEntity, String dealid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealphotos/{0}", new Object[]{dealid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void addDealMenu(Object requestEntity, String dealid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("dealMenu/{0}", new Object[]{dealid})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void submitBusiness(String bid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("submit/{0}", new Object[]{bid})).request().post(null);
    }

    public void removeBusiness(String bid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{bid})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
