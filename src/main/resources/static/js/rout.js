import { backHome, openViewUserData, openViewExtrato } from "./dom/home.js";

function manipularRota() {
  const rota = window.location.hash;
  console.log(rota);

  switch (rota) {
    case "":
      backHome();
      break;
    case "/data-user":
      openViewUserData();
      break;
    case "/history":
      openViewExtrato();
      break;
    

    default:
      break;
  }

}

// Adiciona um ouvinte de eventos para manipular mudanças na barra de endereços
window.addEventListener('hashchange', manipularRota);