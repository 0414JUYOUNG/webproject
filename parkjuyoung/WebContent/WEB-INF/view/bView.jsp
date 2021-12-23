<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>  
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript">
	var xhr1 = new XMLHttpRequest();
  	var xhr2 = new XMLHttpRequest();
  	
  	var id = encodeURIComponent(document.getElementById("id").value);
	  	function replylist(){
		  var replyTable = document.getElementById("replyTable");
		  replyTable.innerHTML = "";
		  	xhr1.onreadystatechange = function() {
			  	if(this.readyState == 4 && this.status == 200) {
				  	var json = this.responseText;
				  	var list = JSON.parse(json);
					for(var i = 0 in list) {
						var row = replyTable.insertRow();
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						cell1.innerHTML = list[i].num;
						cell2.innerHTML = list[i].reply;
				  	}
			  	}
			};
			xhr1.open("POST", "rlist.rp", true);
			xhr1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8;");
			var data = "";
			data += "id=" + id;
			xhr1.send(data);
		}
		function rinsert() {
			var reply = encodeURIComponent(document.getElementById("reply").value);
			xhr2.onreadystatechange = function() {
				if(this.readyState == 4 && this.status == 200) {
					// sadasdsa
				}
			}
		}
  	
  </script>
  <div class="container">
  	<table class="table" style="text-align:center; border: 1px sp;od #dddddd">
  		<tr>
  		<td style="background-color:#fafafa; text-align:center">댓글 : </td>
  		<td><input class="form-control" type="text" id="reply" size="80">
  		<td colspan="2"> <button class="btn btn-primary pull-right" onclick="rinsert();">한줄 댓글 작성</button>
  		</tr>
  		<table class="table" style="text=align:center; border:1px solid #dddddd">
  			<tbody id ="replyTable">
  			
  			</tbody>
  		</table>
	</div>
</body>
</html>