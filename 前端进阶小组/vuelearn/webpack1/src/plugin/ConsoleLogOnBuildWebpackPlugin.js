const pluginName = 'ConsoleLogOnBuildWebpackPlugin';

class ConsoleLogOnBuildWebpackPlugin {
    apply(compiler) {

        // compiler.plugin('should-emit', compilation => {
        //     console.log('should i emit?');
        //     return true;
        // })
        // compiler.plugin('emit', (compilation, callback) => {
        //     console.log('Have I reached here?');
        //     callback()
        // })
        //


        compiler.hooks.run.tap(pluginName, compilation => {
            console.log("webpack 构建过程开始！");
        });
    }
}
module.exports = ConsoleLogOnBuildWebpackPlugin;