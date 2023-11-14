
import { context } from "./util.js";
import { verifyUsernameAlreadyExistsServ, criarContaServ } from "./service/criarContaService.js"

function verifyUsernameAlreadyExists() {

    var username =  document.getElementById('username').value;

    verifyUsernameAlreadyExistsServ(username).then(notExists => {
        if (notExists) {
            nextStep();
        }
        else{
            treatCssError();
        }
    }).catch(error => {
    });

}


function nextStep() {

    document.getElementById("step-two").style.display = "block";
    document.getElementById("step-one").style.display = "none";
}

function backStep() {
    document.getElementById("name").value ="";
    document.getElementById("cpf").value ="";
    document.getElementById("email").value="";
    document.getElementById("telephone").value;
    document.getElementById("step-two").style.display = "none";
    document.getElementById("step-one").style.display = "block";
}

function treatCssError() {
    var alert = document.getElementById("alert-text");
    alert.style.visibility = "visible";
    document.getElementById("username").classList.add("my-alert");
}
function redirectLogin() {
    window.location.href = context + "/turing-bank";
}

function modalAccountCreated(data) {


    document.getElementById("name-account-modal").innerHTML = "<strong>Nome do Cliente: </strong>" + data.name;
    document.getElementById("cpf-account-modal").innerHTML = "<strong>CPF: </strong>" + data.cpf;
    document.getElementById("telephone-account-modal").innerHTML = "<strong>Telefone: </strong>" + data.telephone;
    document.getElementById("email-account-modal").innerHTML = "<strong>Email: </strong>" + data.email;
    document.getElementById("number-account-modal").innerHTML = "<strong>Número da Conta: </strong>" + data.number;
    document.getElementById("openingDate-account-modal").innerHTML = "<strong>Data de Abertura: </strong>" + data.openingDate;
    document.getElementById("type-account-modal").innerHTML = "<strong>Tipo de Conta: </strong>Corrente";




    var meuModal1 = new bootstrap.Modal(document.getElementById('modalAccountCreated'));
    meuModal1.show();
}
function criarConta(){

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const name = document.getElementById("name").value;
    const cpf = document.getElementById("cpf").value;
    const email = document.getElementById("email").value;
    const telephone = document.getElementById("telephone").value;
    
    const data = {
        cpf: cpf,
        name: name,
        telephone: telephone,
        email: email,
        typeAccount: "CURRENT",
        login: {
            username: username,
            password: password
        }
    };
    criarContaServ(data).then(data => {

        console.log(data);
        modalAccountCreated(data);
    });
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


    document.getElementById("next-step").addEventListener("click", verifyUsernameAlreadyExists);
    document.getElementById("criar-conta").addEventListener("click", criarConta);
    document.getElementById("back-step").addEventListener("click", backStep);

    

});


