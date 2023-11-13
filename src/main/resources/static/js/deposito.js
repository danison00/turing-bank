function mascaraMoeda(event) {

    var input = event.target;
    var value = input.value.replace(/\D/g, ''); // Remove todos os não-dígitos

    // Formata o valor como moeda brasileira
    var formattedValue = "R$ " + (value / 100).toLocaleString("pt-BR", {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });

    input.value = formattedValue;
}

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('value').addEventListener('input', mascaraMoeda);

});