var main = {
    init:function(){
        var _this = this;
        $("#btn-save").on("click", function(){
            _this.save();
        });
    },
    save:function(){
        var data = {
            title: $("#title").val(),
            author: $("#author").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charaset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert("글이 등록되었습니다요.");
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};
// main이라는 객체로 덮는 이유는 다른 .js 파일에서 같은 함수의 이름이 존재할 수 있으므로...
// 브라우저의 scope는 공용 공간으로 쓰이기 때문에 나중에 로딩된 js의 init, save가 먼저 로딩된 function들을 overwrite함
// 즉, main이라는 객체 안에서만 init()과 save()가 유효하다.
main.init();