cordova.define("cordova-plugin-rsa.rsa", function(require, exports, module) {
var argscheck = require('cordova/argscheck'),
    exec      = require('cordova/exec');


function RSA () {};

RSA.prototype = {

    encrypt: function (params, success, failure)
    {



        params = params || {};

        if(params === undefined) failure("传输的参数不能为空!!!");

        exec(success, failure, 'RSA', 'encrypt', params);

    },

};

module.exports = new RSA;

});
