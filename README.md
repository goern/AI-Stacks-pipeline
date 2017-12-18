# AI-Stacks-pipeline
This is a CentOS CI based pipeline to product optimized AI Stacks

```bash
oc login ...
oc create -f ai-stacks-pipeline.yaml
cd pipeline-images/ ; for d in `ls -1` ; do oc process -f ${d}/buildConfigAndImageStream.yaml | oc create -f - ; done ; cd ..
```