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

    handleAjax();
});

//adauga handlere pentru fielduri ajax
function handleAjax(){
    $(".ajax").change(function(){

        var val = $(this).val();
        var id = $(this).attr('id');
        var data = {};
        data[id] = val;

        $.post(getUrl(this), data,
            function(result){
                //$("#asigType").html(result);
                replaceFields (result);
            });
    });
}


function getUrl (field){
    var form = $(field.form);
    return form.attr('action') + '/ajax';
}

function replaceFields (newOptions){
    $.each(newOptions, function(key, value) {
        console.log (key + ":" + value);
        if (value == null) {
            //nothing
        } else if (isField (key)){
            replaceValue (key, value);
        } else {
            replaceOptions(key, value)
        }
    });
}

function replaceValue (key, value){
    //TODO de verificat
    var field = $("#" + name);
    field.val(value);
}


function isField (key){
    return !key.endsWith ("List");
}

function getFieldName (nameList){
    return  nameList.substring(0, nameList.search("List"));
}

function replaceOptions (nameList, newOptions){
    var name = getFieldName(nameList);
    var field = $("#" + name);
    field.empty(); // remove old options
    $.each(newOptions, function(index, value) {
        field.append($("<option></option>")
            .attr("value", value.value)
            .text(value.text));
    });
}



//-----------paginare-----------------------------
function doSubmit() {
    $('input[type="submit"]:first').click();
}

function pagePrev() {
    var page = $('[name="page"]');
    if (parseInt(page.val()) > 0) {
        page.val(parseInt(page.val()) - 1);
    } else {
        page.val(0);
    }
    doSubmit();
}

function pageNext() {
    var page = $('[name="page"]');
    page.val(parseInt(page.val()) + 1);
    doSubmit();
}
//---------------------------------------------------