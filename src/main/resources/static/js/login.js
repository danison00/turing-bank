import { telaDeposito, login } from "./service/loginService.js";
import { ajax, context } from "./util.js";



function log() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    
    var data = {
        username: username,
        password: password
    };
    
    login(data).then(response =>{


        if(response){
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

    }).catch(error =>{
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
    document.getElementById("link-criar-conta").href = context +"/criar-conta";
    console.log(context);


    document.getElementById("button-login").addEventListener("click", log);


});