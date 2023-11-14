import { telaDeposito, loginSer } from "./service/loginService.js";
import { ajax, context, sanitazeInputs, alertInputEmpty } from "./util.js";



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

    loginSer(data).then(response => {



        if (response) {
            console.log(response);
            treatingCssError();
        }

        // if(response.status === "200"){

        //     console.log("logado");
        // }else{
        //     response.json().then(error=>{
        //         console.log(error.message);
        //     });
        // }

    }).catch(error => {
        console.log(error);
    });

}

function treatingCssError() {
    document.getElementById("password").classList.add('alert');
    document.getElementById('username').classList.add('alert');
    document.getElementById('alert-text').style.visibility = "visible";
}

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('password').addEventListener("click", function () {
        document.getElementById('password').classList.remove('alert');
        document.getElementById('username').classList.remove('alert');
        document.getElementById('alert-text').style.visibility = "hidden";
    });
    document.getElementById('username').addEventListener("click", function () {
        document.getElementById('password').classList.remove('alert');
        document.getElementById('username').classList.remove('alert');
        document.getElementById('alert-text').style.visibility = "hidden";
    });
    document.getElementById("link-criar-conta").href = context + "/criar-conta";

    document.getElementById("button-login").addEventListener("click", login);

    document.getElementById('username').addEventListener("change", function () {
        document.getElementById('username').classList.remove('my-alert');
    });
    document.getElementById('password').addEventListener("change", function () {
        document.getElementById('password').classList.remove('my-alert');
    });
    document.getElementById('btn-deposito').addEventListener("click", function () {
        window.location.href = context+"/deposito";
    });


});