import { depositCheckDataServ, depositoServ } from "./depositoService.js";
import { context, sanitazeInputs, alertInputEmpty } from "./util.js";



function depositCheckData() {


    var accountNumber = document.getElementById("accountNumber");
    var value = document.getElementById("value");

    var inputsInvalid = sanitazeInputs([accountNumber, value]);

    if (inputsInvalid.length > 0) {
        alertInputEmpty(inputsInvalid);
        return;
    }
    var accountNumber = document.getElementById("accountNumber").value;
    var value = document.getElementById("value").value;



    var accountNumber = document.getElementById("accountNumber").value;
    var value = document.getElementById("value").value;


    depositCheckDataServ(accountNumber).then(data => {

        abrirModalConfirm({
            name: data.name,
            accountNumber: accountNumber,
            value: value
        });
    }).catch(error => {
        treatingCssError(error.message);
    });

}

function abrirModalConfirm(data) {
    document.getElementById('name-account-modal').innerText = "Nome: " + data.name;
    document.getElementById('number-account-modal').innerText = "Número da conta: " + data.accountNumber;
    document.getElementById('value-deposit-modal').innerText = "Valor do depósito: " + document.getElementById("value").value;

    var meuModal = new bootstrap.Modal(document.getElementById('modalConfirmDeposit'));
    meuModal.show();
}

function deposito() {

    var accountNumber = document.getElementById("accountNumber").value;
    var value = document.getElementById("value").value.replace("R$ ", '').replace(".", "").replace(",", ".");

    var meuModal1 = new bootstrap.Modal(document.getElementById('modalConfirmDeposit'));
    meuModal1.hide();

    depositoServ({
        "accountNumber": accountNumber,
        "value": value,
    }).then(() => {
        abrirModalDepositFinalize("Deposito realizado com sucesso!");
    }).catch(error => {
        abrirModalDepositFinalize(error.message);
    });
}
function abrirModalDepositFinalize(message) {
    document.getElementById("accountNumber").value = "";
    document.getElementById("value").value = "";
    document.getElementById("modal-finalyze-message").innerText = message;
    var meuModal = new bootstrap.Modal(document.getElementById('modalDepositFinalize'));
    meuModal.show();
}


function treatingCssError(message) {



    document.getElementById("accountNumber").classList.add('my-alert');
    document.getElementById('alert-text').style.visibility = "visible";
    document.getElementById('alert-text').innerText = message;


}




function mascaraMoeda(event) {

    var input = event.target;
    var value = input.value.replace(/\D/g, ''); // Remove todos os não-dígitos
    var formattedValue = "R$ " + (value / 100).toLocaleString("pt-BR", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });

    input.value = formattedValue;
}

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('value').addEventListener('input', mascaraMoeda);
    document.getElementById('deposit-check-data').addEventListener('click', depositCheckData);
    document.getElementById('accountNumber').addEventListener("click", function () {
        document.getElementById('accountNumber').classList.remove('my-alert');
        document.getElementById('alert-text').style.visibility = "hidden";
    });
    document.getElementById('value').addEventListener("click", function () {
        document.getElementById('value').classList.remove('my-alert');
    });
    document.getElementById('btn-deposito').addEventListener('click', deposito);
});


