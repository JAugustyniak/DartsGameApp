
function multi(val)
{
    document.getElementById("d").value = document.getElementById("d").value * val;
}

function myFunction() {
    document.getElementById("d").value = 2;
    // alert(new Date());
}

function add(val){
    document.getElementById("d").value = val;
}

function addAndSubmit(val){
    document.getElementById("d").value = val;
    document.getElementById("throw_form").submit();
}

function submitForm() {
    var form = document.getElementsByName('nick')[0];
    form.submit();
    form.reset();
    return false;
}

