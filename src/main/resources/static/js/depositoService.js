let context = "http://192.168.0.105:8080";

function depositCheckData() {
    var accountNumber = document.getElementById("accountNumber").value;
    var value = document.getElementById("value").value;
    ajax(context + "/api/transaction/deposit/check?accountNumber=" + accountNumber, "GET", null).then(response => {
        if (!response.ok) {

            response.json().then(data => {
                treatingCssError(data.message);

            });

        }
        if (response.ok) {
            response.json().then(data => {
                abrirModalConfirm(data);
            });
        }
    });
}

function deposito() {
    var accountNumber = document.getElementById("accountNumber").value;
    var value = document.getElementById("value").value.replace("R$ ", '').replace(",",".").replace(".", "");
  

    console.log(value);
    var meuModal1 = new bootstrap.Modal(document.getElementById('modalConfirmDeposit'));
    meuModal1.hide();


    const data = {
        "accountNumber": accountNumber,
        "value": value,
    };

    ajax(context + '/api/transaction/deposit', "POST", data).then(response => {


        if (!response.ok) {
            response.json().then(data => {

                abrirModalDepositFinalize("ERRO: "+ data.message);

            });
        }
        if (response.ok) {

            abrirModalDepositFinalize("Depósito realizado Com Sucesso!")
        }
    });

}

function ajax(url, metodo, corpo) {

    const body = corpo ? JSON.stringify(corpo) : null;

    const config = {
        method: metodo,
        headers: { 'Content-Type': 'application/json' },
        body: body,
    };
    console.log(body, "kkkkk");


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
function treatingCssError(message) {
    document.getElementById("accountNumber").classList.add('my-alert');
    document.getElementById('alert-text').style.visibility = "visible";
    document.getElementById('alert-text').innerText = message;

}
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('accountNumber').addEventListener("click", function () {
        document.getElementById('accountNumber').classList.remove('my-alert');
        document.getElementById('alert-text').style.visibility = "hidden";
    });


});
function abrirModalConfirm(data) {
    document.getElementById('name-account-modal').innerText = "Nome: " + data.name;
    document.getElementById('number-account-modal').innerText = "Número da conta: " + data.accountNumber;
    document.getElementById('value-deposit-modal').innerText = "Valor do depósito: " + document.getElementById("value").value ;

    var meuModal = new bootstrap.Modal(document.getElementById('modalConfirmDeposit'));
    meuModal.show();
}
function abrirModalDepositFinalize(message) {

    document.getElementById("modal-finalyze-message").innerText = message;
    var meuModal = new bootstrap.Modal(document.getElementById('modalDepositFinalize'));
    meuModal.show();
}