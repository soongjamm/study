package com.soongjamm.startboot.godOfJava.webserver;

public class RequestDTO {
    private String requestMethod="GET";
    private String uri="/";
    private String httpVersion="HTTP/1.1";

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    @Override
    public String toString() {
        return requestMethod+ " " + uri + " " + httpVersion;
    }
}
