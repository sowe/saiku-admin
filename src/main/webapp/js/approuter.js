define(
  ["jquery", "backbone", "R", "underscore", "views", "models"],
  function($, Backbone, R, _, views, models) {

    var currentView;

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
        if (currentView) {
          currentView.close();
        }
        currentView = new views.Home().render();
      },
      users: function() {
        if (currentView) {
          currentView.close();
        }
        currentView = new views.UsersRoles({model: new models.Users()});
      },
      cubes: function() {
        if (currentView) {
          currentView.close();
        }
        currentView = new views.Cubes({model: new models.OlapConf()});
      },
      perform: function() {
        if (currentView) {
          currentView.close();
        }
        currentView = new views.Perform({model: new models.QueriesMysql()}).render();
      }
    });


    return AppRouter;
  });
