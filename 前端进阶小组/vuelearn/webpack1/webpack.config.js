const path = require('path')
const webpack = require('webpack')
const ConsoleLogOnBuildWebpackPlugin = require('./src/plugin/ConsoleLogOnBuildWebpackPlugin.js')
module.exports = {
    mode: 'production',
    entry: './src/index.js',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist')
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: ['file-loader']
            },
            {
                test: /\.(woff|woff2|eot|ttf|otf)$/,
                use: [
                    'file-loader'
                ]
            },
            {
                test: /\.(csv|tsv)$/,
                use: [
                    'csv-loader'
                ]
            },
            {
                test: /\.xml$/,
                use: [
                    'xml-loader'
                ]
            },
            {
                test:/\.tpl\.html$/,
                use:['tpl-loader']
            }
        ]
    },
    plugins:[new ConsoleLogOnBuildWebpackPlugin()]

}


webpack(module.exports, function (err, stats) {
    if (err) throw err

    process.stdout.write(stats.toString({
            colors: true,
            modules: false,
            children: false,
            chunks: false,
            chunkModules: false
        }) + '\n')

    console.log(' \n打包结束\n');
})

