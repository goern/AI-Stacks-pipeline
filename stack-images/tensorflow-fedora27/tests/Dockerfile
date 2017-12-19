FROM 172.30.254.79:5000/ai-coe/tensorflow-fedora27:1.4.1

WORKDIR /opt/ai-stacks/tensorflow/tests/
COPY . /opt/ai-stacks/tensorflow/tests/

RUN pip install pytest

CMD pytest