FROM ubuntu:24.04

RUN sed -Ei "s@(security|ports|archive).ubuntu.com@mirrors.cloud.tencent.com@g" /etc/apt/sources.list.d/ubuntu.sources

RUN apt-get update && \
    apt-get install -y \
        git \
        wget \
        unzip \
        openssh-server \
        zsh \
        curl

ENV LANG=C.UTF-8
ENV LANGUAGE=C.UTF-8

RUN useradd -m -s /usr/bin/zsh caobaoqi6040

USER caobaoqi6040
WORKDIR /home/caobaoqi6040

RUN sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)" "" --unattended