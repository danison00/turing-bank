import { loginServ } from "../service/loginService.js";
import { context, sanitazeInputs, alertInputEmpty, removeInputAlerts } from "../util.js";



function login() {

    var username = document.getElementById("username");
    var password = document.getElementById("password");

    const inputsInvalids = sanitazeInputs([username, password]);

    if (inputsInvalids.length > 0) {
        alertInputEmpty(inputsInvalids);
        return;
    }

    var data = {
        username: username.value,
        password: password.value
    };

    loginServ(data).then(response => {

        if (response) {
            console.log(response);
            treatingCssError();
        }

    }).catch(error => {
        console.log(error);
    });

}

function treatingCssError() {
    
    alertInputEmpty([ document.getElementById("password"), document.getElementById('username')]);
    document.getElementById('alert-text').style.visibility = "visible";
}

document.addEventListener('DOMContentLoaded', function () {
    
    const inputPassword = document.getElementById('password');
    const inputUsername =  document.getElementById('username');
    const alertTextMessage =  document.getElementById('alert-text');
    inputPassword.onclick = () => {
        removeInputAlerts([inputPassword, inputUsername]);
        alertTextMessage.style.visibility = "hidden";
    };
    username.onclick = () => {
        removeInputAlerts([inputPassword, inputUsername]);
        alertTextMessage.style.visibility = "hidden";
    };

    document.getElementById("link-criar-conta").href = context + "/criar-conta";
    document.getElementById("button-login").onclick = login;
    document.getElementById('btn-deposito').onclick = () => {
        window.location.href = context+"/deposito";
    };


});