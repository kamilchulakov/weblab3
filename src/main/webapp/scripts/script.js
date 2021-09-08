let answerValues = document.getElementById("answerValues"),
    pointer = document.getElementById("pointer"),
    grad = "linear-gradient(to right, red,orange,yellow,green,blue,indigo,violet)",
    pickedBtn = false,
    X = 0,
    Y = 0,
    R = 1,
    statusBar = document.getElementById("status"),
    statusImg = document.getElementById("cat"),
    arrayButton=document.getElementsByClassName("buttonR");
let buttonItems=[].slice.call(arrayButton);

// setPointer()

buttonItems.forEach(function choose(btn, index){
    btn.addEventListener('click', function () {
        buttonItems.forEach(
            function disable(btn2, index2) {
                if (index2 !== index) {
                    btn2.style.backgroundImage=""
                }
            }
            )
        R = btn.value
        pickedBtn = true
        btn.style.backgroundImage=grad;
        }
    )
})

function checkX() {
    let X_group = document.getElementsByName("x-radio");
    let flag = true
    for (let i = 0; i < X_group.length; i++) {
        if (X_group[i].checked) {
            flag = false
            X = X_group[i].value
            return true
        }
    }
    if (flag) statusBar.textContent="Выберите X";
    return false
}
function checkY() {
    let Y_text = document.getElementById('Y-text');
    let Y2 = Y_text.value.replace(',', '.')
    if (Y2.trim() === "") {
        statusBar.textContent="Заполните поле Y";
        return false;
    } else if (!isFinite(Y2)) {
        statusBar.textContent="Должно Y быть числом";
        return false;
    } else if (Y2 >= 5 && Y2 <= -3) {
        statusBar.textContent="Должно быть Y в диапазоне (-3; 5)";
        return false;
    } else {
        if (Y2 >= -3 && Y2 <= 5) {
            Y = Y2
            return true
        }
        else {
            statusBar.textContent="Должно быть Y в диапазоне (-3; 5)";
            return false;
        }
    }
}

function checkR() {
    return pickedBtn
}

// function setPointer() {
//     pointer.setAttribute("visibility", "visible");
//     pointer.setAttribute("cx", (X / R * 2 * 60 + 150).toString());
//     pointer.setAttribute("cy", (-Y / R * 2 * 60 + 150).toString());
// }

function processSubmit() {
    $.ajax({
        type: "GET",
        url: "controller",
        data: {
            "x": X,
            "y": Y,
            "r": R
        },
        success: function () {
            document.location.reload();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            statusImg.hidden=false
        }
    });
}

document.getElementById("clearButton").onclick = function clear() {
    $.ajax({
        type: "DELETE",
        url: "controller",
        success: function () {
            document.location.reload();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            statusImg.hidden=false
        }
    });
}

// $('svg').mousedown(function (e) {
//     if (R) {
//         const position = $('.svg-wrapper').offset();
//         const rowX = e.pageX - position.left;
//         const rowY = e.pageY - position.top;
//         X = (((R / 50) * (150 - rowX) * -1) / 2).toFixed(1);
//         Y = (((R / 50) * (150 - rowY)) / 2).toFixed(1);
//
//         processSubmit()
//
//     } else statusBar.textContent="Выберите R next time, please."
// })
document.getElementById("svgField").onmousedown = function submit(event) {
    const svgSize = 300;
    let rowX = event.x - $('.svg-wrapper').offset().left
    let rowY = event.y - $('.svg-wrapper').offset().top
    X = (((R / 50) * (svgSize / 2 - rowX) * -1) / 2).toFixed(1);
    Y = (((R / 50) * (svgSize / 2 - rowY)) / 2).toFixed(1);
    if (checkR()) {
        processSubmit()
    }else statusBar.textContent="Выберите R next time, please."
}

document.getElementById("submitButton").onclick = function submit() {
    // statusBar.textContent="Be ready to get some errors here."
    if (checkX()) {
        if (checkY()) {
            if (checkR()) {
                processSubmit()
            }else statusBar.textContent="Выберите R next time, please."
        }
    }
}