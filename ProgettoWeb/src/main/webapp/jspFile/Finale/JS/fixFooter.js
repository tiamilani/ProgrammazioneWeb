$(window).resize(function(){
    var altezza = $('body').height();
    var schermo = $(window).height();
    var altezzaFooter = $('#footer').height();

    if (altezza >= schermo){
        $('#footer').css({position: "relative"});
    } else if((altezza + altezzaFooter) >= schermo){
        $('#footer').css({position: "relative"});
    } else{
        $('#footer').css({position: "absolute"});
    }
});

