


function nextStep() {

    verifyUsernameAlreadyExists().then(exists => {
        if (!exists) {
            document.getElementById("step-two").style.display = "block";
            document.getElementById("step-one").style.display = "none";
        }
    }).catch(error => {
        treatCssError();
    });
}

function backStep() {
    document.getElementById("step-two").style.display = "none";
    document.getElementById("step-one").style.display = "block";
}

function treatCssError() {
    var alert = document.getElementById("alert-text");
    alert.style.visibility = "visible";
    document.getElementById("username").classList.add("my-alert");
}
function redirectLogin() {
    window.location.href = context + "/login";
}





document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('username').addEventListener("click", function () {
        document.getElementById('username').classList.remove('my-alert');
        document.getElementById('alert-text').style.visibility = "hidden";
    });
    let mod = new bootstrap.Modal(document.getElementById("modalAccountCreated"));
    mod._element.addEventListener("hidden.bs.modal", function () {
        redirectLogin();
    });
  
});