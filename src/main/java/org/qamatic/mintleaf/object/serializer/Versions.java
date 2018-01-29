package org.qamatic.mintleaf.object.serializer;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

public class Versions implements Serializable {

    private String id;

    @XStreamImplicit
    private List<String> changeSets;

    private String scriptLocation;

    public Versions(String versionId, List<String> changeSets, String location) {
        this.id = versionId;
        this.changeSets = changeSets;
        this.scriptLocation = location;
    }

    public String getId() {
        return id;
    }

    public List<String> getChangeSets() {
        return changeSets;
    }

    public String getScriptLocation() {
        return scriptLocation;
    }
}
