package util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luffy
 * @date 2021/10/25
 */
public class TGT {

    public static String getTGT(final String app, final String username, final String password) {
        try {
            final CookieStore httpCookieStore = (CookieStore)new BasicCookieStore();
            final CloseableHttpClient client = HttpClients.createDefault();
            final HttpPost httpPost = new HttpPost("https://server.smart-sso.com:8443/cas/v1/tickets");
            final List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add((NameValuePair)new BasicNameValuePair("app", app));
            params.add((NameValuePair)new BasicNameValuePair("username", username));
            params.add((NameValuePair)new BasicNameValuePair("password", password));
            httpPost.setEntity((HttpEntity)new UrlEncodedFormEntity((List)params));
            final HttpResponse response = (HttpResponse)client.execute((HttpUriRequest)httpPost);
            final Header headerLocation = response.getFirstHeader("Location");
            final String location = (headerLocation == null) ? null : headerLocation.getValue();
            System.out.println("Location：" + location);
            if (location != null) {
                return location.substring(location.lastIndexOf("/") + 1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        getTGT("default", "admin", "Admin_10086");
    }
}
