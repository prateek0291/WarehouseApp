<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
	<%@include file="UserMenu.jsp"%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">
				<h1>Welcome to Uom Register Page</h1>
			</div>
			<div class="card-body">
				<form:form id="myForm" action="save" method="POST"
					modelAttribute="uom">
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="uomType"> UOMTYPE</label>
						</div>
						<div class="col-4">
							<form:select path="uomType" class="form-control">
								<form:option value="">-SELECT-</form:option>
								<form:option value="PACKING">PACKING</form:option>
								<form:option value="NO PACKING">NO PACKING</form:option>
								<form:option value="-NA-">-NA-</form:option>
							</form:select>
						</div>
						<div class="col-4" id="uomTypeError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="uomModel"> UOMMODEL</label>
						</div>
						<div class="col-4">
							<form:input path="uomModel" class="form-control" />
						</div>
						<div class="col-4" id="uomModelError"></div>
					</div>
					<!--new Row -->
					<div class="row">
						<div class="col-4">
							<label for="note"> NOTE</label>
						</div>
						<div class="col-4">
							<form:textarea path="note" class="form-control" />
						</div>
						<div class="col-4" id="noteError"></div>
					</div>
					<input type="submit" value="Register"  id="register" class="btn btn-success" />
				</form:form>
			</div>
			<div class="card-footer bg-info text-white">
				<b>${message}</b>
			</div>
		</div>
	</div>
	
	<script>
$(document).ready(function(){
    //1. Hide Error section
    $("#uomModelError").hide();
    $("#noteError").hide();
    $("#uomTypeError").hide();

    //2. Define flag for error section
    var uomModelError = false;
    var noteError = false;
    var uomTypeError = false;

    //3. Link with action/event
    $("#uomModel").keyup(function(){
        validate_uomModel();
    });

    $("#uomModel").blur(function(){
    	var val=$("#uomModel").val();
    	$.ajax({
			url : 'checkmodel',
			data:{ "uomModel":$("#uomModel").val()},
			success:function(resTxt){
				if(resTxt!=''){
					$("#uomModelError").show();
					$("#uomModelError").html(resTxt);
					$("#uomModel").val("");
					$("#uomModel").focus();
				}else{
					 $("#uomModelError").hide();
				}
					
			}

       	});
     });

    $("#note").keyup(function(){
        validate_note();
    });

    $("#uomType").change(function(){
        validate_uomType();
    });


    //4. drfine one validation function
    function validate_uomModel(){
        var val=$("#uomModel").val();
        if(val==''){
            $("#uomModelError").show();
            $("#uomModelError").html("Enter <b>UOM MODEL</b>")
            $("#uomModelError").css("color","red");
            uomModelError = false;
        }else{
            $("#uomModelError").hide();
            uomModelError = true;
        }
        return uomModelError;
    }

    function validate_note(){
        var val=$("#note").val();
        if(val==''){
            $("#noteError").show();
            $("#noteError").html("Enter <b>UOM DESCRIPTION</b>");
            $("#noteError").css("color","red");
            noteError = false;
        }else{
            $("#noteError").hide();
            noteError = true;
        }

        return noteError;
    }

    function validate_uomType(){
        var val=$("#uomType").val();

        if(val==''){
            $("#uomTypeError").show();
            $("#uomTypeError").html("Choose <b>UOM TYPE</b>");
            $("#uomTypeError").css("color","red");
            uomTypeError = false;
        }else{
            $("#uomTypeError").hide();
            uomTypeError = true;
        }
        return uomTypeError ;
    }

    // on click submit button
    $("#register").click(function(){
        //on click submit button

        //a. reset flags to false
         uomModelError = false;
         noteError = false;
         uomTypeError = false;

        //b. call all validate function
         validate_uomModel();
         validate_note();
         validate_uomType();

         //c. check if all are true then submit else dont
         if(uomModelError && noteError && uomTypeError){
             return true;
         }
         else return false;

    });

});    
</script>
</body>
</html>