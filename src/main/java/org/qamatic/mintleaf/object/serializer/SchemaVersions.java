package org.qamatic.mintleaf.object.serializer;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.List;

public class SchemaVersions implements Serializable {

    @XStreamAlias("version")
    @XStreamImplicit
    private List<Versions> versions;

    public SchemaVersions(List<Versions> versions){
        this.versions = versions;
    }

    public List<Versions> getVersions() {
        return versions;
    }
}
