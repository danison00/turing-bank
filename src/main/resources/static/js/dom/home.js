import { findData } from "../service/homeService.js";
import { context } from "../util.js"
import { generateViewUserData } from "./pagesGenerator.js";

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
    window.location.hash = "deposito";
   // window.location.href = context + "/deposito";
}

function logout() {
    window.location.href = context + "/api-public/logout";

}

export function openViewUserData() {

    window.location.hash = "/data-user";
    addViewInCard2(generateViewUserData(accountData));


}

export function openViewExtrato() {
    window.location.hash = "/history";

    document.getElementById("card2").checked = true;
   // addViewInCard2([]);

}

function addViewInCard2(tags) {

    const divCard2 = document.querySelector(".card2");
    while (divCard2.firstChild) {
        divCard2.removeChild(divCard2.firstChild);
    }

    tags.forEach(element => {
        divCard2.appendChild(element);
    })
    document.getElementById("card2").checked = true;

}
export function backHome() {
    window.location.hash = "";
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