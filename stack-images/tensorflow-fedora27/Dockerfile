# mlcc -i Fedora27,CPU,Python3,Pandas,Keras,scikit-learn,TensorFlow
# mlcc version: 20171025a: Nov  2 2017
# modified by goern

FROM fedora:27

RUN yum -y install wget; cd /var/cache && rm -rf dnf yum

RUN yum -y update; yum -y install cmake gcc gcc-c++ git make patch pciutils unzip vim-enhanced; cd /var/cache && rm -rf dnf yum

RUN yum -y install python3-pip || yum -y install python34-pip; yum -y install python3-devel || yum -y install python34-devel; cd /var/cache && rm -rf dnf yum; ln -s /usr/bin/pip3 /usr/bin/pip; pip install --upgrade pip;

RUN pip install numpy==1.13.3 scipy==1.0.0 pandas==0.21.1 cython==0.27.3 scikit-learn==0.19.1 tensorflow==1.4.1 keras==2.1.2

