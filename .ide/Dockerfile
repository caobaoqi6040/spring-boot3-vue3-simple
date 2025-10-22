FROM ubuntu:24.04

RUN sed -Ei "s@(security|ports|archive).ubuntu.com@mirrors.cloud.tencent.com@g" /etc/apt/sources.list.d/ubuntu.sources && \
    apt-get update && \
    apt-get install -y git && \
    rm -rf /var/lib/apt/lists/*

RUN apt-get update && apt-get install -y wget unzip openssh-server git

ENV LANG C.UTF-8
ENV LANGUAGE C.UTF-8