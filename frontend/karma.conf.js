module.exports = function(config) {
    config.set({
        frameworks: ['jasmine'],
        files: [
            'src/**/*.js',
            'test/**/*.spec.js'
        ],
        browsers: ['Chrome'],
        preprocessors: {
            'src/**/*.js': ['coverage']
        },
        reporters: ['progress', 'coverage'],
        singleRun: true
    });
};