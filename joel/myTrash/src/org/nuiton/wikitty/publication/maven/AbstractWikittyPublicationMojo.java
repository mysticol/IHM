package org.nuiton.wikitty.publication.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;


/**
 * Base Mojo 
 * @author mfortun
 *
 */
public abstract class AbstractWikittyPublicationMojo extends AbstractMojo {

    /**
     * Flag to activate verbose mode.
     * <p/>
     * <b>Note:</b> Verbose mode is always on if you starts a debug maven
     * instance (says via {@code -X}).
     * 
     * @parameter expression="${license.verbose}"
     *            default-value="${maven.verbose}"
     */
    private boolean verbose;

    protected abstract void init() throws Exception;

    protected abstract void doAction() throws Exception;

    public final void execute() throws MojoExecutionException,
            MojoFailureException {
        try {
            if (getLog().isDebugEnabled()) {

                // always be verbose in debug mode
                setVerbose(true);
            }

            // check if project packaging is compatible with the mojo

            boolean canContinue = checkPackaging();
            if (!canContinue) {
                getLog().warn(
                        "The goal is skip due to packaging '" );
                                //+ getProject().getPackaging() + "'");
                return;
            }

            // init the mojo

            try {

                init();

            } catch (MojoFailureException e) {
                throw e;
            } catch (MojoExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new MojoExecutionException("could not init goal "
                        + getClass().getSimpleName() + " for reason : "
                        + e.getMessage(), e);
            }

            // check if mojo can be skipped

            canContinue = checkSkip();
            if (!canContinue) {
                if (isVerbose()) {
                    getLog().info("Goal will not be executed.");
                }
                return;
            }

            // can really execute the mojo

            try {

                doAction();

            } catch (MojoFailureException e) {
                throw e;
            } catch (MojoExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new MojoExecutionException("could not execute goal "
                        + getClass().getSimpleName() + " for reason : "
                        + e.getMessage(), e);
            }
        } finally {
            afterExecute();
        }
    }

    /**
     * A call back to execute after the {@link #execute()} is done
     */
    protected void afterExecute() {
        // by default do nothing
    }

    protected boolean checkPackaging() {
        // by default, accept every type of packaging
        return true;
    }

    /**
     * Checks if the mojo execution should be skipped.
     * 
     * @return {@code false} if the mojo should not be executed.
     */
    protected boolean checkSkip() {
        // by default, never skip goal
        return true;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

}
