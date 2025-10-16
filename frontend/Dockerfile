# =========================================
# stage 1: build the application
# https://docs.docker.com/guides/vuejs/containerize/#step-2-configure-the-dockerfile
# https://pnpm.io/docker
# =========================================
ARG NODE_VERSION=23.11.1-alpine
ARG PNPM_VERSION=10.15.0
ARG NGINX_VERSION=alpine3.21

FROM node:${NODE_VERSION} AS builder
WORKDIR /app

RUN corepack enable && corepack prepare pnpm@${PNPM_VERSION} --activate

COPY pnpm-lock.yaml ./

RUN --mount=type=cache,id=pnpm-store,target=/root/.local/share/pnpm/store \
    pnpm fetch

COPY . .

ENV CI=true
ENV VITE_API_BASE_URL=/api

RUN --mount=type=cache,id=pnpm-store,target=/root/.local/share/pnpm/store \
    pnpm install --offline

ENV PATH="/app/node_modules/.bin:${PATH}"

RUN pnpm run build-only

# =========================================
# stage 2: nginx
# =========================================
FROM nginxinc/nginx-unprivileged:${NGINX_VERSION} AS runner

USER nginx
COPY nginx.conf /etc/nginx/nginx.conf
COPY --chown=nginx:nginx --from=builder /app/dist /usr/share/nginx/html

EXPOSE 80
ENTRYPOINT ["nginx", "-c", "/etc/nginx/nginx.conf"]
CMD ["-g", "daemon off;"]
