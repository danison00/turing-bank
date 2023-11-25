
import { backHome } from "./home.js";


export function generateViewUserData(accountData) {
    const divPrincipal = document.createElement('div');
    divPrincipal.classList.add('user-card-info');

    const divRangeBlack = document.createElement('div');
    divRangeBlack.classList.add('range-black');
    const divContentInfo = document.createElement('div');
    divContentInfo.classList.add('content-info');
    const informacoes = [
        'Nome: ' + accountData.name,
        'Cpf: ' + accountData.cpf,
        'Email: ' + accountData.email,
        'Telefone: ' + accountData.telephone,
        'Numero da conta: ' + accountData.number,
        'Data de abertura: ' + accountData.openingDate,
        'Tipo de conta: Corrente'
    ];

    informacoes.forEach(info => {
        const paragrafo = document.createElement('p');
        const span = document.createElement('span');
        span.textContent = info;
        paragrafo.appendChild(span);
        divContentInfo.appendChild(paragrafo);
    });
    const divContainerBtn = document.createElement('div');
    divContainerBtn.classList.add('container-btn');

    const btnVoltar = document.createElement('button');
    btnVoltar.classList.add('btn-back-home');
    btnVoltar.id = 'btn-back-home';
    btnVoltar.textContent = 'Voltar';
    btnVoltar.onclick = backHome;
    divContainerBtn.appendChild(btnVoltar);

    divPrincipal.appendChild(divRangeBlack);
    divPrincipal.appendChild(divContentInfo);

    return [divPrincipal, divContainerBtn]
}

export function generatedViewUserData(){
    
}