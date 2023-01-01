const proxyObj = {}
const CompressionPlugin = require('compression-webpack-plugin')

proxyObj['/ws'] = {
  ws: true,
  target: 'ws://localhost:6666'
}
proxyObj['/'] = {
    ws: false,
    target: 'http://localhost:6666',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
}
module.exports = {
  devServer: {
    host: 'localhost',
    port: 8080,
    proxy: proxyObj
  },
  configureWebpack: () => {
    if (process.env.NODE_ENV === 'production') {
      return {
        plugins: [
          // Webpack压缩设置
          new CompressionPlugin({
            test: /\.js$|\.html$|\.css/,
            threshold: 1024,
            deleteOriginalAssets: false
          })
        ],
        performance: {
          hints: 'warning', // 枚举
          maxAssetSize: 200000, // 整数类型（以字节为单位）
          maxEntrypointSize: 400000, // 整数类型（以字节为单位）
            assetFilter: function (assetFilename) {
                // 提供资源文件名的断言函数
                return assetFilename.endsWith('.css') || assetFilename.endsWith('.js')
            }
        }
      }
    }
  },
    lintOnSave: true,
    publicPath: '/',
    assetsDir: './static'
    // outputDir: '../tclassserver/tclass-gateway/src/main/resources/static'
}
