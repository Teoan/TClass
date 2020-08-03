let proxyObj = {};
const CompressionPlugin = require("compression-webpack-plugin");

proxyObj["/ws"] = {
  ws: true,
  target: "ws://localhost:8081"
};
proxyObj["/"] = {
  ws: false,
  target: "http://localhost:8081",
  changeOrigin: true,
  pathRewrite: {
    "^/": ""
  }
};
module.exports = {
  devServer: {
    host: "localhost",
    port: 8080
    // proxy:proxyObj
  },
  configureWebpack: () => {
    if (process.env.NODE_ENV === "production") {
      return {
        plugins: [
          //Webpack压缩设置
          new CompressionPlugin({
            test: /\.js$|\.html$|\.css/,
            threshold: 1024,
            deleteOriginalAssets: false
          })
        ]
      };
    }
  },
  lintOnSave: true,
  publicPath: "./",
  assetsDir: "./static"
};
