$(document).ready(function () {

    $('[data-toggle="popover"]').popover();

    //$("#publishButton").hide();

    $("#publishButton").click(function (event) {
        publish();
    });


    //$("#title-editable").keyup(function () {
    //    if ($(this).val()) {
    //        $("#publishButton").show();
    //    }
    //    else {
    //        //if ($("#content-editable").val()) {
    //        //    $("#publishButton").show();
    //        //}
    //        //else {
    //        //    $("#publishButton").hide();
    //        //}
    //    }
    //});

    //$("#content-editable").keyup(function () {
    //    if ($(this).val()) {
    //        $("#publishButton").show();
    //    }
    //    else {
    //        if ($("#title-editable").val()) {
    //            $("#publishButton").show();
    //        }
    //        else {
    //            $("#publishButton").hide();
    //        }
    //    }
    //});
});

function publish() {
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


function saveEditableContent(tag1, tag2) {
    var content = contentEditor.serialize()["element-0"].value;
    var title = titleEditor.serialize()["element-0"].value.replace(/<(?:.|\n)*?>/gm, '');
    var summary = "";
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
        success: function (msg) {
            alert(msg);
        }
    });
}