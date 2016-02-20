$(function () {

    $("#like-auth").click(function (event) {

        if ($(this).find($(".fa")).hasClass('fa-heart')) {
            $(this).find($(".fa")).removeClass('fa-heart').addClass('fa-heart-o');
            $(this).next().slideDown(200);
        }
        else if ($(this).find($(".fa")).hasClass('fa-heart-o')) {

            $(this).find($(".fa")).removeClass('fa-heart-o').addClass('fa-heart');
            $("#navi ul li div").slideUp(200);
        }

        //$("#navi ul li div").slideUp(200);

    });
});