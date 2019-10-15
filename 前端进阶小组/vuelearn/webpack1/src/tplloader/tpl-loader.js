var _ = require('lodash');

module.exports = function(source){
    // console.log(source);
    var template = _.template(source + 'loader has worked!');
    return 'module.exports = ' + template;
};