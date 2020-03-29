
(function () {
    source.plugin = new Object();

    source.plugins = [];

    source.plugin.init = function (plugin) {
        let obj = new PluginObj(plugin);
        source.plugin[obj.namespace] = obj;

        let old = null;
        source.plugins.forEach(one => {
            if (one.name == plugin.name) {
                old = one;
            }
        });
        if (old == null) {
            source.plugins.push(obj);
        } else {
            source.plugins.splice(source.plugins.indexOf(old), 1, obj);
        }
    };

    let PluginObj = function (plugin) {
        this.plugin = plugin;
        this.namespace = plugin.name;
        this.name = plugin.name;
        this.text = plugin.text;
        this.version = plugin.version;
    }

    PluginObj.prototype.event = function (type, data, project) {
        return source.server.event(this.name, this.version, type, data, project);
    };
})();
export default source;