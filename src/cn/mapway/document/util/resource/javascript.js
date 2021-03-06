// 接口调用 ${title}
function ${name}()
{
	this.basepath='${basepath}';
};
${name}.prototype.setBase=function (path)
{
	this.basepath=path;
};
${name}.prototype.http_post = function(url, data, handler,errorhandler) {
	var pa={
			url:url,
			type:"POST",
			contentType: "application/json; charset=utf-8",
			data:$.toJSON(data),
			success : function(json) {
				handler(json);
			},
			error : function(er) {
				 if(errorhandler!=null)
					 {
					 	errorhandler(er);
					 }
				 else
					 {
					 alert(er);
					 }
			}
		};
	$.ajax(pa);
};

${name}.prototype.http_get = function(url, data, handler,errorhandler) {
	$.ajax({
		url : url,
		type : "GET",
		dataType : 'text',
		cache : false,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		data :$.toJSON(data),
		success : function(json) {
			handler(json);
		},
		error : function(er) {
			 if(errorhandler!=null)
			 {
			 	errorhandler(er);
			 }
		 else
			 {
			 alert(er);
			 }
		}
	});
};

${methods} 

var S=new ${name}();
