import { context } from "./util.js";



function depositCheckDataServ(accountNumber) {

    return new Promise((resolve, reject) => {

        ajax(context + "/api/transaction/deposit/check?accountNumber=" + accountNumber, "GET", null).then(response => {

            if (!response.ok) {

                response.json().then(data => {
                    reject(data);
                });
            }
            if (response.ok) {
                response.json().then(data => {

                    resolve(data);
                });
            }
        });
    });
}

function depositoServ(data) {

    return new Promise((resolve, reject) => {

        ajax(context + '/api/transaction/deposit', "POST", data).then(response => {

            if (response.ok) {
               

                  resolve();

            }
            if (!response.ok) {

                reject();
            }
        }).catch(error =>{
            reject(error);
        });
    });

}

function ajax(url, metodo, corpo) {

    const body = corpo ? JSON.stringify(corpo) : null;

    const config = {
        method: metodo,
        headers: { 'Content-Type': 'application/json' },
        body: body,
    };


    return fetch(url, config)
        .then(resposta => {

            return resposta;
        })
        .catch(erro => {
            alert(erro.message);
        });
}
function redirectSelf() {
    location.reload();
}



export { depositCheckDataServ, depositoServ }