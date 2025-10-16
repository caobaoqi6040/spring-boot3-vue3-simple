## backend

```shell
cd backend
make build
make tag-release TAG=0.0.1
```

## frontend

```shell
cd frontend
make build
make tag-release TAG=0.0.1
```

## deploy

```shell
docker compose -f ../docker-compose.yaml up -d
```

```shell
# wget https://github.com/fatedier/frp/releases/download/v0.64.0/frp_0.64.0_linux_amd64.tar.gz
# frpc -c frpc.toml
cp ./spring-boot3-vue3-simple-frpc.service /etc/systemd/system/spring-boot3-vue3-simple-frpc.service

sudo systemctl start spring-boot3-vue3-simple-frpc
sudo systemctl stop spring-boot3-vue3-simple-frpc
sudo systemctl status spring-boot3-vue3-simple-frpc
sudo systemctl restart spring-boot3-vue3-simple-frpc
```
