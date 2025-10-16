# 🤝 贡献指南

感谢您有兴趣为这个项目做贡献！在提交您的贡献之前，请阅读以下指南以确保流程顺利进行。

## 🔧 环境设置

### 前端设置 (pnpm)

本项目文档使用 [pnpm](https://pnpm.io/) 进行包管理。请确保在继续之前已安装 pnpm。

1. **Fork** 本仓库并从 `main` 分支创建您的分支。
2. 在项目根目录运行 `pnpm install` 安装依赖。
3. 运行 `pnpm run docs:dev` 启动开发服务器。
4. 如果出现提示，点击"安装"来安装必要的开发脚本。
5. 进行修改并在本地测试您的更改。

### 后端设置 (Maven)

项目后端使用 [Maven](https://maven.apache.org/) 进行包管理。请确保在继续之前已安装 Maven。

1. **Fork** 本仓库并从 `main` 分支创建您的分支。
2. 在项目根目录运行 `mvn compile` 编译项目。
3. 现在您可以开始进行修改了！

### 后端设置 (Gradle)

或者，如果您使用 [Gradle](https://gradle.org/) 进行后端设置，请按照以下步骤操作：

1. **Fork** 本仓库并从 `main` 分支创建您的分支。
2. 在项目根目录运行 `./gradlew build` 构建项目。
3. 运行 `./gradlew run` 在本地启动后端服务器。
4. 一切就绪！

## ✅ 代码检查和类型检查

本项目使用 [ESLint](https://eslint.org/) 进行代码检查，使用 [TypeScript](https://www.typescriptlang.org/) 进行类型检查。在提交拉取请求之前，请确保您的代码通过这两项检查。

运行代码检查和类型检查：

```bash
yarn run lint
yarn run test
```

## 📝 提交信息格式

我们遵循 [约定式提交](https://www.conventionalcommits.org/) 规范来编写提交信息。这有助于保持项目的一致性和清晰度。

**请确保在提交信息或拉取请求(PR)描述中提及相关的问题编号。**

### 提交信息示例：
```
fix: 修复API响应问题 (修复 #123)
```

遵循这些指南有助于维护项目质量并促进协作。
