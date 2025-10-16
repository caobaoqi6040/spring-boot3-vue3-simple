LOCAL_IMAGE_NAME ?= caobaoqi6040/spring-boot3-vue3-simple-frontend
REMOTE_IMAGE_NAME ?= docker-registry.xing-lian-td.cn/spring-boot3-vue3-simple-frontend
TAG ?= latest

.PHONY: build
build:
	docker build -f Dockerfile -t $(LOCAL_IMAGE_NAME):$(TAG) .

.PHONY: tag-dev
tag-dev:
	docker tag $(LOCAL_IMAGE_NAME):latest $(LOCAL_IMAGE_NAME):dev

.PHONY: tag-release
tag-release:
	docker tag $(LOCAL_IMAGE_NAME):latest $(REMOTE_IMAGE_NAME):$(TAG)

.PHONY: push-release
push-release:
	docker push $(REMOTE_IMAGE_NAME):$(TAG)
