const buttons = document.querySelectorAll(".button");
let inputs = document.querySelectorAll("input[name='btn']");
const range = document.querySelector(".range-view-history");








document.addEventListener("DOMContentLoaded", function () {
   
    buttons.forEach((button, index) => {
        button.addEventListener("click", function () {
            buttons.forEach((button)=>{
                button.style.color = "";
            })
    
            button.style.color = "yellowgreen";
            inputs[index].checked = true;
            
            if (index == 0) range.style.left = "3%";
            if (index == 1) range.style.left = "36%";
            if (index == 2) range.style.left = "70%";

        });

    });

    console.log("inputs");
    // console.log(buttons);
});
