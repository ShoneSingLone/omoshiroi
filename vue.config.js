module.exports = {
    publicPath: "./",
    outputDir: "docs",
    runtimeCompiler: true,
    devServer: {
        open: true,
        proxy: {
            "^https://api.github.com": {
                target: "https://www.baidu.com"
            }
        }
    }
};