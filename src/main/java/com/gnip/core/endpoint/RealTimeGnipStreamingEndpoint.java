package com.gnip.core.endpoint;

public class RealTimeGnipStreamingEndpoint extends GnipStreamingEndpoint {

  public RealTimeGnipStreamingEndpoint(String account, String publisher, String product, String label) {
    super(account, publisher, product, label);
  }

  public RealTimeGnipStreamingEndpoint(String account, String publisher, String product, String label, int clientId) {
    super(account, publisher, product, label, clientId);
  }
}