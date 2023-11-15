
import { context, sanitazeInputs, alertInputEmpty, removeInputAlerts } from "../util.js";
import { verifyUsernameAlreadyExistsServ, criarContaServ } from "../service/criarContaService.js"

function verifyUsernameAlreadyExists() {

    var username = document.getElementById('username').value;

    verifyUsernameAlreadyExistsServ(username).then(notExists => {
        if (notExists) {
            nextStep();
        }
        else {
            treatCssError();
        }
    }).catch(error => {
    });

}


function nextStep() {

    const username = document.getElementById("username");
    const password = document.getElementById("password");

    var inputsEmpty = sanitazeInputs([username, password]);

    if (inputsEmpty.length > 0) {
        alertInputEmpty(inputsEmpty);
        return;
    }

    document.getElementById("step-two").style.display = "block";
    document.getElementById("step-one").style.display = "none";
}

function backStep() {
    document.getElementById("name").value = "";
    document.getElementById("cpf").value = "";
    document.getElementById("email").value = "";
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
function criarConta() {

    
    var name = document.getElementById("name");
    var cpf = document.getElementById("cpf");
    var email = document.getElementById("email");
    var telephone = document.getElementById("telephone");


    var inputsEmpty = sanitazeInputs([name, cpf, email, telephone]);

    if (inputsEmpty.length > 0) {
        alertInputEmpty(inputsEmpty);
        return;
    }


    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    name = name.value;
    cpf = cpf.value;
    email = email.value;
    telephone = telephone.value;

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
    
    document.getElementById('username').addEventListener("change", function () {
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

    var username = document.getElementById("username");
    username.addEventListener("change", removeInputAlerts([username]));

    var password = document.getElementById("password");
    password.addEventListener("change", function(){
        password.classList.remove("my-alert");
    }); 
    
    document.getElementById("name").addEventListener("change", function(){
        document.getElementById("name").classList.remove("my-alert");
    }); 
    document.getElementById("cpf").addEventListener("change", function(){
        document.getElementById("cpf").classList.remove("my-alert");
    });
    document.getElementById("email").addEventListener("change", function(){
        document.getElementById("email").classList.remove("my-alert");
    });
    document.getElementById("telephone").addEventListener("change", function(){
        document.getElementById("telephone").classList.remove("my-alert");
    }) ;



});


