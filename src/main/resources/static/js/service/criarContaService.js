import { ajax, context } from "../util.js";


function verifyUsernameAlreadyExistsServ(username) {


    return ajax(context + "/api/username-not-exists/" + username, "GET", null).then(response => {
        if (response.ok) {

            return true;
        }
        else {
           return false;
        }
    }).catch(erro =>{
        alert("Aconteceu um erro no servidor");
    });



}

function criarContaServ(data) {

   return ajax(context + "/api-public/account", "POST", data).then(response => {
        if (response.ok) {

            return response.json();
        }
        else {
            alert("erro");
        }
    });
}


export { verifyUsernameAlreadyExistsServ, criarContaServ };