/**
 * Created by wjl48511 on 2019/7/19.
 */
const express = require('express');
const webpack = require('webpack');
const webpackDevMiddleware = require('webpack-dev-middleware');
const webpackHotMiddleware = require('webpack-hot-middleware');
// webpack-dev-server不同，因为这里使用的两个模块都没有服务器的功能，所以我们只能用express来开启服务
const app = express();
const config = require('./webpack.config.js');
const compiler = webpack(config);

// Tell express to use the webpack-dev-middleware and use the webpack.config.js
// configuration file as a base.

//必须添加
Object.keys(config.entry).forEach(function(key){
    config.entry[key].unshift('webpack-hot-middleware/client?reload=true');
});

app.use(webpackDevMiddleware(compiler, {
    publicPath: config.output.publicPath,
}));

app.use(webpackHotMiddleware(compiler))
// Serve the files on port 3000.
app.listen(3000, function () {
    console.log('Example app listening on port 3000!\n');
});

// 在这种情况下，webpack使用eventSource与浏览器沟通，与websocket双向通信不同的是，eventsource只能从服务器到客户端