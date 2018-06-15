FROM openshift/jenkins-slave-base-centos7

MAINTAINER Christoph Görn <goern@redhat.com>

ENV APP_ROOT=/opt/app-root \
    HOME=/opt/app-root/src \
    PATH=$HOME/.local/bin/:/opt/app-root/src/bin:/opt/app-root/bin:$PATH

ENV PYTHON_VERSION=3.6 \
    PYTHONUNBUFFERED=1 \
    PYTHONIOENCODING=UTF-8 \
    LC_ALL=en_US.UTF-8 \
    LANG=en_US.UTF-8 \
    PIP_NO_CACHE_DIR=off \
    BASH_ENV=/usr/local/bin/scl_enable \
    ENV=/usr/local/bin/scl_enable \
    PROMPT_COMMAND=". /usr/local/bin/scl_enable"

COPY root/bin/scl_enable /usr/local/bin/scl_enable

RUN yum install -y centos-release-scl-rh && \
    INSTALL_PKGS="rh-python36 rh-python36-python-devel rh-python36-python-setuptools rh-python36-python-pip nss_wrapper libffi-devel libtool-ltdl enchant" && \
    yum install -y centos-release-scl && \
    yum -y --setopt=tsflags=nodocs install --enablerepo=centosplus $INSTALL_PKGS && \
    rpm -V $INSTALL_PKGS && \
    yum -y clean all --enablerepo='*'

WORKDIR /opt/app-root/src

RUN source scl_source enable rh-python36 && \
    virtualenv ${APP_ROOT} && \
    pip install --upgrade pip && \
    pip install --user pipenv && \
    chown -R 1001:0 $HOME && \
    chmod -R g+rw $HOME

USER 1001
