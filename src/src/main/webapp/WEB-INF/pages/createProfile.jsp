<html>

<style> 
.panel-body{
    width: 38em; 
    word-wrap: break-word;
}
</style>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Plan Studies</title>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
	
	<script src="/js/bootstrap.min.js"></script>

    <!-- Custom CSS -->
    <link href="/css/stylish-portfolio.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


</head>

<body>
	<form>
	  <fieldset class="form-group">
	    <label for="Course Name">Course Name</label>
	    <input type="text" class="form-control" id="courseName" placeholder="Course Name">
	  </fieldset>
	  <fieldset class="form-group">
	    <label for="Course Id">Course Id</label>
	    <input type="text" class="form-control" id="courseId" placeholder="Course Id">
	  </fieldset>
	  <fieldset class="form-group">
	    <label for="University/College">University/College</label>
	    <input type="text" class="form-control" id="university" placeholder="University">
	  </fieldset>
	  <fieldset class="form-group">
	    <label for="Course Id">Find available classes within radius of </label>
	    <input type="text" class="form-control" id="radius" placeholder="">
	  </fieldset>
	</form>
	<button type="button" id="search-submit" class="btn btn-primary">Find</button>
	
		<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Course Info</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</body>
<script>
function fetchCourses() {
	var html;
	//$("#loadDiv").show();
	var query = $("#courseName").val() + "," + $("#courseId").val() + "," + $("#university").val() + "," + $("#radius").val();
	$.ajax({
    type: "POST",
    url: "http://localhost:8080/StudentFinancial/rest/stufin",
    contentType: "application/json; charset=utf-8",
    accept : "text/html",
    async: false,
    timeout: 120000,
    //dataType: "json",
    data: query,
    success: function(data1) {
    	//data = JSON.parse(data1);
		data = data1;
		//$("#loadDiv").hide();
    },
    error: function (xhr, textStatus, errorThrown) {
        $("#error").html(xhr.responseText);
    }
	});
	if (data) {
		html =  '<div class="container">' +   data +'</div>';
	}
	$(".modal-body").html(html);
	$('#myModal').modal({
		keyboard: false
	});
}



$(document).ready(function(){
	$("#search-submit").click(function() {
		fetchCourses();
	});
});
</script>

</html>