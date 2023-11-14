
import {context} from "./util.js"


export let accountData;


export function findData() {


    ajax(context + '/api/my-account', "GET", null)
        .then(dados => {
            accountData = dados;
            console.log('Dados recebidos:', dados);
            document.getElementById("nameUser").innerText = "Olá, " + String(dados.name).split(" ")[0] + "!";

        });
}


function transferir() {

    var numberAccount = document.getElementById("numberAccount").value;
    var value = document.getElementById("valueTranfer").value;
    var fav = false;
    if (document.getElementById("fav").checked) fav = true;
    var data = {

        "accountDestination": numberAccount,
        "value": value,
        "saveDestination": fav

    }
    fetch(context + '/api/transaction/transfer', {
        method: 'POST', // ou 'POST', 'PUT', 'DELETE', etc., dependendo do tipo de requisição que você deseja fazer

        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(response => {

            if (response.ok) {
                alert("tranferência realizada com sucesso")
            }
            else {


            }


        })
        .catch(error => {
            // Aqui você trata erros da requisição
        });


}
function buscarDados() {



    fetch(context + '/api/my-account', {
        method: 'GET', // ou 'POST', 'PUT', 'DELETE', etc., dependendo do tipo de requisição que você deseja fazer

    })
        .then(response => {

            if (response.redirected) {
                window.location.href = response.url;
            }
            else {

                return response.json();



            }


        })
        .then(data => {



            console.log(data);
        })
        .catch(error => {
            // Aqui você trata erros da requisição
        });
}
export function logoutServ() {
    fetch(context + '/api-public/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (response.ok) {

                if (response.redirected)
                    window.location.href = response.url;


            }

        })
        .catch(error => {

            alert("Erro na requisição: " + error.message);
            console.log(error.message);
        });
}


function ajax(url, metodo, corpo) {

    const config = {
        method: metodo || 'GET',
        headers: { 'Content-Type': 'application/json' },
        body: corpo || null,
    };

    return fetch(url, config)
        .then(resposta => {
            if (!resposta.ok) {
                throw new Error(`Erro HTTP! Código: ${resposta.status}`);
            }
            return resposta.json();
        })
        .catch(erro => {
            console.error('Ocorreu um erro:', erro);
        });
}


