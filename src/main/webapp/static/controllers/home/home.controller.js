(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location','UserService','AdvertiserService','$rootScope','$scope','$timeout','SessionService','DTOptionsBuilder', 'DTColumnBuilder','DTColumnDefBuilder','AllAdvertiserData','ReportService'];
    
    function HomeController($location,UserService, AdvertiserService,$rootScope, $scope,$timeout,SessionService,DTOptionsBuilder, DTColumnBuilder,DTColumnDefBuilder,AllAdvertiserData,ReportService) {

        var vm = this;
         var vm = this;
        vm.timeSelectedCustom = 'today';
        vm.groupBySelected = 'campaign';

        vm.report ={ advertiser:[],publisher:[], ad :[],campaign:[], from:"",to:"",groupby:[]};
        
        vm.device ={campaign:[],from:"2016-05-31",to:"2016-05-31",groupby:[]};

        vm.country ={campaign:[],from:"2016-05-31",to:"2016-05-31",groupby:[]};

        vm.zone ={campaign:[],from:"2016-05-31",to:"2016-05-31",groupby:[]};

        vm.allCampaigns = [];
        vm.selectedAdvCampaigns = [];

        vm.dtOptions = DTOptionsBuilder.newOptions().withDOM('dfrtip')
        .withPaginationType('simple_numbers')
        .withDisplayLength('10')
        .withButtons([
            'copy',
            'print',
            'excel'            
        ]).withLanguage({
            "sEmptyTable":     "No data available in table",
            "sInfo":           "Showing _START_ to _END_ of _TOTAL_ entries",
            "sInfoEmpty":      "Showing 0 to 0 of 0 entries",
            "sInfoFiltered":   "(filtered from _MAX_ total entries)",
            "sInfoPostFix":    "",
            "sInfoThousands":  ",",
            "sLengthMenu":     "_MENU_",
            "sLoadingRecords": "Loading...",
            "sProcessing":     "Processing...",
            "sSearch":         "",
            "sZeroRecords":    "No matching records found",
            'sSearchPlaceholder': "Search..."            
        });

        vm.DTColumnDefs = [
            DTColumnDefBuilder.newColumnDef(2).notSortable()
        ];

        initController();
        

         function initController() {
            NProgress.start();
            SessionService.initService();
            $rootScope.checkCurrentRol();
            getAdvertisers();                
            
        }
        
        $scope.times = {};
        $scope.times["today"]       = 'Today';
        $scope.times["yesterday"]   = "Yesterday";
        $scope.times["month"]       ="This Month";
        
        function getAdvertisers(){            
            vm.allAdvertisers = AllAdvertiserData.data;

            $.each(vm.allAdvertisers, function (i,a){
                vm.report.advertiser[i] = a.advertiserId;
                $.each(a.campaigns, function (j,c){
                    vm.allCampaigns.push(c);
                });
            }); 
            vm.report.groupby[0]='campaign';   
            vm.report.from="today";                            
            getAdvertiserReport(vm.report);
            
        }

        //Get Advertiser repot 
        function getAdvertiserReport(params){
          
            ReportService.getAdvertiserReport(vm.report).then(function(data){                 
                 vm.data  = data.data;                 
                 getTotals();               
                 NProgress.done();
            });   
        }
        
        
        function getTotals() {
            vm.globalRev = vm.globalConversions = vm.globalImpressions = vm.globalClicks = vm.globalRequests = vm.globalObjectiveImpressions = 0;
            angular.forEach(vm.data, function(value, key) {
                if(!angular.isUndefined(value)){
                    vm.globalImpressions+=parseInt(value.impressions);      
                    vm.globalClicks+=parseInt(value.clicks);     
                    vm.globalConversions+=parseInt(value.conversions);                                        
                    vm.isPublisher=true;
                    if(!angular.isUndefined(value.cost) && value.cost > 0){                                     
                        var revenue = value.cost;                                                        
                        vm.globalRev = parseFloat(vm.globalRev) + parseFloat(revenue);  
                        vm.globalRev = vm.globalRev.toFixed(2);
                    }
                }
            });
        }

        $rootScope.getColumnToFilter = function(what) {
            var th = '';
            if(what=='th') {
                angular.forEach($scope.groupBy, function(v, k){
                        if(v.id == vm.groupBySelected) {                                                     
                            th  = v.type;
                            return;
                        }

                });         
                return th;       
            } else {
                return what[vm.groupBySelected];
            }
        };

        


        $scope.myChartObject = {
              "type": "AreaChart",
              "displayed": false,
              "data": {
                "cols": [
                  {
                    "id": "month",
                    "label": "Month",
                    "type": "string",
                    "p": {}
                  },
                  {
                    "id": "laptop-id",
                    "label": "Laptop",
                    "type": "number",
                    "p": {}
                  },
                  {
                    "id": "desktop-id",
                    "label": "Desktop",
                    "type": "number",
                    "p": {}
                  },
                  {
                    "id": "server-id",
                    "label": "Server",
                    "type": "number",
                    "p": {}
                  },
                  {
                    "id": "cost-id",
                    "label": "Shipping",
                    "type": "number"
                  }
                ],
                "rows": [
                  {
                    "c": [
                      {
                        "v": "January"
                      },
                      {
                        "v": 19,
                        "f": "42 items"
                      },
                      {
                        "v": 12,
                        "f": "Ony 12 items"
                      },
                      {
                        "v": 7,
                        "f": "7 servers"
                      },
                      {
                        "v": 4
                      }
                    ]
                  },
                  {
                    "c": [
                      {
                        "v": "February"
                      },
                      {
                        "v": 13
                      },
                      {
                        "v": 1,
                        "f": "1 unit (Out of stock this month)"
                      },
                      {
                        "v": 12
                      },
                      {
                        "v": 2
                      }
                    ]
                  },
                  {
                    "c": [
                      {
                        "v": "March"
                      },
                      {
                        "v": 24
                      },
                      {
                        "v": 5
                      },
                      {
                        "v": 11
                      },
                      {
                        "v": 6
                      }
                    ]
                  }
                ]
              },
              "options": {
                "title": "Sales per month",
                "isStacked": "true",
                "fill": 20,
                "displayExactValues": true,
                "vAxis": {
                  "title": "Sales unit",
                  "gridlines": {
                    "count": 10
                  }
                },
                "hAxis": {
                  "title": "Date"
                }
              },
              "formatters": {}
            }


    }

})();