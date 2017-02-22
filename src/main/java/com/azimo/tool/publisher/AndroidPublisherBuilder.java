package com.azimo.tool.publisher;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.PrivateKey;
import java.util.Collections;

/**
 * Created by F1sherKK on 18/01/17.
 */
public class AndroidPublisherBuilder {

    public HttpTransport newTrustedTransport() throws Exception {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    public JsonFactory newJacksonFactory() throws Exception {
        return JacksonFactory.getDefaultInstance();
    }

    public Credential authorizeWithServiceAccount(String serviceAccountEmail,
                                                  String credentialsPath,
                                                  HttpTransport httpTransport,
                                                  JsonFactory jsonFactory) throws Exception {
        return new GoogleCredential.Builder()
            .setTransport(httpTransport)
            .setJsonFactory(jsonFactory)
            .setServiceAccountId(serviceAccountEmail)
            .setServiceAccountScopes(Collections.singleton(AndroidPublisherScopes.ANDROIDPUBLISHER))
            .setServiceAccountPrivateKeyFromP12File(new File(credentialsPath))
            .build();
    }

    public Credential authorizeWithServiceAccount(String credentialsJsonPath,
                                                  HttpTransport httpTransport,
                                                  JsonFactory jsonFactory) throws Exception {
        return GoogleCredential.fromStream(
                new FileInputStream(new File(credentialsJsonPath)),
                httpTransport,
                jsonFactory
        ).createScoped(Collections.singleton(AndroidPublisherScopes.ANDROIDPUBLISHER));
    }


    public AndroidPublisher build(HttpTransport transport,
                                  JsonFactory jsonFactory,
                                  Credential credential,
                                  String applicationName) {
        return new AndroidPublisher.Builder(
            transport, jsonFactory, credential).setApplicationName(applicationName)
            .build();
    }
}
