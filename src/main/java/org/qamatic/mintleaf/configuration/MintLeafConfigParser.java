package org.qamatic.mintleaf.configuration;


import org.apache.commons.lang3.StringUtils;
import org.qamatic.mintleaf.object.serializer.MintLeafNode;

public class MintLeafConfigParser implements ConfigActions <MintLeafNode> {


    private MintLeafNode rootNode;

    private String filePath;

    public MintLeafConfigParser(MintLeafNode node, String configPath){
        this.rootNode = node;
        this.filePath = configPath;
    }

    private MintLeafNode fromXML() {

        if(StringUtils.isNotBlank(this.filePath)){

        }
        return null;
    }

    @Override
    public MintLeafNode convert() {
        return null;
    }
}
