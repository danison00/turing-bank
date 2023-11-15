import { findData } from "../service/homeService.js";
import { context } from "../util.js"

let accountData;

findData().then(response => {

    if (response.ok) return response.json();



}).then(clientData => {
    accountData = clientData;
    console.log('Dados recebidos:', accountData);
    document.getElementById("nameUser").innerText = "Olá, " + String(clientData.name).split(" ")[0] + "!";
}).catch(error => {});

function hiddenBalance() {
    document.getElementById("nao-ver").style.display = "none";
    document.getElementById("ver").style.display = "";
    document.getElementById("balance").innerText = "R$ ****"

}
function showBalance() {
    document.getElementById("ver").style.display = "none";
    document.getElementById("nao-ver").style.display = "block";

    var balance = formattedBalance(String(accountData.balance));
    document.getElementById("balance").innerText = balance;

}
function formattedBalance(balance) {

    var valorDepoisVirgula;
    if (!balance.includes(".")) {
        valorDepoisVirgula = "00";

    } else {
        valorDepoisVirgula = balance.split(".")[1];
    }

    var aux1 = balance.split(".")[0];

    for (var i = aux1.length; i > 0; i--) {
        if (i % 3 == 0 && aux1.length - i != 0) {
            var part1 = aux1.substring(0, aux1.length - i);
            var part2 = aux1.substring(aux1.length - i);
            aux1 = part1 + "." + part2;
        }
        console.log(aux1);
    }


    return "R$ " + aux1 + "," + valorDepoisVirgula;

}
function redirectDeposit() {
    window.location.href = context + "/deposito";
}
function logout() {
    window.location.href = context + "/api-public/logout";

}

function openViewUserData() {

    const divPrincipal = document.createElement('div');
    divPrincipal.classList.add('user-card-info');

    const divRangeBlack = document.createElement('div');
    divRangeBlack.classList.add('range-black');
    const divContentInfo = document.createElement('div');
    divContentInfo.classList.add('content-info');
    const informacoes = [
        'Nome: ' + accountData.name,
        'Cpf: ' + accountData.cpf,
        'Email: ' + accountData.email,
        'Telefone: ' + accountData.telephone,
        'Numero da conta: ' + accountData.number,
        'Data de abertura: ' + accountData.openingDate,
        'Tipo de conta: Corrente'
    ];

    informacoes.forEach(info => {
        const paragrafo = document.createElement('p');
        const span = document.createElement('span');
        span.textContent = info;
        paragrafo.appendChild(span);
        divContentInfo.appendChild(paragrafo);
    });
    const divContainerBtn = document.createElement('div');
    divContainerBtn.classList.add('container-btn');

    const btnVoltar = document.createElement('button');
    btnVoltar.classList.add('btn-back-home');
    btnVoltar.id = 'btn-back-home';
    btnVoltar.textContent = 'Voltar';
    btnVoltar.onclick = backHome;
    divContainerBtn.appendChild(btnVoltar);

    divPrincipal.appendChild(divRangeBlack);
    divPrincipal.appendChild(divContentInfo);
    addViewInCard2([divPrincipal, divContainerBtn]);


}

function openViewExtrato(){

    document.getElementById("card2").checked = true;
  //  addViewInCard2([]);

}



function addViewInCard2(html) {

    const divCard2 = document.querySelector(".card2");
    while (divCard2.firstChild) {
        divCard2.removeChild(divCard2.firstChild);
    }

    html.forEach(element => {
        divCard2.appendChild(element);
    })
    document.getElementById("card2").checked = true;

}
function backHome() {
    document.getElementById("card1").checked = true;
}


document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("nao-ver").addEventListener("click", hiddenBalance);
    document.getElementById("ver").addEventListener("click", showBalance);
    document.getElementById("item-depositar").addEventListener("click", redirectDeposit);
    document.getElementById("btn-logout").addEventListener("click", logout);

    document.getElementById("btn-user-data").onclick = openViewUserData;
    document.getElementById("btn-extrato").onclick = openViewExtrato;


})