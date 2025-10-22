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
cp ./xxx.service /etc/systemd/system/xxx.service

systemctl daemon-reload
sudo systemctl start | stop | status | restart
```
