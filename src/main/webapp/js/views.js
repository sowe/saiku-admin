define(
  ["backbone", "rbbone", "BModal", "BAlert", "validate", "R", "models", "highcharts", 
  "text!templates/home.html",
  "text!templates/partials/_home_users.html",
  "text!templates/partials/_home_roles.html",
  "text!templates/partials/_roles.html",
  "text!templates/partials/_explain_query.html",
  "text!templates/users_roles.html",
  "text!templates/cubes.html",
  "text!templates/perform.html"],
  function(Backbone, rbbone, BModal, BAlert, validate, R, models, highcharts, 
    homeTemplate, homeUsersTemplate, homeRolesTemplate, rolesTemplate, explainQueryTemplate, usersRolesTemplate, cubesTemplate, performTemplate) {


    var BaseView = R.View.extend({
      init: function(options) {
        this._super(options);
        this.subviews = new Array();
      },
      tagName: 'div',
      showLoading: function() {
        $('#loading').show();
      },
      hideLoading: function() {
        $('#loading').hide();
      },
      showMessage: function(msg) {
        $(this.el).prepend('<div class="alert alert-success fade in"><button type="button" class="close" data-dismiss="alert">&times;</button>'+msg+'</div>')
        $(".alert", this.el).alert();
      }
      ,
      close: function () {
        if(this.subviews && this.subviews.length>0) {
          for (var i = 0; i<this.subviews.length; i++) {
            this.subviews[i].close();
          }
        }

        if (this.interval) {
          clearInterval(this.interval);
        }
        // this.remove(); // Remove view from DOM
        this.undelegateEvents();
        $(this).empty;
        this.unbind(); // Unbind all local event bindings
      }
    });


    var HomeView =  BaseView.extend({
      initialize: function(options) {
        this._super(options);

        this.showLoading();
      },
      el: $("#content"),
      template: _.template(homeTemplate),
      render: function() {

        $('.nav li').removeClass('active');
        $('.nav li.menu_home').addClass('active');

        $(this.el).html(this.template());

        this.subviews.push(new HomeUsersView({model: new models.Users(), parentEl: this.el}));
        this.subviews.push(new HomeRolesView({model: new models.Roles(), parentEl: this.el}));


        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
    
        var chart = 
          $('#grafic_status').highcharts({
              chart: {
                  type: 'spline',
                  animation: Highcharts.svg, // don't animate in old IE
                  marginRight: 10
              },
              series: [{
                  name: ' ',
                  data: (function() {
                      // generate an array of random data
                      var data = [],
                          time = (new Date()).getTime(),
                          i;

                      // data.push({x:time, y:0});

                      for (i = -10; i <= 0; i++) {
                          data.push({
                              x: time,
                              y: 0
                          });
                      }
                      return data;
                  })()
              }],
              title: {
                  text: 'Mysql'
              },
              xAxis: {
                  type: 'datetime',
                  tickPixelInterval: 150
              },
              yAxis: {
                  title: {
                      text: 'Queries per second avg'
                  },
                  plotLines: [{
                      value: 0,
                      width: 1,
                      color: '#808080'
                  }]
              },
              tooltip: {
                  formatter: function() {
                          return '<b>'+Highcharts.numberFormat(this.y, 2)+'</b><br/>'+
                          Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x);
                  }
              },
              legend: {
                  enabled: false
              }
          }).highcharts();


        this.subviews.push(new StatusView({model: new models.Status(), chart: chart}));

        // this.hideLoading();

        return this;
      }
    });

    var HomeUsersView =  BaseView.extend({
      initialize: function(options) {
        this.model.on("sync", this.render, this);
        this.model.fetch();

        this.el = $(".home_users", options.parentEl);
      },
      template: _.template(homeUsersTemplate),
      render: function() {
        $(this.el).html(this.template({data: this.model.toJSON()}));
        return this;
      }
    });

    var HomeRolesView =  BaseView.extend({
      initialize: function(options) {
        this.model.on("sync", this.render, this);
        this.model.fetch();

        this.el = $(".home_roles", options.parentEl);
      },
      template: _.template(homeRolesTemplate),
      render: function() {
        $(this.el).html(this.template({data: this.model.toJSON()}));
        this.hideLoading();
        return this;
      }
    });




    var UsersRolesView =  BaseView.extend({
      init: function(options) {
        this._super(options);
        this.model.on("sync", this.render, this);
        this.model.fetch();
        this.roles = new models.Roles();

        this.showLoading();
      },

      events: {
        "submit #form_user"         : "create_user",
        "submit #form_roles"        : "create_role"
      },

      create_role: function() {
        this.roles.create({name: $('#form_roles input[name="role"]').val()});

        $('#newRole').modal('hide');
        this.render({successMsg: 'The role has been created successfully'});

        return false;
      },
      create_user: function() {
        this.model.create({
            name: $('input[name="name"]').val(),
            surName: $('input[name="surname"]').val(),
            email: $('input[name="email"]').val(),
            user: $('input[name="user"]').val(),
            userName: $('input[name="username"]').val(),
            password: $('input[name="password"]').val(),
            roles: $('select[name="roles"]').val() || []
        });

        $('#newUser').modal('hide');
        this.render({successMsg: 'The user has been created successfully'});

        return false;
      },

      el: $("#content"),
      template: _.template(usersRolesTemplate),
      render: function(options) {
        $('.nav li').removeClass('active');
        $('.nav li.menu_users').addClass('active');


        var that = this;
        this.roles.fetch({success: function() {
          $(that.el).html(that.template({data: that.model.toJSON(), roles: that.roles.models}));

          that.subviews.push(new RolesView({model: that.roles, parentEl: that.el}));

          $("#content #form_user").validate();

          if(options.successMsg) {
            that.showMessage(options.successMsg);
          }
        }});

        this.hideLoading();

        return this;
      }
    });

    var RolesView =  BaseView.extend({
      initialize: function(options) {
        this.model.on("sync", this.render, this);
        this.model.fetch();

        this.parentEl = options.parentEl;
        this.el = $(".content_roles", options.parentEl);

      },
      template: _.template(rolesTemplate),
      render: function() {
        $(this.el).html(this.template({data: this.model.toJSON()}));
        $("#form_roles", this.el).validate();
        return this;
      }
    });




    var CubesView =  BaseView.extend({
      init: function(options) {
        this._super(options);
        this.showLoading();
        this.roles = new models.Roles();
        var that = this;
        this.roles.fetch({success: function(){
          that.model.on("sync", that.render, that);
          that.model.fetch();
        }});
      },

      el: $("#content"),

      events: {
        "click .dup_row" : "dup_row",
        "change select[name='security_type']" : "change_security_type",
        "submit #form_cube"         : "create_cube"
      },

      dup_row: function () {
        var tr_dup = $('tr.dup').clone().removeClass('dup');
        $('input[type="text"]', tr_dup).val('');
        tr_dup.insertAfter($('tr:last-child').prev());
      },

      change_security_type: function () {

        var security_type = $('select[name="security_type"]').val();
        if( security_type=='one2one' || security_type=='lookup' ) {
          $('.content_olap_roles').show();
        } else {
          $('.content_olap_roles').hide();
        }
      },

      create_cube: function() {

        var olap = new models.Olap();

        var _mapping = [];
        var i = 0;

        $("input[name=role_spring]").each(function() {
          _mapping[i] = {roleSpring: $(this).val(), rolesSaiku: []};
          i++;
        });

        i = 0;
        $("select[name=roles_saiku]").each(function() {
          _mapping[i].rolesSaiku = $(this).val();
          i++;
        });


        olap.save({
            name: $('input[name="name"]').val(),
            security: {
              type: $('select[name="security_type"]').val(),
              enable: $('input[name="security_enable"]').is(':checked'),
              mapping: _mapping
            },
            connection: {
              type: $('select[name="connection_type"]').val(),
              driver: $('select[name="connection_driver"]').val(),
              databaseJdbcDriver: $('select[name="connection_jdbc_driver"]').val(),
              databaseJdbc: $('input[name="connection_jdbc"]').val(),
              databaseUsername: $('input[name="connection_username"]').val(),
              databasePassword: $('input[name="connection_password"]').val(),
              catalog: $('input[name="connection_catalog"]').val()
            }
        });

        this.render({successMsg: 'The operation has been completed successfully'});

        return false;
      },

      template: _.template(cubesTemplate),
      render: function(options) {

        $('.nav li').removeClass('active');
        $('.nav li.menu_cubes').addClass('active');

        $(this.el).html(this.template({data: this.model.toJSON(), roles: this.roles.toJSON()}));
        $("#form_cube", this.el).validate();

        this.hideLoading();

        if(options.successMsg) {
          this.showMessage(options.successMsg);
        }

        return this;
      }
    });


    var PerformView =  BaseView.extend({
      init: function(options) {
        this._super(options);
        this.showLoading();
        this.model.on("sync", this.render, this);
        this.model.fetch();
      },

      el: $("#content"),
      template: _.template(performTemplate),

      events: {
        'click .show_explain': 'show_explain'
      },

      show_explain: function(ev) {

        this.subviews.push( new QueryExplainView({model: new models.ExplainSqlCollection(),
                                                  query: $(ev.target).html()}));

        return false;
      },

      render: function() {
        $('.nav li').removeClass('active');
        $('.nav li.menu_perform').addClass('active');

        $(this.el).html(this.template({data: this.model.toJSON()}));

        this.hideLoading();

        return this;
      }
    });



    var QueryExplainView = BaseView.extend({
      init: function(options) {
        this._super(options);
        this.showLoading();

        var that = this;
        this.model.fetch({data: {query: options.query}, success: function() {
          that.render();
        }});

      },
      template: _.template(explainQueryTemplate),
      render: function() {

        $('#explain_query').html(this.template({data: this.model.toJSON()}));
        this.hideLoading();

        return this;
      }
    });


    var StatusView = BaseView.extend({
      init: function(options) {
        this._super(options);
        this.chart = options.chart;

        this.model.on("sync", this.render, this);
        this.model.fetch();

        var that = this;
        this.interval = setInterval(function(){
            console.log('fetch');
            that.model.fetch();
          }, 2000);
      },
      render: function() {

        var x = (new Date()).getTime(), // current time
            y = this.model.attributes['queriesPerSecondAvg'];

        this.chart.series[0].addPoint([x,y], true, true);
      }
    });


    return {
      Home: HomeView,
      UsersRoles: UsersRolesView,
      Cubes: CubesView,
      Perform: PerformView
    };

  });
