define(
  ["R", "backbone", "rbbone", "underscore"],
  function(R, Backbone, rbbone, _) {

    var domain = "/saiku_admin/api";

    var User = R.Model.extend();

    var UsersCollection = R.Collection.extend({
      model: User,
      url: function() {
        return domain+"/users";
      }
    });



    var Role = R.Model.extend();

    var RolesCollection = R.Collection.extend({
      model: Role,
      url: function() {
        return domain+"/roles";
      }
    });

    var OlapConf = R.Model.extend({
      url: function() {
        return domain+"/olap";
      }
    });

    var Olap = R.Model.extend({
      url: function() {
        return domain+"/olap";
      }
    });

    var QueryMysql = R.Model.extend();
    var QueriesMysqlCollection = R.Collection.extend({
      model: QueryMysql,
      url: function() {
        return domain+"/perform";
      }
    });

    var ExplainSql = R.Model.extend();

    var ExplainSqlCollection = R.Collection.extend({
      url: function() {
        return domain+'/perform/explain';
      },
      model: ExplainSql,
      fetch: function(options) {
        // we overwrite fetch method for send data by post type, because the query may be very large

        var that = this;

        $.ajax({
            type : 'POST',
            url : this.url(),
            data: { query: options.data.query },
            dataType : 'json',
            success : function(data) {

              for (var i = data.length - 1; i >= 0; i--) {
                that.add(new ExplainSql({
                  extra: data[i].extra, 
                  _id: data[i].id, 
                  key: data[i].key,
                  keyLen: data[i].keyLen,
                  possibleKeys: data[i].possibleKeys,
                  ref: data[i].ref,
                  rows: data[i].rows,
                  selectType: data[i].selectType,
                  table: data[i].table,
                  type: data[i].type}));
              }

              options.success();
            }
        });
      }
    });


    var Status = R.Model.extend({
      url: function() {
        return domain+'/status';
      }
    });


    return {
      Users: UsersCollection,
      User: User,
      Roles: RolesCollection,
      OlapConf: OlapConf,
      Olap: Olap,
      QueriesMysql: QueriesMysqlCollection,
      ExplainSql: ExplainSql,
      ExplainSqlCollection: ExplainSqlCollection,
      Status: Status
    };
});
