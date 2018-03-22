import com.redhat.aicoe.pipeline.AIStacksPipelineUtils

/**
 * A class of methods used in the AI-Stacks Jenkinsfile pipeline.
 * These methods are wrappers around methods in the ai-stacks-pipeline library.
 */
class aIStacksPipelineUtils implements Serializable {

    def aIStacksPipelineUtils = new AIStacksPipelineUtils()

    /**
     * TODO create BuildConfigs from templates
     * @param openshiftProject Openshift Project
     * @return
     */
    def createBuildConfigs(String openshiftProject) {
        return aIStacksPipelineUtils.createBuildConfigs(openshiftProject)
    }

    /**
    * Build container image in OpenShift and tag it
    * @param openshiftProject OpenShift Project
    * @param buildConfig
    * @param tag
    * @return
    */
    def buildImageWithTag(String openshiftProject, String buildConfig, String tag) {
        return aIStacksPipelineUtils.buildImageWithTag(openshiftProject, buildConfig, tag)
    }

   /**
    * send buildlog to remote service endpoint
    * @param buildName name of the Build
    * @return
    */
    def sendBuildlogToURL(String buildName) {
        return aIStacksPipelineUtils.sendBuildlogToURL(buildName)
    }

    /**
     * Redeploy to a specific ImageStreamTag
     * @param projectName 
     * @param buildConfig
     * @param imageStreamTag
     * @return
     */
    def redeployFromImageStreamTag(String projectName, String buildConfig, String imageStreamTag) {
        return aIStacksPipelineUtils.redeployFromImageStreamTag(projectName, buildConfig, imageStreamTag)
    }
}