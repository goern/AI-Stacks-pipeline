# AI-Stacks-pipeline
This is a CentOS CI based pipeline to product optimized AI Stacks

## Creating the pipeline

```bash
oc login ...
oc process -f pipeline-images/jenkins/jenkins-persistent-buildconfig-template.yaml | oc create -f -
oc process -f pipeline-images/jenkins/jenkins-ai-coe-slave-buildconfig-template.yaml | oc create -f -
oc create -f ai-stacks-pipeline.yaml
```

## Create the AI-Stacks BuildConfigs

```bash
cd stack-images/ ; for d in `ls -1` ; do oc process -f ${d}/buildConfigAndImageStream.yaml | oc create -f - ; done ; cd ..
```

FIXME radanalytics will fail with this for-loop!

# Operations

## Cleanup and Garbage Collection

`oc adm prune builds --confirm --namespace ai-coe`

# Known Issues

* location of image in tensorflow test Dockerfile
* version used for tensorflow is in Jenkinsfile and BC template as a parameter