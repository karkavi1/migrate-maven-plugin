package org.qamatic.mintleaf.object.serializer;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.Serializable;
import java.util.List;

@XStreamAlias("mintleaf")
public class MintLeafNode implements Serializable {


    @XStreamAsAttribute
    @Parameter(defaultValue = "1.0", required = true)
    private String version;

    @Parameter(defaultValue = "MintLeafNode configuration file", required = true)
    private String description;


    @XStreamAlias("database ")
    public Database mintNodeDatabase;

    @XStreamAlias("schemaVersions")
    @XStreamImplicit
    public List<SchemaVersions> mintNodeSchemaVersions;


    public MintLeafNode(String version, String description, Database database, List<SchemaVersions> schemaVersions){
        this.version = version;
        this.description = description;
        this.mintNodeDatabase = database;
        this.mintNodeSchemaVersions = schemaVersions;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public Database getMintNodeDatabase() {
        return mintNodeDatabase;
    }

    public List<SchemaVersions> getMintNodeSchemaVersions() {
        return mintNodeSchemaVersions;
    }
}
