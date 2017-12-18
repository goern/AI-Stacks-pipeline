# mlcc -i Centos7,CPU,Python3,scikit-image
# mlcc version: 20171025a: Nov  2 2017
# modified by goern

FROM centos:7

RUN yum -y install wget && \
    yum -y --enablerepo=extras install epel-release && \
    yum -y update && \
    yum -y install cmake gcc gcc-c++ git make patch pciutils unzip vim-enhanced && \
    yum -y install python3-pip || yum -y install python34-pip && \
    yum -y install python3-devel || yum -y install python34-devel && \
    cd /var/cache && rm -rf dnf yum && \
    ln -s /usr/bin/pip3 /usr/bin/pip && \
    pip install --upgrade pip

RUN pip install numpy scipy cython scikit-image

