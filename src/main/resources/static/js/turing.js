
import { context } from "./util.js"

document.getElementById("redirect-login").addEventListener("click", redirectLogin);


function redirectLogin(){
    window.location.href = context+"/login"
}