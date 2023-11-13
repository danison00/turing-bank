function telaDeposito(){
    window.location.href = context+"/deposito";
}

function ajax(url, metodo, corpo) {
   
    const config = {
      method: metodo || 'GET',
      headers: {'Content-Type': 'application/json'},
      body: corpo || null,
    };
  
    return fetch(url, config)
      .then(resposta => {
        if (!resposta.ok) {
          throw new Error(`Erro HTTP! Código: ${resposta.status}`);
        }
        return resposta;
      })
      .catch(erro => {
        console.error('Ocorreu um erro:', erro);
      });
  }