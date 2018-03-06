// Openshift project
OPENSHIFT_NAMESPACE = 'ai-coe'
OPENSHIFT_SERVICE_ACCOUNT = 'jenkins'
DOCKER_REPO_URL = 'docker-registry.default.svc.cluster.local:5000'
CI_NAMESPACE= 'aicoe'

// Defaults for SCM operations
env.ghprbGhRepository = env.ghprbGhRepository ?: 'goern/AI-Stacks-pipeline'
env.ghprbActualCommit = env.ghprbActualCommit ?: 'master'

// Fedora Fedmsg Message Provider for stage
MSG_PROVIDER = "fedora-fedmsg-devel"

// If this PR does not include an image change, then use this tag
STABLE_LABEL = "stable"
tagMap = [:]

// Initialize
tagMap['tensorflow-fedora27'] = '1.4.1'
tagMap['tensorflow-fedora27-test'] = '1.4.1'
tagMap['tensorflow-centos7-python3'] = STABLE_LABEL
tagMap['scikit-image-centos7-python3'] = STABLE_LABEL
tagMap['scikit-image-centos7-python2'] = STABLE_LABEL
tagMap['gcc630-centos7'] = STABLE_LABEL
tagMap['tensorflow-neural-style-s2i'] = STABLE_LABEL
tagMap['tensorflow-build-s2i'] = STABLE_LABEL
tagMap['tensorflow-serving-gpu-s2i'] = STABLE_LABEL
tagMap['jupyter-notebook-py35'] = STABLE_LABEL
tagMap['tf-base-notebook'] = STABLE_LABEL
tagMap['base-notebook'] = STABLE_LABEL

// IRC properties
IRC_NICK = "ai-coe-bot"
IRC_CHANNEL = "#thoth-station"

properties(
    [
        buildDiscarder(logRotator(artifactDaysToKeepStr: '30', artifactNumToKeepStr: '', daysToKeepStr: '90', numToKeepStr: '')),
        disableConcurrentBuilds(),
    ]
)


library(identifier: "cico-pipeline-library@master",
        retriever: modernSCM([$class: 'GitSCMSource',
                              remote: "https://github.com/CentOS/cico-pipeline-library",
                              traits: [[$class: 'jenkins.plugins.git.traits.BranchDiscoveryTrait'],
                                       [$class: 'RefSpecsSCMSourceTrait',
                                        templates: [[value: '+refs/heads/*:refs/remotes/@{remote}/*']]]]])
                            )
library(identifier: "ci-pipeline@master",
        retriever: modernSCM([$class: 'GitSCMSource',
                              remote: "https://github.com/CentOS-PaaS-SIG/ci-pipeline",
                              traits: [[$class: 'jenkins.plugins.git.traits.BranchDiscoveryTrait'],
                                       [$class: 'RefSpecsSCMSourceTrait',
                                        templates: [[value: '+refs/heads/*:refs/remotes/@{remote}/*']]]]])
                            )
library(identifier: "ai-stacks-pipeline@master",
        retriever: modernSCM([$class: 'GitSCMSource',
                              remote: "https://github.com/goern/AI-Stacks-pipeline",
                              traits: [[$class: 'jenkins.plugins.git.traits.BranchDiscoveryTrait'],
                                       [$class: 'RefSpecsSCMSourceTrait',
                                        templates: [[value: '+refs/heads/*:refs/remotes/@{remote}/*']]]]])
                            )

