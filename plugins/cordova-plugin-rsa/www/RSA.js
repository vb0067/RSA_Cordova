var argscheck = require('cordova/argscheck'),
    exec      = require('cordova/exec');

function RSA () {};

RSA.prototype = {

    encrypt: function (params1, success, failure)
    {

      

        params = params || {};

        if(params === undefined) failure("传输的参数不能为空!!!");

        params[0] =params1;
        exec(success, failure, 'RSA', 'encrypt', params);
        
    },

};

module.exports = new RSA;
