package com.azimo.tool.jira.interceptor;

import okhttp3.Request;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import helper.MockHelper;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by F1sherKK on 18/01/17.
 */
public class JiraContentTypeInterceptorTest {

    JiraContentTypeInterceptor contentTypeInterceptor;
    Request requestMock;

    @Mock
    okhttp3.Interceptor.Chain chainMock;
    @Captor
    ArgumentCaptor<Request> requestCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        requestMock = MockHelper.getRequest();
        contentTypeInterceptor = new JiraContentTypeInterceptor();
    }

    @Test
    public void whenAppliedInterceptor_ShouldAddJiraContentTypeHeader() throws IOException {
        when(chainMock.request()).thenReturn(requestMock);

        contentTypeInterceptor.intercept(chainMock);

        verify(chainMock).proceed(requestCaptor.capture());

        assertEquals(requestCaptor.getValue().header(JiraContentTypeInterceptor.CONTENT_TYPE_HEADER), JiraContentTypeInterceptor.CONTENT_TYPE_VALUE);
    }
}
