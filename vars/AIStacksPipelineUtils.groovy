import com.redhat.aicoe.pipeline.AIStacksPipelineUtils
import org.centos.Utils

/**
 * A class of methods used in the AI-Stacks Jenkinsfile pipeline.
 * These methods are wrappers around methods in the ai-stacks-pipeline library.
 */
class AIStacksPipelineUtils implements Serializable {

    def AIStacksPipelineUtils = new AIStacksPipelineUtils()
    def utils = new Utils()

    /**
     * create BuildConfigs from templates
     * @param openshiftProject Openshift Project
     * @return
     */
    def createBuildConfigs(String openshiftProject) {
        return AIStacksPipelineUtils.createBuildConfigs(openshiftProject)
    }
}