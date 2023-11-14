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
    inputs.forEach(element => {
        element.classList.add('my-alert');
    });
}

export function removeInputAlert(input){
    input.classList.remove('my-alert');

}

export {context, ajax, sanitazeInputs};