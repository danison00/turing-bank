const buttons = document.querySelectorAll(".button");
let inputs = document.querySelectorAll("input[name='btn']");
const range = document.querySelector(".range");

console.log(buttons);

document.addEventListener("DOMContentLoaded", function () {
   
    buttons.forEach((button, index) => {
        button.addEventListener("click", function () {
            buttons.forEach((button)=>{
                button.style.color = "";
            })
    
            button.style.color = "rgba(0, 128, 0, 0.267)";
            inputs[index].checked = true;
            if (index == 0) range.style.left = "3%";
            if (index == 1) range.style.left = "calc(36%";
            if (index == 2) range.style.left = "calc(70%";

        });

    });

    console.log("inputs");
    // console.log(buttons);
});
