let index = {
	init: function() {
		$("#btn-save").on("click", () => { // 화살표 함수는 this를 바인딩하기 위해서!!
			this.save();
		});
		
		$("#btn-update").on("click", () => { // 화살표 함수는 this를 바인딩하기 위해서!!
					this.update();
				});
		
	},
	
	save: function() {
			//alert("user의 save함수 호출됨");
			let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val(),
			};
			
			$.ajax({
				// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data:JSON.stringify(data), // http body데이터
			contentType:"application/json; charset=utf-8", //body 데이터가 ㅓㅇ떤 타입인지 (MIME)
			dataType:"json" //응답
			})	.done(function(resp){
					if(resp.status === 500){
						alert("회원가입에 실패하였습니다.");
					}else{
						alert("회원가입이 완료되었습니다.");
						location.href = "/";
					}

				}).fail(function(error){
						alert(JSON.stringify(error));
				}); // ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert요청!!
	
	},
	
	update: function() {
				//alert("user의 save함수 호출됨");
				let data = {
					id: $("#id").val(),
					username: $("#username").val(),
					password: $("#password").val(),
					email: $("#email").val(),
				};
				
				console.log(data);
				
				$.ajax({
					// 회원가입 수행 요청
				type: "PUT",
				url: "/user",
				data:JSON.stringify(data), // http body데이터
				contentType:"application/json; charset=utf-8", //body 데이터가 ㅓㅇ떤 타입인지 (MIME)
				dataType:"json" //응답
				}).done(function(resp) {
					alert("회원수정이 완료되었습니다.");
					//console.log(resp);			
					location.href="/";
				}).fail(function(error) {
					alert(JSON.stringify(error));
					
				}); // ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert요청!!
		
		},
	
	
	
	
}

index.init();

