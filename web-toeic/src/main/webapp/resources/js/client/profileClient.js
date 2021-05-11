$(document).ready(function() {
	$('#tabs a').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	})

	// add image
	$(document).on('click', '#btnAddNewImage', function (event) {
		var formData = new FormData();
		var file_image = $('#file_Image')[0].files[0];
		formData.append("file_image", file_image);

		$.ajax({
			data: formData,
			type: 'POST',
			url : "http://localhost:8080/webtoeic/register/save-image",
			enctype: 'multipart/form-data',
			contentType: false,
			cache: false,
			processData: false,
			success: function (data) {
				$('#info-success').text("Thêm mới anh thành công");
			},
			error: function (e) {
				alert("error");
				console.log("ERROR: ", e);
			}

		});
	});

	$(document).on('click', '#btnXacNhanDoiMK', function(event) {
		event.preventDefault();
		removeElementsByClass("error");
		ajaxPostChangePass();
	});

	function ajaxPostChangePass() {
		// PREPATEE DATA
		var data = $('.formDoiMatKhau').serializeFormJSON();
		// do post
		$.ajax({
			async : false,
			type : "POST",
			contentType : "application/json",
			url : "/webtoeic/api/admin/profile/doiMatKhau",
			data : JSON.stringify(data),
			success : function(response) {
				if (response.status == "success") {
					$('#doiMKModal').modal('hide');
					alert("Đổi mật khẩu thành công. Bạn phải đăng nhập lại để xác nhận");
					location.href = "/webtoeic/logout";
				} else {
					$('input').next().remove();
					$.each(response.errorMessages, function(key, value) {
						$('input[name=' + key + ']').after('<span class="error" style="color:red; font-size: 14px; margin-left:5px">' + value + '</span>');
					});
				}
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
	}

	(function($) {
		$.fn.serializeFormJSON = function() {

			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
	})(jQuery);

	function removeElementsByClass(className) {
		var elements = document.getElementsByClassName(className);
		while (elements.length > 0) {
			elements[0].parentNode.removeChild(elements[0]);
		}
	}
})
