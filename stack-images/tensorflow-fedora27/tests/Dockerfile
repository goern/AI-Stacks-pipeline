FROM ai-coe/tensorflow:1.4.1

WORKDIR /opt/ai-stacks/tensorflow/tests/
COPY . /opt/ai-stacks/tensorflow/tests/

RUN pip install pytest

CMD pytest