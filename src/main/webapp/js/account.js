var elmSubmit = document.getElementById("ID_SUBMIT");
elmSubmit.onclick = function() {
	var elmId = document.getElementById("ID_ID");
	var elmName = document.getElementById("ID_NAME");
	var elmPassword = document.getElementById("ID_PASSWORD");
	var elmTask = document.getElementById("ID_TASK");
	var canSubmit = true;
	if (elmId.value == "" || elmName.value == "" || elmPassword.value == "" || elmTask.value == "") {
		alert("不正な入力項目があります。");
		canSubmit = false;
	}
	return canSubmit;
}
