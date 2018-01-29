package org.qamatic.mintleaf;

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.qamatic.mintleaf.service.MintTaskExecutorService;

import java.util.List;


@Mojo(name = "migrate",
        defaultPhase = LifecyclePhase.PACKAGE,
        instantiationStrategy = InstantiationStrategy.PER_LOOKUP,
        threadSafe = true,
        requiresReports = true,
        requiresDependencyResolution = ResolutionScope.TEST)
public class Migrate extends AbstractMojo {

    @Parameter( defaultValue = "${project}", readonly = true )
    private MavenProject mavenProject;


    @Parameter( defaultValue = "${project.resources}", readonly = true)
    private List<Resource> resources;

    /*
     * Database Id of The target database
     */
    @Parameter( property = "mintleaf.targetdb", defaultValue = "h2testdb",
            required = true)
    protected String targetDatabase;

    /*
     * Schema Version needs to be migrated
     */
    @Parameter( property = "mintleaf.schemaversion", defaultValue = "1.0",
            required = true)
    protected String schemaVersion;

    /*
     * Configuration
     */
    @Parameter( property = "mintleaf.config", defaultValue = "default-h2-config.xml",
            required = true)
    protected String mintLeafConfig;

    @Parameter( property = "mintleaf.basedir", defaultValue = "${basedir}/target/classes/mintleaf/")
    private String baseDirectory;

    public void execute() throws MojoExecutionException, MojoFailureException {

        try {

            getLog().
                    info("Mint leaf migrate goal is executing.... "
                            + this.mavenProject.getBasedir().getAbsolutePath().toString());

            MintTaskExecutorService mintTaskExecutorService = new
                    MintTaskExecutorService(new StringBuffer(this.baseDirectory).append("config/").append(this.mintLeafConfig).toString(),
                    this.schemaVersion,
                    this.baseDirectory,
                    this.targetDatabase);

            mintTaskExecutorService.taskExecution();


        } catch (MintleafException mle) {
            throw new MojoFailureException("migrate-maven-plugin Mojo failure exception" +  mle.getMessage());
        }
    }
}