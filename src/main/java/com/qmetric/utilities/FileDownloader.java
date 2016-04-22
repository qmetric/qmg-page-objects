package com.qmetric.utilities;

/**
 * Created by pgnanasekaran on 08/06/2015.
 */

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class FileDownloader {

    private static final Logger LOG = Logger.getLogger(FileDownloader.class);
    private String localDownloadPath = System.getProperty("java.io.tmpdir");
    private final int HTTP_CLIENT_TIMEOUT = 60;

    private String downloader(WebElement element, String attribute) throws Exception
    {
        String fileToDownloadLocation = element.getAttribute(attribute);
        if (fileToDownloadLocation.trim().equals(""))
            throw new NullPointerException("The element you have specified does not link to anything!");

        URL fileToDownload = new URL(fileToDownloadLocation);
        File downloadedFile = new File(this.localDownloadPath + fileToDownload.getFile().replaceFirst("/|\\\\", ""));

        HttpClient client = getHttpClient();
        HttpGet httpGet = new HttpGet(fileToDownload.toURI());
        HttpResponse response = client.execute(httpGet);
        FileUtils.copyInputStreamToFile(response.getEntity().getContent(), downloadedFile);
        response.getEntity().getContent().close();

        String downloadedFileAbsolutePath = downloadedFile.getAbsolutePath();
        LOG.info("File downloaded to '" + downloadedFileAbsolutePath + "'");

        return downloadedFileAbsolutePath;
    }

    private HttpClient getHttpClient()
    {
        RequestConfig config = RequestConfig.custom().setSocketTimeout(HTTP_CLIENT_TIMEOUT * 5000)
                .setConnectTimeout(HTTP_CLIENT_TIMEOUT * 5000)
                .setConnectionRequestTimeout(HTTP_CLIENT_TIMEOUT * 5000)
                .setStaleConnectionCheckEnabled(true)
                .build();

        SSLConnectionSocketFactory sslConnectionSocketFactory = null;
        try
        {
            sslConnectionSocketFactory = new SSLConnectionSocketFactory(SSLContext.getDefault(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        final RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
        registryBuilder.register("http", new PlainConnectionSocketFactory());
        registryBuilder.register("https", sslConnectionSocketFactory != null ? sslConnectionSocketFactory : null);

        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registryBuilder.build());
        connectionManager.setDefaultMaxPerRoute(10);
        return HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(config).setSSLSocketFactory(sslConnectionSocketFactory).build();
    }

    public String downloadFile(WebElement element) throws Exception
    {
        return downloader(element, "href");
    }

}