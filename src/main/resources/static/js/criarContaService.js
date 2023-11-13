
function verifyUsernameAlreadyExists() {

    return new Promise((resolve, reject) => {
        const username = document.getElementById("username").value;

        ajax(context + "/api/username-exists/" + username, "GET", null).then(response => {
            if (response.ok) {
                return resolve(false);
            }
            else {
                response.json().then(erro => {

                    reject(erro)
                });

            }
        });
    });


}

function criarConta() {

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
    ajax(context + "/api-public/account", "POST", data).then(response => {
        if (response.ok) {

            response.json().then(data => {
                modalAccountCreated(data);
            });
        }
        else {
            alert("erro");
        }
    });
}
function modalAccountCreated(data) {


    document.getElementById("name-account-modal").innerHTML = "<strong>Nome do Cliente: </strong>" + data.name;
    document.getElementById("cpf-account-modal").innerHTML = "<strong>CPF: </strong>" + data.cpf;
    document.getElementById("telephone-account-modal").innerHTML = "<strong>Telefone: </strong>" + data.telephone;
    document.getElementById("email-account-modal").innerHTML = "<strong>Email: </strong>" + data.email;
    document.getElementById("number-account-modal").innerHTML = "<strong>Número da Conta: </strong>" + data.number;
    document.getElementById("openingDate-account-modal").innerHTML = "<strong>Data de Abertura: </strong>" + data.openingDate;
    document.getElementById("type-account-modal").innerHTML = "<strong>Tipo de Conta: </strong>Corrente";



    console.log(data);

    var meuModal1 = new bootstrap.Modal(document.getElementById('modalAccountCreated'));
    meuModal1.show();
}