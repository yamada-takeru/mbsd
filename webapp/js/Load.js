$(window).on('load', function() {
    $(".page-loader").delay(1000).fadeOut(600);
});

setTimeout('stoploading()', 10000);

function stoploading() {
    $(".page-loader").fadeOut('fast');
}