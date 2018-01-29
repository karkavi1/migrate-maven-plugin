package org.qamatic.mintleaf.object.serializer;

import java.io.Serializable;

public class Database implements Serializable {

    private String id;
    private String type;
    private String url;

    public Database(String dbId, String dbType, String dbServerUrl) {
        this.id = dbId;
        this.type = dbType;
        this.url = dbServerUrl;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