pipeline {
    agent {
        kubernetes {
            cloud 'openshift'
            label 'ai-stacks-pipeline-' + env.ghprbActualCommit
            serviceAccount OPENSHIFT_SERVICE_ACCOUNT
            containerTemplate {
                name 'jnlp'
                args '${computer.jnlpmac} ${computer.name}'
                image DOCKER_REPO_URL + '/'+ CI_NAMESPACE +'/jenkins-aiceo-slave:' + STABLE_LABEL
                ttyEnabled false
                command ''
            }
        }
    }
    stages {
        stage("Setup Build Templates") {
            steps {
                script {
                    aIStacksPipelineUtils.createBuildConfigs(OPENSHIFT_NAMESPACE)
                }
            }
        }
        stage("Get Changelog") {
            steps {
                node('master') {
                    script {
                        env.changeLogStr = pipelineUtils.getChangeLogFromCurrentBuild()
                        echo env.changeLogStr
                    }
                    writeFile file: 'changelog.txt', text: env.changeLogStr
                    archiveArtifacts allowEmptyArchive: true, artifacts: 'changelog.txt'
                }
            }
        }
        stage("Build Container Images") {
            if (env.BRANCH_NAME == 'master') {
                parallel {
                    stage("Tensorflow: Fedora27") {
                        steps { // FIXME we could have a conditional build here
                            echo "Building Tensorflow container image..."
                            script {
                                tagMap['tensorflow-fedora27'] = aIStacksPipelineUtils.buildImageWithTag(OPENSHIFT_NAMESPACE, "tensorflow-fedora27", '1.4.1')
                            }

                            echo "Building Tensorflow Test container image..."
                            script {
                                tagMap['tensorflow-fedora27-test'] = aIStacksPipelineUtils.buildImageWithTag(OPENSHIFT_NAMESPACE, "tensorflow-fedora27-test", '1.4.1')
                            }
                        }          
                    }
                    stage("Tensorflow: CentOS7+Python3") {
                        steps { // FIXME we could have a conditional build here
                            script {
                                tagMap['tensorflow-centos7-python3'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, "tensorflow-centos7-python3")
                            }
                        }                
                    }
                    stage("Scikit-Image: CentOS7+Python3") {
                        steps { // FIXME we could have a conditional build here
                            script {
                                tagMap['scikit-image-centos7-python3'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, "scikit-image-centos7-python3")
                            }
                        }                
                    }
                    stage("Scikit-Image: CentOS7+Python2") {
                        steps { // FIXME we could have a conditional build here
                            script {
                                tagMap['scikit-image-centos7-python2'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, "scikit-image-centos7-python2")
                            }
                        }                
                    }
                    stage("radanalytics: Tensorflow: Neural-Style S2I") {
                        steps { // FIXME we could have a conditional build here
                            script {
                                tagMap['tensorflow-neural-style-s2i'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, 'tensorflow-neural-style-s2i')
                            }
                        }                
                    }
                    stage("radanalytics: Tensorflow: S2I") {
                        steps { // FIXME we could have a conditional build here
                            script {
                                tagMap['tensorflow-build-s2i'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, 'tensorflow-build-s2i')
                            }
                        }                
                    }
                    stage("radanalytics: Tensorflow: Serving GPU S2I") {
                        steps { // FIXME we could have a conditional build here
                            script {
                                tagMap['tensorflow-serving-gpu-s2i'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, 'tensorflow-serving-gpu-s2i')
                            }
                        }                
                    }
                    stage("radanalytics: Jupyter: Python 3.5") {
                        steps { // FIXME we could have a conditional build here
                            script {
                                tagMap['jupyter-notebook-py35'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, 'jupyter-notebook-py35')
                            }
                        }                
                    }
                    stage("radanalytics: Jupyer Notebooks") {
                        steps { 
                            script {
                                tagMap['base-notebook'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, 'base-notebook')
                            }
                            script {
                                tagMap['tf-base-notebook'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, 'tf-base-notebook')
                            }
                        }                
                    }
                    stage("GCC 6.3.0: CentOS7") {
                        steps { // FIXME we could have a conditional build here
                            script {
                                tagMap['gcc630-centos7'] = pipelineUtils.buildStableImage(OPENSHIFT_NAMESPACE, "gcc630-centos7")
                            }
                        }                
                    } 
                }
            } else {
                echo "Not building container images on branch ${env.BRANCH_NAME}"
            }
        }
        stage("Run Tests") {
            failFast true
            parallel {
                stage("Functional Tests") {
                    steps {
                        echo "TODO"
                    }
                }
                stage("Performance Tests") {
                    steps {
                        echo "TODO"
                    }
                }
            }
        }
        stage("Image Tag Report") {
            steps {
                script {
                    pipelineUtils.printLabelMap(tagMap)
                }
            }
        }
    }
    post {
        always {
            script {
                String prMsg = ""
                if (env.ghprbActualCommit != null && env.ghprbActualCommit != "master") {
                    prMsg = "(PR #${env.ghprbPullId} ${env.ghprbPullAuthorLogin})"
                }
                def message = "${JOB_NAME} ${prMsg} build #${BUILD_NUMBER}: ${currentBuild.currentResult}: ${BUILD_URL}"

                mattermostSend channel: "#thoth-station", icon: 'https://avatars1.githubusercontent.com/u/33906690', message: "${message} ${env.BUILD_URL}"
                pipelineUtils.sendIRCNotification("${IRC_NICK}", IRC_CHANNEL, message)
            }
        }
        success {
            echo "All Systems GO!"
        }
        failure {
            error "BREAK BREAK BREAK - build failed!"
        }
    }
}