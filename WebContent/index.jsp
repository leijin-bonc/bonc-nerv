<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="js/echarts.js"></script>
<script src="js/jquery-1.7.2.js"></script>
<link rel="stylesheet" type="text/css" href="css/index.css">
<title></title>
</head>
<body>
	<div class="all">
		<div class="top">
			<div class="h">
			
			<h1><img src="images/bonc.png"></h1>
			</div>
		</div>
		<div class="bottom">
		<div class="sj">
			<div class="sj_top">数据导出</div>
			<div class="sj_sj">
				<form action="out" method="post">
					<input type="Date"name="date"/>
					<input type="submit" value="数据导出"/>
				</form>
			</div>
		</div>
		
		<div class="tb">
			<div class="sj">
			<div class="sj_top">图表显示</div>
			<div class="sj_sj">
					<input id="date" type="date" placeholder="显示日期" value=""/>
					<input id="tm" type="time" placeholder="显示时刻" value=""/>
					<input id="num" type="number" placeholder="显示个数" value=""/>
					<input type="submit" value="显示图表" onclick="view()"/>
			</div>
		</div>
			<div class="pic">
				<ul>
					<li><div id="ftp" style="width: 800px;height: 500px"></div></li>
				</ul>	
			</div>
			<div class="pic">
				<ul>
					<li><div id="hbase" style="width:800px;height: 500px"></div></li>
				</ul>	
			</div>
			<div class="pic">
				<ul>
					<li><div id="hive" style="width: 800px;height: 500px">	</div></li>
				</ul>	
			</div>
			<div class="pic">
				<ul>
					<li><div id="xcloud" style="width: 800px;height: 500px"></div></li>
				</ul>	
			</div>
			</div>
			
		</div>
		</div>
		
	</div>
	<script type="text/javascript">
	debugger;
	  var str= new Array('ftp','hbase','hive','xcloud');
	  var arr= new Array();
	  read();
	  
	  /*  发送ajax请求数据*/
	  function read(dt,num,tm){
			  $.ajax({
		          type : "post",
		          async : false, //同步执行
		          url : "XData",
		          data : {dt:dt,num:num,tm:tm},
		          dataType : "json", //返回数据形式为json
		          success : function(result) {
				          if (result) {
				                 for(var i=0;i<result.length;i++){
				                    arr.push(result[i]);
				                  }    
				          }
		          
				      },
				      error : function(errorMsg) {
				          alert("图表请求数据失败啦!");
				      }
				     })
	  }  
	  
	  
	/*遍历集合 初始化图表 */     
	  for(var sw=0;sw<str.length;sw++){
		chat(str[sw],arr[sw]);
	  } 
	  
	  function chat(str1,arr1){
		  
		  
		  require.config({
	          paths:{ 
	              'echarts' : 'http://echarts.baidu.com/build/dist'
	          }
	      });
	      // 使用
	      require(
	          [
	              'echarts',
	              'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	          ],
	          drewEcharts
	      );
	      
	      
	       function drewEcharts(ec) {
	              // 基于准备好的dom，初始化echarts图表
	              myChart = ec.init(document.getElementById(str1)); 
	              var option = {
	                  tooltip: {
	                      show: true
	                  },
	                  title : {
	                	  show:true,
	                	  text:str1+"类型文件"
	                  },
	                  legend: {
	                      data:['文件数']
	                  },
	                  xAxis : [
	                      {
	                          type : 'category',
	                          data : (function(){
	                        	  var arr2=[];
	                        	  for(var i=0;i<arr1.bonc.length;i++){
                                      arr2.push(arr1.bonc[i].name);
                                    } 
	                        	  return arr2;
                          })() 
	                     }
	                  ],
	                  yAxis : [
	                      {
	                          type : 'value'
	                      }
	                  ],
	                  series : [
	                      {
	                          "name":"文件数",
	                          "type":"bar",
	                          "data":(function(){
	                        	  var arr2=[];
	                        	  for(var i=0;i<arr1.bonc.length;i++){
                                      arr2.push(arr1.bonc[i].files);
                                    } 
	                        	  return arr2;
                          })() 
	                          
	                      }
	                  ]
	              };               
	              // 为echarts对象加载数据 
	              myChart.setOption(option);        
	          }
	       }  
	  function view(){
		  debugger;
		var dt= $("#date").val();
		var tm= $("#tm").val();
		var num= $("#num").val();
		if(num == undefined || num=="" || num < 0){
			alert("请选正确的数据个数！");
			return ;
		}
		
		if(dt == undefined || dt==""){
			alert("请选择要显示的日期！");
			return ;
		}else if(tm == undefined || tm==""){
			alert("请选择要显示的时刻！");
			return ;
		}else{
			tm= tm.substring(0,2)+tm.substring(3);
			arr=[];
			read(dt,num,tm);
			for(var sw=0;sw<str.length;sw++){
				chat(str[sw],arr[sw]);
			} 
		}
	  }
	</script>
	
	<!-- <script type="text/javascript">
        // 路径配置
        require.config({
            paths:{ 
                'echarts' : 'http://echarts.baidu.com/build/dist'
            }
        });
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            drewEcharts
        );
         function drewEcharts(ec) {
                // 基于准备好的dom，初始化echarts图表
                myChart = ec.init(document.getElementById('main')); 
                var option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['文件数']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : (function(){
                                    var arr=[];
                                        $.ajax({
                                        type : "post",
                                        async : false, //同步执行
                                        url : "XData",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].name);
                                                  arr.push(result[i].name);
                                                }    
                                        }
                                        
                                    },
                                    error : function(errorMsg) {
                                        alert("不好意思，大爷，图表请求数据失败啦!");
                                        myChart.hideLoading();
                                    }
                                   })
                                   return arr;
                                })() 
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"文件数",
                            "type":"bar",
                            "data":(function(){
                                        var arr=[];
                                        $.ajax({
                                        type : "post",
                                        async : false, //同步执行
                                        url : "XData",
                                        data : {},
                                        dataType : "json", //返回数据形式为json
                                        success : function(result) {
                                        if (result) {
                                               for(var i=0;i<result.length;i++){
                                                  console.log(result[i].num);
                                                  arr.push(result[i].num);
                                                }  
                                        }
                                    },
                                    error : function(errorMsg) {
                                        alert("不好意思，大爷，图表请求数据失败啦!");
                                        myChart.hideLoading();
                                    }
                                   })
                                  return arr;
                            })()
                            
                        }
                    ]
                };               
                // 为echarts对象加载数据 
                myChart.setOption(option);        
            }
    </script> -->
</body>
</html>