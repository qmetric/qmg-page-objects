package com.qmetric.utilities;

import javax.net.ssl.*;
import javax.security.cert.X509Certificate;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class URLCheck {
    public static int getResponseCodeForDocLink(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            @Override public void checkClientTrusted(final java.security.cert.X509Certificate[] x509Certificates, final String s) throws CertificateException
            {
            }

            @Override public void checkServerTrusted(final java.security.cert.X509Certificate[] x509Certificates, final String s) throws CertificateException
            {
            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        try
        {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            urlConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            urlConnection.setHostnameVerifier(allHostsValid);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (KeyManagementException e)
        {
            e.printStackTrace();
        }

        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        urlConnection.setRequestProperty("Accept", "application/pdf");
        urlConnection.connect();
        return urlConnection.getResponseCode();
    }
}
