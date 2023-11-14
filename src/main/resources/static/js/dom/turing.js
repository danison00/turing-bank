
import { context } from "../util.js"

//document.getElementById("redirect-login").addEventListener("click", redirectLogin);

document.getElementById("redirect-login").onclick = redirectLogin;


function redirectLogin(){
    window.location.href = context+"/login"
}