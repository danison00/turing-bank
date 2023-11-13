function hiddenBalance() {
    document.getElementById("eye-slash").style.display = "none";
    document.getElementById("eye").style.display = "inline";
    document.getElementById("balance").innerText = "R$ ****"


}
function showBalance() {
    document.getElementById("eye-slash").style.display = "inline";
    document.getElementById("eye").style.display = "none";

    var balance = formattedBalance(String(accountData.balance));

    document.getElementById("balance").innerText = balance;

}
function formattedBalance(balance) {

    var valorDepoisVirgula;
    if (!balance.includes(".")) {
        valorDepoisVirgula = "00";

    } else {
        valorDepoisVirgula = balance.split(".")[1];
    }


    var aux1 = balance.split(".")[0];

    for (var i = aux1.length; i > 0; i--) {
        if (i % 3 == 0 &&  aux1.length - i != 0) {
            var part1 = aux1.substring(0, aux1.length - i);
            var part2 = aux1.substring(aux1.length - i);
            aux1 = part1 + "." + part2;
        }
        console.log(aux1);
    }


    return "R$ " + aux1 + "," + valorDepoisVirgula;

}