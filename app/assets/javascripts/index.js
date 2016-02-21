$(function () {

    $("#like-auth").click(function (event) {
        var requestLikeUnlike=jsRoutes.controllers.ContentController.likeUnlike($("#post").attr("articleid"));
        requestLikeUnlike.ajax({
            success: function(result) {
            },
            failure: function(err) {
            }
        });

        if ($(this).find($(".fa")).hasClass('fa-heart')) {
            $(this).find($(".fa")).removeClass('fa-heart').addClass('fa-heart-o');
            $(this).next().slideDown(200);
            $("#nblikes").html(parseInt($("#nblikes").html(), 10)-1);
        }
        else if ($(this).find($(".fa")).hasClass('fa-heart-o')) {
            $(this).find($(".fa")).removeClass('fa-heart-o').addClass('fa-heart');
            $("#navi ul li div").slideUp(200);
            $("#nblikes").html(parseInt($("#nblikes").html(), 10)+1);
        }



    });
});

