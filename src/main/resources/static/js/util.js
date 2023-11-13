let context = "https://turing-bank.onrender.com/";

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