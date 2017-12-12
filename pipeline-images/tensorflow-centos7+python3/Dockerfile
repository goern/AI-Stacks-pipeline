# mlcc -i Centos7,CPU,Python3,Pandas,Keras,scikit-learn,TensorFlow
# mlcc version: 20171025a: Nov  2 2017
# modified by goern

FROM centos:7

RUN yum -y install wget; cd /var/cache && rm -rf dnf yum

RUN yum -y --enablerepo=extras install epel-release; yum -y update; yum -y install cmake gcc gcc-c++ git make patch pciutils unzip vim-enhanced; cd /var/cache && rm -rf dnf yum



RUN yum -y install python3-pip || yum -y install python34-pip; yum -y install python3-devel || yum -y install python34-devel; cd /var/cache && rm -rf dnf yum; ln -s /usr/bin/pip3 /usr/bin/pip; pip install --upgrade pip;

RUN pip install numpy

RUN pip install scipy

RUN pip install pandas

RUN pip install cython

RUN pip install scikit-learn

RUN pip install tensorflow-gpu==1.3.0rc0

RUN pip install keras

