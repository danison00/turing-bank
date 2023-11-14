let context = "https://turing-bank.onrender.com";

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

function sanitazeInputs(inputs){

    var inputsInvalid = [];
   
    inputs.forEach(element => {
        if(element.value === "" || element.value === null){
            inputsInvalid.push(element);

        }

    });
    return inputsInvalid;

}

export function alertInputEmpty(inputs){

    var style = document.createElement("style");
    style.appendChild(document.createTextNode(".my-alert { border: 1px solid red; }"));
    document.head.appendChild(style);

    inputs.forEach(element => {
        element.classList.add("my-alert");   //classList.add('my-alert');
    });
}

export function removeInputAlert(inputs){

    inputs.forEach(element => {
        element.classList.remove('my-alert');
    });

   

}

export {context, ajax, sanitazeInputs};