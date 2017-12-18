# mlcc -i Centos7,CPU,Python2,scikit-image
# mlcc version: 20171025a: Nov  2 2017
# modified by goern

FROM centos:7

RUN yum -y install wget && \
    yum -y --enablerepo=extras install epel-release && \
    yum -y update && \
    yum -y install cmake gcc gcc-c++ git make patch pciutils unzip vim-enhanced && \
    yum -y install python2-pip || yum -y install python-pip && \
    yum -y install python2-devel || yum -y install python-devel && \
    cd /var/cache && rm -rf dnf yum && \
    pip install --upgrade pip

RUN pip install numpy scipy cython scikit-image

