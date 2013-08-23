define(
  ["jquery", "backbone", "R", "underscore", "views", "models"],
  function($, Backbone, R, _, views, models) {

    var AppRouter = Backbone.Router.extend({
      initialize: function(options) {
      },

      // route: function(route, name, callback) {

      //   if( !callback ) callback = this[ name ];

      //   return Backbone.Router.prototype.route.call(this, route, name, function() {

      //     if(!this.storage.isLogin()){
      //       this.navigate('', {trigger: true});
      //       return false;
      //     } else {
      //       callback.apply(this, arguments);
      //     }
      //   });
      // },

      routes: {
        "": "home",
        "home": "home",
        "users": "users",
        "cubes": "cubes",
        "perform": "perform"
      },

      home: function() {
        var homeView = new views.Home().render();
      },
      users: function() {
        var usersRolesView = new views.UsersRoles({model: new models.Users()});
        // usersRolesView.render();
      },
      cubes: function() {
        var cubesView = new views.Cubes({model: new models.OlapConf()});
        // cubesView.render();
      },
      perform: function() {
        var performView = new views.Perform({model: new models.QueriesMysql()});
        performView.render();
      }
    });


    return AppRouter;
  });
