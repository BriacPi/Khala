$(document).ready(function(){

    $('[data-toggle="popover"]').popover();


    $("button").click(function(event){
        var content=contentEditor.serialize()["element-0"].value;
        var title= titleEditor.serialize()["element-0"].value.replace(/<(?:.|\n)*?>/gm, '');
        console.log(content);
        $.post("demo_test_post.asp",
            {
                name: "Donald Duck",
                city: "Duckburg"
            },
            function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
            });
    });
});