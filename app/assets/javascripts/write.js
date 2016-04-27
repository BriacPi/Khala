$(document).ready(function () {

    //$("#publishButton").hide();

    $("#publishButton").popover({trigger: 'manual'}).click(function (event) {

        publish();

    });

});

function publish() {
    var content = contentEditor.serialize()["element-0"].value;
    var title = titleEditor.serialize()["element-0"].value.replace(/<(?:.|\n)*?>/gm, '');
    var summary = "";
    if (title && content) {
        //alert("I am an alert box 1!");
        var arr = {
            _id: id,
            title: title,
            summary: summary,
            content: content
            //tag1: tag1,
            //tag2: tag2
        };
        $.ajax({
            url: '/api/publish/public',
            type: 'POST',
            data: JSON.stringify(arr),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            success: function (msg) {
                window.location.href = '/article/id/' + id.toString();
            }
        });
    }
    else {
        //alert("No empty content can be published!");
        $("[data-toggle='popover']").popover('show');
        //$('[data-toggle="popover"]').popover("hide", {animation:true ,delay: {show: 500, hide: 1500}});

    }

}


function saveEditableContent(tag1, tag2) {
    var content = contentEditor.serialize()["element-0"].value;
    var title = titleEditor.serialize()["element-0"].value.replace(/<(?:.|\n)*?>/gm, '');
    var summary = "";
    var arr = {
        _id: id,
        title: title,
        summary: summary,
        content: content
        //tag1: tag1,
        //tag2: tag2
    };
    $.ajax({
        url: '/api/save',
        type: 'POST',
        data: JSON.stringify(arr),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (msg) {
            alert(msg);
        }
    });
}