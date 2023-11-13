const context = "http://192.168.0.105:8080";



document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('password').addEventListener("click", function () {
        document.getElementById('password').classList.remove('alert');
        document.getElementById('username').classList.remove('alert');
        document.getElementById('alert-text').style.visibility = "hidden";
    });
    document.getElementById('username').addEventListener("click", function () {
        document.getElementById('password').classList.remove('alert');
        document.getElementById('username').classList.remove('alert');
        document.getElementById('alert-text').style.visibility = "hidden";
    });

});

function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;


    var data = {
        username: username,
        password: password
    };

    console.log(data);


    fetch(context + '/api-public/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {

                document.location.href = context + '/home';
                return response.json();
            } else {
                treatingCssError();
            }


        })
        .catch(error => {

            console.log(error.message);
            alert("Erro na requisição: " + error.message);
        });
}

function treatingCssError() {
    document.getElementById("password").classList.add('alert');
    document.getElementById('username').classList.add('alert');
    document.getElementById('alert-text').style.visibility = "visible";
}
