require.config({
  baseUrl: "js/",
  paths: {
    text: "lib/text",
    jquery: "lib/jquery",
    underscore: "lib/underscore",
    backbone: "lib/backbone",
    R: "lib/prelude",
    rbbone: "lib/backbone-integration",
    BTransitions: "lib/bootstrap-transition",
    BModal: "lib/bootstrap-modal",
    BAlert: "lib/bootstrap-alert",
    validate: "lib/jquery.validate",
    highcharts: "lib/highcharts/highcharts"
  },
  shim: {
    backbone: {
      deps: ["underscore", "jquery"],
      exports: "Backbone"
    },
    underscore: { exports: "_", },
    R: {
     exports: "R"
    },
    BModal: {
     deps: ["BTransitions"]
    },
    BAlert: {
      deps: ["BTransitions"]
    },
    validate: {
      deps: ["jquery"]
    },
    rbbone: {
      deps: ["R", "backbone"],
      exports: "R"
    },
    highcharts: {
      deps: ["jquery"]
    },
    jquery: { exports: "$" }
  }
});

require(["jquery", "backbone"], function($, Backbone) {

  require(["approuter"], function(AppRouter) {
    window.app = new AppRouter();
    Backbone.history.start();
  });

});
