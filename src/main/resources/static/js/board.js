let index = {
	init: function() {
		$("#btn-save").on("click", () => { // 화살표 함수는 this를 바인딩하기 위해서!!
			this.save();
		});
		$("#btn-delete").on("click", () => { // 화살표 함수는 this를 바인딩하기 위해서!!
			this.deleteById();
		});
		$("#btn-update").on("click", () => { // 화살표 함수는 this를 바인딩하기 위해서!!
			this.update();
		});
		$("#btn-reply-save").on("click", ()=>{ 
			this.replySave();
		});
		
	},
	
	save: function() {
			let data = {
				title: $("#title").val(),
				content: $("#content").val()
			};
			
			$.ajax({
				// 회원가입 수행 요청
			type: "POST",
			url: "/api/board",
			data:JSON.stringify(data), // http body데이터
			contentType:"application/json; charset=utf-8", //body 데이터가 ㅓㅇ떤 타입인지 (MIME)
			dataType:"json" //응답
			}).done(function(resp) {
				alert("글쓰기가 완료되었습니다.");
				location.href="/";
			}).fail(function(error) {
				alert(JSON.stringify(error));
				
			}); // ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert요청!!
	
	},
	
	deleteById: function() {
		let id = $("#id").text();
				$.ajax({
					// 회원가입 수행 요청
				type: "DELETE",
				url: "/api/board/"+id,
				dataType:"json" //응답
				}).done(function(resp) {
					alert("삭제가 완료되었습니다.");
					location.href="/";
				}).fail(function(error) {
					alert(JSON.stringify(error));
					
				}); // ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert요청!!
		
		},
		
		update: function() {
					let id = $("#id").val();
			
					let data = {
						title: $("#title").val(),
						content: $("#content").val()
					};
					
					console.log(id, data);
					
					
					$.ajax({
						// 회원가입 수행 요청
					type: "PUT",
					url: "/api/board/"+id,
					data:JSON.stringify(data), // http body데이터
					contentType:"application/json; charset=utf-8", //body 데이터가 ㅓㅇ떤 타입인지 (MIME)
					dataType:"json" //응답
					}).done(function(resp) {
						alert("글수정이 완료되었습니다.");
						location.href="/";
					}).fail(function(error) {
						alert(JSON.stringify(error));
						
					}); // ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert요청!!
			
			},
			
			replySave: function(){
			    let data = {
			        userId: $("#userId").val(),
			        boardId: $("#boardId").val(),
			        content: $("#reply-content").val()
			    };

			    if (!data.content.trim()) {
			        alert("댓글 내용을 입력해주세요.");
			        return;
			    }

			    console.log("데이터 : ", data);
			    
			    $.ajax({ 
			        type: "POST",
			        url: `/api/board/${data.boardId}/reply`,
			        data: JSON.stringify(data),
			        contentType: "application/json; charset=utf-8",
			        dataType: "json"
			    }).done(function(resp){
			        alert("댓글작성이 완료되었습니다.");
			        location.href = `/board/${data.boardId}`;
			    }).fail(function(error){
			        alert(JSON.stringify(error));
			    }); 
			},

			
			replyDelete : function(boardId, replyId){
						$.ajax({ 
							type: "DELETE",
							url: `/api/board/${boardId}/reply/${replyId}`,
							dataType: "json"
						}).done(function(resp){
							alert("댓글삭제 성공");
							location.href = `/board/${boardId}`;
						}).fail(function(error){
							alert(JSON.stringify(error));
						}); 
					},
	
	
	
	
}

index.init();

