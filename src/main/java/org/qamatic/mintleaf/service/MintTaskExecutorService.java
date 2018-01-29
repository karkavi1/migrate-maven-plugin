package org.qamatic.mintleaf.service;

import org.apache.commons.lang3.StringUtils;
import org.qamatic.mintleaf.*;
import org.qamatic.mintleaf.cli.MigrationTask;
import org.qamatic.mintleaf.configuration.MintleafXmlConfiguration;
import org.qamatic.mintleaf.configuration.SchemaVersionInfo;

import java.io.File;

public class MintTaskExecutorService {

    private String configuration;

    private String schemaVersion;

    private String baseDirectory;

    private String targetDatabase;

    private MintleafConfiguration mintleafConfiguration;

    public MintTaskExecutorService(String mintXMLConfiguration, String version, String configPath, String targetDB) {
        this.configuration = mintXMLConfiguration;
        this.schemaVersion = version;
        this.baseDirectory = configPath;
        this.targetDatabase = targetDB;
    }


    /**
     * Method executes Mint Leaf Task
     *
     * @throws MintleafException
     */
    public void taskExecution() throws MintleafException {

        MintLeafValidator validator = new MintLeafValidator();

        /*
         * Validates user supplied properties and return "true" validation passes.
         */
        if(validator.validate()) {

            SchemaVersionInfo versionInfo = this.getSchemaVersionInfo();

            try (ConnectionContext connectionContext = this.getDatabase().getNewConnection()) {
                MintleafCliTask task = new MigrationTask(connectionContext, versionInfo, null);
                task.execute();
            }
        }
    }

    /**
     * Method establish DB Connection and return DB Instance
     *
     * @return database {@link Database}
     */
    private Database getDatabase() {
        return this.mintleafConfiguration.getDbConnectionInfo(this.targetDatabase).getNewDatabaseInstance();
    }

    /**
     * This method decorates SchemaVersionInfo
     *
     * @return schemaVersionInfo {@link SchemaVersionInfo}
     * @throws MintleafException
     */
    private SchemaVersionInfo getSchemaVersionInfo() throws MintleafException {

        mintleafConfiguration = MintleafXmlConfiguration.deSerialize(this.configuration);

        SchemaVersionInfo schemaVersionInfo = mintleafConfiguration.getSchemaVersionInfo(this.schemaVersion);

        return new SchemaVersionInfo(schemaVersionInfo.getId(),
                schemaVersionInfo.getChangeSets(),
                new StringBuilder(this.baseDirectory).append(schemaVersionInfo.getScriptLocation()).toString());
    }

    /**
     * Mint Leaf Validator Class
     */
    private class MintLeafValidator {

        public boolean validate() throws MintleafException {

            if(StringUtils.isBlank(MintTaskExecutorService.this.targetDatabase)){
                throw new MintleafException("Target Database should not be empty / null");
            }

            if(StringUtils.isBlank(MintTaskExecutorService.this.schemaVersion)) {
                throw new MintleafException("Schema Version should not be empty / null");
            }

            if(StringUtils.isBlank(MintTaskExecutorService.this.configuration)) {
                throw new MintleafException("MintLeaf configuration should not be empty / null");
            } else {
                File mintConfigFile = new File(MintTaskExecutorService.this.configuration);
                if (!mintConfigFile.exists() || !mintConfigFile.isFile()){
                    throw new MintleafException("MintLeaf configuration does not exists in "
                            + MintTaskExecutorService.this.configuration);
                }
            }

            return true;
        }
    }
}