import com.redhat.aicoe.pipeline.AIStacksPipelineUtils

/**
 * A class of methods used in the AI-Stacks Jenkinsfile pipeline.
 * These methods are wrappers around methods in the ai-stacks-pipeline library.
 */
class aIStacksPipelineUtils implements Serializable {

    def aIStacksPipelineUtils = new AIStacksPipelineUtils()

    /**
     * create BuildConfigs from templates
     * @param openshiftProject Openshift Project
     * @return
     */
    def createBuildConfigs(String openshiftProject) {
        return aIStacksPipelineUtils.createBuildConfigs(openshiftProject)
    }
}