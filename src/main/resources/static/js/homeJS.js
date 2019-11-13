
function multi(val)
{
    document.getElementById("d").value = document.getElementById("d").value * val;
}

function add(val){
    document.getElementById("d").value = val;
}

function submitForm() {
    var form = document.getElementsByName('nick')[0];
    form.submit();
    form.reset();
    return false;
}

