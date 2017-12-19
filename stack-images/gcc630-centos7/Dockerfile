
# mlcc -i Centos7,CPU,GCC
# mlcc version: 20171025a: Nov  2 2017
# modified by goern

FROM centos:7

RUN yum -y install wget && \
    yum -y --enablerepo=extras install epel-release && \
    yum -y update && \
    yum -y install cmake gcc gcc-c++ git make patch pciutils unzip vim-enhanced bzip2 && \
    cd /var/cache && rm -rf dnf yum

RUN mkdir -p /tmp/gcc_tmp_build_dir && \
    cd /tmp/gcc_tmp_build_dir && \
    wget "https://ftp.gnu.org/gnu/gcc/gcc-6.3.0/gcc-6.3.0.tar.bz2" && \
    wget "https://gcc.gnu.org/pub/gcc/infrastructure/gmp-6.1.0.tar.bz2" && \
    wget "https://gcc.gnu.org/pub/gcc/infrastructure/mpfr-3.1.4.tar.bz2" && \
    wget "https://gcc.gnu.org/pub/gcc/infrastructure/mpc-1.0.3.tar.gz" && \
    wget "https://gcc.gnu.org/pub/gcc/infrastructure/isl-0.18.tar.bz2" && \
    tar xvf gcc-6.3.0.tar.bz2 && \
    tar xvf gmp-6.1.0.tar.bz2 && \
    tar xvf mpfr-3.1.4.tar.bz2 && \
    tar xvf mpc-1.0.3.tar.gz && \
    tar xvf isl-0.18.tar.bz2 && \
    ln -s /tmp/gcc_tmp_build_dir/gmp-6.1.0 gcc-6.3.0/gmp && \
    ln -s /tmp/gcc_tmp_build_dir/mpfr-3.1.4 gcc-6.3.0/mpfr && \
    ln -s /tmp/gcc_tmp_build_dir/mpc-1.0.3 gcc-6.3.0/mpc && \
    ln -s /tmp/gcc_tmp_build_dir/isl-0.18 gcc-6.3.0/isl && \
    gcc-6.3.0/configure --disable-multilib --enable-languages=c,c++ --prefix=/usr/local/cuda && \
    make -j`getconf _NPROCESSORS_ONLN` && \
    make install-strip && \
    /bin/rm -r /tmp/gcc_tmp_build_dir

