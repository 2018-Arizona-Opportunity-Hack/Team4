
(function(){
    var app = angular.module('app');
    app.controller('ReportController',ReportController);
    ReportController.$inject = ['$scope','$http', 'fileUpload', '$rootScope'];

    function ReportController($scope,$http, fileUpload, $rootScope){
      $scope.initialize = function(){
        $scope.chartType = 'spline';  
        $scope.query = {};
        $scope.options = [{}];
        $scope.initSavedQueries();
        $scope.activeSel;
        $scope.tableData = [];

        // $http.get(configData.url+"/")
        //     .then(function successCallback(response){
        //         if(response.data != null){                    
        //             console.log(response);
        //         }else{
        //             //$scope.error = "Unable to fetch posts";
        //         }

        //     }, function errorCallback(response){
        //         console.log("Error updating views");
        //         //$scope.error = "Unable to fetch posts";
                
        //     });


      }

      $scope.initSavedQueries = function(){
        $scope.savedQueries = [
            {
                id: 1,
                query: 'select donor.id, donor.name, sum(donation.amount) from donor, donations where donor.id = donation.donor_id group by donor.id',
                name: 'Total Donations made by each donor',
                columns: ['Donor Id','Donor Name','Amount']
            }, {
                id: 2,
                query: 'select * from patients_admit_records ',
                name: 'Patients needing 24 hrs attention',
                columns: ['col1','col2','col3']
            },

        ];
      }

      $scope.executeQuery = async function(query){
        $scope.savedQueryData =  await $scope.getQueryData({'query': query.query, 'format':'json'});
        $scope.savedHeader = query.columns;
        $scope.$apply();
        setTimeout(function() {
            $('#table_id1').DataTable();
          });

      }

      $scope.getRelatedColumns = function(){
        
        $http.get(configData.url+"getRelatedFieldColumns?tableName="+$scope.query.entityName)
            .then(function successCallback(response){
                if(response.data != null){                    
                    console.log(response);
                    $scope.selEntityCols = response.data;
                }

            }, function errorCallback(response){
                console.log("Error updating views");
                
            });
      }

      $scope.setSelEntityIndex = function(){
          let count = 0;
          $rootScope.allEntityDetails.forEach(e => {
              if(e.entityName == $scope.query.entityName){
                $scope.query.selEntityIndex = count;
                
              }
              count++;
          });
      }

      $scope.buildQuery = function(){
        let query = "";
        let select = "";
        if ($scope.activeSel == 1 || $scope.activeSel == 2) {
            query = "select * from "+$scope.query.entityName+";";
        } else if ($scope.activeSel == 3 && $scope.query.full) {
            let xaxis = $scope.query.cond.select.one;
            let yaxis = $scope.query.cond.select.two;

            if(!xaxis || xaxis.length == 0){
                return;
            }
            if(!yaxis || yaxis.length == 0){
                return;
            }
            query = "select "+xaxis+","+yaxis+" from "+$scope.query.entityName+";";
        }
        return query;
      }

      $scope.getQueryData = function(options){
        return $http.post(configData.url+"export", options)
        .then(function successCallback(response){
            let result = [];
            if(response.data != null){   
                response.data.data.forEach(ele => {
                    result.push(ele);
                });                
                return result;
                console.log(response);
            }else{
                //$scope.error = "Unable to fetch posts";
            }

        }, function errorCallback(response){
            console.log("Error updating views");
            //$scope.error = "Unable to fetch posts";
            
        });
      }

      $scope.showData = async function(isCSV){
        let query = $scope.buildQuery();
        $scope.tableData = await $scope.getQueryData({query, 'format': isCSV ? 'csv':'json'});
        //$scope.tableData = [["1","Test","90","2018-10-10 07:00:00.0"],["3","Test","909","2018-10-10 07:00:00.0"],["4","Test","9","2018-10-10 07:00:00.0"],["5","Test","1","2018-10-10 07:00:00.0"],["6","Test","3","2018-10-10 07:00:00.0"],["7","Test","4","2018-10-10 07:00:00.0"]]
        if (!isCSV) {
            $scope.$apply();
            setTimeout(function() {
                $('#table_id').DataTable();
            });
        }
      }

      $scope.exportCsv = function(){
        $http.post(configData.url+"exportCsv", {'query':query})
            .then(function successCallback(response){
                if(response.data != null){                    
                    console.log(response);
                }

            }, function errorCallback(response){
                console.log("Error updating views");
                
            });
      }

      $scope.changeChart = function(chartType){
        var chartFinal;
        var chartTitle;
        if(chartType=='line'){
              chartFinal = 'spline';
              chartTitle = 'Line Chart';
        }
        else{
          chartFinal = 'column';
          chartTitle = 'Bar Chart';
        }
      var arrayNew = [];
      for(var i=0;i<$scope.tableData.length;i++ ){ var innerArray = []; innerArray.push(($scope.tableData[0][0])); innerArray.push(Number($scope.tableData[0][1]));  arrayNew.push(innerArray);}
      // var data = $scope.tableData;

      Highcharts.chart('chartContainer', {
          chart: {
              type: chartFinal,
          },
          title: {
              text: chartTitle
          },
          // subtitle: {
          //     text: 'According to the Standard Atmosphere Model'
          // },
          xAxis: {
              reversed: false,
              title: {
                  // enabled: true,
                  text: 'xAxis'
              },
              labels: {
                  format: '{value} '
              },
              maxPadding: 0.05,
              showLastLabel: true
          },
          yAxis: {
              title: {
                  text: 'yAxis'
              },
              labels: {
                  format: '{value}'
              },
              lineWidth: 2
          },
          legend: {
              enabled: false
          },
          tooltip: {
              headerFormat: '<b>{series.name}</b><br/>',
              pointFormat: '{point.x} : {point.y}'
          },
          plotOptions: {
              spline: {
                  marker: {
                      enable: false
                  }
              }
          },
          series: [{
              // name: 'Temperature',
              data: arrayNew
          }]
      });   
      
    }

    $scope.createCharts = function(chartType, arrayNew){
        Highcharts.chart('chartContainer', {
            chart: {
                type: chartType,
            },
            title: {
                text: 'Line Chart'
            },
            // subtitle: {
            //     text: 'According to the Standard Atmosphere Model'
            // },
            xAxis: {
                reversed: false,
                title: {
                    // enabled: true,
                    text: 'xAxis'
                },
                labels: {
                    format: '{value} '
                },
                maxPadding: 0.05,
                showLastLabel: true
            },
            yAxis: {
                title: {
                    text: 'yAxis'
                },
                labels: {
                    format: '{value}'
                },
                lineWidth: 2
            },
            legend: {
                enabled: false
            },
            tooltip: {
                headerFormat: '<b>{series.name}</b><br/>',
                pointFormat: '{point.x} : {point.y}'
            },
            plotOptions: {
                spline: {
                    marker: {
                        enable: false
                    }
                }
            },
            series: [{
                // name: 'Temperature',
                data: arrayNew
            }]
        });   
    }

    $scope.generateCharts = async function(){
      let query = $scope.buildQuery();
      if(!query || query.length == 0){
          return;
      }
      var data = [[2,6],[3,5],[4,2],[5,1]];
      $scope.tableData = await $scope.getQueryData({query, 'format':'json'});
      
      var arrayNew = [];
      for(var i=0;i<$scope.tableData.length;i++ ){ var innerArray = []; innerArray.push(($scope.tableData[0][0])); innerArray.push(Number($scope.tableData[0][1]));  arrayNew.push(innerArray);}
      // var data = $scope.tableData;

      $scope.createCharts($scope.chartType, arrayNew);
    }

    }

})();

