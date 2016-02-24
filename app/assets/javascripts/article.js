$(function () {

    $(".like-auth").each(function(like){
        $(this).click(function (event) {
            var requestLikeUnlike=jsRoutes.controllers.ContentController.likeUnlike($(this).attr("articleid"));
            requestLikeUnlike.ajax({
                success: function(result) {
                },
                failure: function(err) {
                }
            });

            if ($(this).find($(".fa")).hasClass('fa-heart')) {
                $(this).find($(".fa")).removeClass('fa-heart').addClass('fa-heart-o');
                $(this).next("#nblikes").html(parseInt($(this).next("#nblikes").html(), 10)-1);
            }
            else if ($(this).find($(".fa")).hasClass('fa-heart-o')) {
                $(this).find($(".fa")).removeClass('fa-heart-o').addClass('fa-heart');
                $(this).next("#nblikes").html(parseInt($(this).next("#nblikes").html(), 10)+1);
            }
        });
    });

});

