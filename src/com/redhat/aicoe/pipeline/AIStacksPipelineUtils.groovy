#!/usr/bin/groovy
package com.redhat.aicoe.pipeline

/**
 * create BuildConfigs from templates
 * @param openshiftProject Openshift Project
 * @return
 */
def createBuildConfigs(String openshiftProject) { // TODO can we set a default value here?
    openshift.withCluster() {
        openshift.withProject(openshiftProject) {
            script {
                echo "TODO create all BuildConfigs from templates in pipeline-images/*/buildConfig.yaml"
            }
        }
    }
}
