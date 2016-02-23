$(document).ready(function(){

    $('[data-toggle="popover"]').popover();


    $("button").click(function(event){
        var content=contentEditor.serialize()["element-0"].value;
        var title= titleEditor.serialize()["element-0"].value.replace(/<(?:.|\n)*?>/gm, '');
        console.log(content);
        saveEditableContent(1,"tag1","tag2");
    });
});

function saveEditableContent(id,tag1,tag2) {
    var content=contentEditor.serialize()["element-0"].value;
    var title= titleEditor.serialize()["element-0"].value.replace(/<(?:.|\n)*?>/gm, '');
    var summary="";
    var arr = {
        _id: id,
        title: title,
        summary: summary,
        content: content,
        tag1: tag1,
        tag2: tag2
    };
    $.ajax({
        url: '/api/save',
        type: 'POST',
        data: JSON.stringify(arr),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(msg) {
            alert(msg);
        }
    });
}