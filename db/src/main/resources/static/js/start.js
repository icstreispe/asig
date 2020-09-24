$(function () {
    $(".date")
        .each(function (index) {
            //var d = $.datepicker.parseDate('dd.mm.yyyy', $(this).val());
            console.log(index + ": " + $(this).val());
        })
        .datepicker({"dateFormat": "dd.mm.yy",
        })
    //.datepicker("setDate", $.datepicker.parseDate('dd.MM.yyyy', $(this).val()))
    ;


});