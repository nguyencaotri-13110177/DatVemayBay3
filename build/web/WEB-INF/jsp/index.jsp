<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>DatVeMayBay::CaoTri-AnhTuong</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/main.css">



  <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
  
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



<style>
.btnCustom {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	-webkit-transition-duration: 0.4s; /* Safari */
	transition-duration: 0.4s;
}

.dobong:hover {
	box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0
		rgba(0, 0, 0, 0.19);
}

/*---chỉnh vị trí model-----*/
.modal {
  text-align: center;
}

@media screen and (min-width: 768px) { 
  .modal:before {
    display: inline-block;
    vertical-align: middle;
    content: " ";
    height: 100%;
  }
}

.modal-dialog {
  display: inline-block;
  text-align: left;
  vertical-align: middle;
}
/*---End chỉnh vị trí model-----*/

</style>

<script type="text/javascript">
$(document).ready(function(){
	$('#btntimve').click(function(e){
		e.preventDefault();
                
                var masanbaydi,masanbayden,ngaydi;

                // Get the value of the input field with id="numb"
                masanbaydi = document.getElementById("MaSanBayDi").value;
                masanbayden = document.getElementById("MaSanBayDen").value;
                ngaydi = document.getElementById("NgayDi").value;
                
                //------------Kiem tra san bay di != san bay den-------------------
                if(masanbaydi===masanbayden)
                {
                    document.getElementById("thongbao").innerHTML = "<spring:message code="lable.notification.AirportDepartureMustBeAnotherAirportArrival" text="Sân bay đi phải khác sân bay đến"/>";
                    $("#myModal4").modal({backdrop: true});                  
                }
                //------------Kiem tra ngay di != null-------------------
                else if(ngaydi==="")
                {
                    document.getElementById("thongbao").innerHTML = "<spring:message code="lable.notification.PleaseSelectADate" text="Vui lòng chọn ngày đi"/>";
                    $("#myModal4").modal({backdrop: true});
                }
                //------------Nếu thỏa các input thì mới tìm vé-------------------
                else
                {
                $.ajax({
			url:'timve.ute',
			type:'POST',
			dataType:'html',
                        beforeSend: function() {
                            $("#myModal3").modal({backdrop: "static"});
                            
                        },
                        complete: function() {
                            $('#myModal3').modal('toggle');
                        },
			data:{
				MaSanBayDi:$('#MaSanBayDi option:selected').val(),
				MaSanBayDen:$('#MaSanBayDen option:selected').val(),
				NgayDi:$('#NgayDi').val()
			}
		}).done(function(ketqua){
			$('#noidung').html(ketqua);
		});
                }
	});
});
</script>

</head>
<body>
	<%
    	String session_user=(String)session.getAttribute("username");
    %>
	<%@ include file="/module/MenuIndex.jsp"%>
	<!-- End trasparent_nav -->
                
               
               
                
  <div class="container">                                          
      <!-- Modal please wait -->
      <div class="modal fade" id="myModal3" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">

            <div class="modal-body">
              <p>Please wait...</p>
            </div>

          </div>

        </div>
      </div>
      
      <!-- Modal kiem tra input -->
      <div class="modal fade" id="myModal4" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            
            <div class="modal-body">
              <p id="thongbao" style="color:red">Thông báo...</p>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-success" data-dismiss="modal">OK</button>
            </div>
          </div>

        </div>
      </div>
  </div>
        
        
        <!--Khung tìm vé bên phải-->
	<div class="panel panel-default"
		style="margin-top: 10%; margin-left: 3%; width: 20%;position: fixed">
		<div class="panel-heading">
			<center><spring:message code="lable.search.SearchTickets" text="Tìm vé"/></center>
		</div>
		<div class="panel-body">
			<form> <!--action="timve.ute" method="post"  => đưa lên Sript sử dụng Ajax-->
				<div class="form-group">
					<label for="email"><spring:message code="lable.search.From" text="Sân bay đi"/>:</label> <select name="MaSanBayDi" id="MaSanBayDi"
						class="form-control">
						<c:forEach var="f" items="${ListSanBay}">
							<option value="${f.getMaSanBay()}">${f.getTenSanBay()}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="email"><spring:message code="lable.search.To" text="Sân bay đến"/></label> <select name="MaSanBayDen" id="MaSanBayDen"
						class="form-control">
						<c:forEach var="f" items="${ListSanBay}">
							<option value="${f.getMaSanBay()}">${f.getTenSanBay()}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="email"><spring:message code="lable.search.Departure" text="Ngày đi"/></label> <input type="date" id="NgayDi"
						class="form-control" name="NgayDi">
				</div>
				<center>
					<button class="btnCustom dobong" id="btntimve" ><spring:message code="lable.search.SearchTickets" text="Tìm vé"/></button>
                                        
				</center>
			</form>
		</div>
                        
	</div>
        
        <!--Phần show nội dung danh sách vé tìm được, phần này được lấy từ timve.jsp do Ajax trên scrip xử lý-->
        <div  id="noidung">
        </div>



	<footer> </footer>
</body>
</html>