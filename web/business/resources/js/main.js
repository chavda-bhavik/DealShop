$(function(){
    $("#form-total").steps({
        headerTag: "h2",
        bodyTag: "section",
        transitionEffect: "fade",
        enableAllSteps: true,
        stepsOrientation: "vertical",
        autoFocus: true,
        transitionEffectSpeed: 500,
        titleTemplate : '<div class="title">#title#</div>',
        labels: {
            previous : 'Back Step',
            next : '<i class="zmdi next zmdi-arrow-right"></i>',
            finish : '<i class="zmdi end zmdi-check"></i>',
            current : ''
        },
//        onFinished: function() {
//          $("#regBtn").click();
//        },
    })
});
