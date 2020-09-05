package no.maardal.fant.market;

import javax.net.ssl.SSLEngineResult;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class FantServiceIntegrationTest extends JerseyTest {
    
    @Override
    protected Application configure() {
        return new ResourceConfig(FantService.class);
    }
    
    @Test
    public void givenGetItemID_whenCorrectRequest_thenResponseIsItemID() {
        Response response = target("items/4").request().get();
        
        Assert.assertEquals("Http Response should be 200: ", 
                Status.OK.getStatusCode(),
                response.getStatus());
        Assert.assertEquals("Http Content-type should be ",
               MediaType.TEXT_HTML,
               response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        
        String content = response.readEntity(String.class);
        Assert.assertEquals("Content of response is: ", "itemID 4", content);
        //final String response = target("items/4").request().get(String.class);
        //Assert.assertTrue("itemID 4".equals(response));
    }
    
}
