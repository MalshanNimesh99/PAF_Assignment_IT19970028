$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});



// SAVE ============================================
$(document).on("click", "#btnSave", function() {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation------------------- 
	var status = validateEmployerForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	$("#formEmployer").submit();
});



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function() {
	$("#hidEmployerIDSave").val($(this).closest("tr").find('#hidEmployerIDUpdate').val());
	$("#nic").val($(this).closest("tr").find('td:eq(0)').text());
	$("#name").val($(this).closest("tr").find('td:eq(1)').text());
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#dept").val($(this).closest("tr").find('td:eq(3)').text());
	$("#contact").val($(this).closest("tr").find('td:eq(4)').text());
});


// Delete============================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "EmployersAPI",
		type : "DELETE",
		data : "empid=" + $(this).data("empid"),
		dataType : "text",
		complete : function(response, status) {
			onEmployerDeleteComplete(response.responseText, status);
		}
	});
});



// CLIENT-MODEL==============================================================
function validateEmployerForm() {
	// CODE
	if ($("#nic").val().trim() == "") {
		return "Insert Employer nic.";
	}
	// NAME
	if ($("#name").val().trim() == "") {
		return "Insert Employer Name.";
	}
	
	if ($("#address").val().trim() == "") {
		return "Insert Employer address.";
	}
	
	if ($("#dept").val().trim() == "") {
		return "Insert Employer department.";
	}
	
	if ($("#contact").val().trim() == "") {
		return "Insert Employer contact.";
	}
	
	return true;
}

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateEmployerForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidEmployerIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "EmployersAPI",
			type: type,
			data: $("#formEmployer").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onEmployerSaveComplete(response.responseText, status);
			}
		});
});


function onEmployerSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divEmployersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidEmployerIDSave").val("");
	$("#formEmployer")[0].reset();
}



function onEmployerDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divEmployersGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}