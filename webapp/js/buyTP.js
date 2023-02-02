$('#rentCheck').click(function() {
    $("#rentCarArea").slideToggle(this.checked);
});

function radioDeselection(){
        for(const element of document.getElementsByName('carid')){
            element.checked = false;
        }

}