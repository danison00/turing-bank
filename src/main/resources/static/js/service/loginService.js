import { context } from "../util.js";

function telaDeposito() {
  window.location.href = context + "/deposito";
}

function login(data) {
 

  return fetch(context + '/api-public/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(data)
  })
    .then(response => {

     
        if (response.ok) {

         
          document.location.href = context + '/home';
         // return response.json();
        } else {
          
         return response.json();
         
        }
    

    })
    .catch(error => {

      error.then(response=>{
       
        return  response;
      })

    });
}


export { telaDeposito, login };