package com.gnip.core.endpoint;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.twitter.hbc.core.HttpConstants;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public abstract class GnipStreamingEndpoint implements StreamingEndpoint {
  private static final String BASE_PATH = "/accounts/%s/publishers/%s/streams/%s/%s.json";
  protected final String account;
  protected final String publisher;
  protected final String product;
  protected final String label;
  protected final ConcurrentMap<String, String> queryParameters = Maps.newConcurrentMap();

  public GnipStreamingEndpoint(String account, String publisher, String product, String label) {
    this(account, publisher, product, label, 0);
  }

  public GnipStreamingEndpoint(String account, String publisher, String product, String label, int clientId) {
    this.account = Preconditions.checkNotNull(account);
    this.publisher = Preconditions.checkNotNull(publisher);
    this.product = Preconditions.checkNotNull(product);
    this.label = Preconditions.checkNotNull(label);

    if (clientId > 0) {
      addQueryParameter("client", String.valueOf(clientId));
    }
  }

  @Override
  public String getURI() {
    String uri = String.format(BASE_PATH, account.trim(), publisher.trim(), product.trim(), label.trim());

    if (queryParameters.isEmpty()) {
      return uri;
    } else {
      return uri + "?" + generateParamString(queryParameters);
    }
  }

  protected String generateParamString(Map<String, String> params) {
    return Joiner.on("&")
            .withKeyValueSeparator("=")
            .join(params);
  }

  @Override
  public String getHttpMethod() {
    return HttpConstants.HTTP_GET;
  }

  @Override
  public String getPostParamString() {
    return null;
  }

  @Override
  public String getQueryParamString() {
    return generateParamString(queryParameters);
  }

  @Override
  public void addQueryParameter(String param, String value) {
    queryParameters.put(param, value);
  }

  @Override
  public void removeQueryParameter(String param) {
    queryParameters.remove(param);
  }

  // These don't do anything
  @Override
  public void setBackfillCount(int count) { }

  @Override
  public void setApiVersion(String apiVersion) { }

  @Override
  public void addPostParameter(String param, String value) { }

  @Override
  public void removePostParameter(String param) { }

}