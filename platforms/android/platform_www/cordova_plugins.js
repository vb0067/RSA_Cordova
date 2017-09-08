cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "cordova-plugin-rsa.rsa",
        "file": "plugins/cordova-plugin-rsa/www/RSA.js",
        "pluginId": "cordova-plugin-rsa",
        "clobbers": [
            "deppon.rsa"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.3.2",
    "cordova-plugin-rsa": "1.2.1"
};
// BOTTOM OF METADATA
});