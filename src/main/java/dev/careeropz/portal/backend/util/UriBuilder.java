package dev.careeropz.portal.backend.util;

import lombok.Builder;

@Builder
public class UriBuilder {
    String host;
    Integer port;
    String urlPathItems;

    public String buildHttpUrl(){
        StringBuilder uriStringBuilder = new StringBuilder();
        uriStringBuilder.append("http://");
        uriStringBuilder.append(host);
        uriStringBuilder.append(":");
        uriStringBuilder.append(port);
        if(urlPathItems != null && !urlPathItems.isBlank()){
            uriStringBuilder.append(urlPathItems);
        }
        return uriStringBuilder.toString();
    }
}
