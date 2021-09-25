let answerValues = document.getElementById("answerValues"),
    pointer = document.getElementById("pointer"),
    pointsPlace = document.getElementById("svgField")
    grad = "linear-gradient(to right, red,orange,yellow,green,blue,indigo,violet)",
    pickedBtn = false,
    X = 0,
    Y = 0,
    R = 1,
    statusBar = document.getElementById("status"),
    statusImg = document.getElementById("cat"),
    arrayButton=document.getElementsByClassName("buttonR");
let buttonItems=[].slice.call(arrayButton);

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
        changeStatus("Заполните поле Y");
        return false;
    } else if (!isFinite(Y2)) {
        changeStatus("Должно Y быть числом");
        return false;
    } else if (Y2 >= 5 && Y2 <= -3) {
        changeStatus("Должно быть Y в диапазоне (-3; 5)");
        return false;
    } else {
        if (Y2 >= -3 && Y2 <= 5) {
            Y = Y2
            return true
        }
        else {
            changeStatus("Должно быть Y в диапазоне (-3; 5)");
            return false;
        }
    }
}

function checkR() {
    if (pickedBtn) {
        // let points = [].slice.call(document.getElementsByClassName("pointer"));
        // alert(points.length)
        // for (let i = 0; i < points.length; i++) {
        //     let pointer = points[i]
        //     pointer.setAttribute("cx", (X / R * 2 * 60 + 150).toString());
        //     pointer.setAttribute("cy", (-Y / R * 2 * 60 + 150).toString());
        // }
    }
    return pickedBtn
}

function processSubmit() {
    $.ajax({
        type: "GET",
        url: "controller",
        data: {
            "x": X,
            "y": Y,
            "r": R
        },
        success: function (data) {
            answerValues.innerHTML=data.split("kotlin")[0]
            pointsPlace.innerHTML="<polygon fill=\"#1976d2\" fill-opacity=\"1\" points=\"150,210 270,150 150,150\"></polygon>\n" +
                "        <rect fill=\"#1976d2\" fill-opacity=\"1\" x=\"150\" y=\"90\" height=\"60\" width=\"120\"></rect>\n" +
                "        <g transform=\"translate(150,150)\">\n" +
                "            <path d=\"M0 0 -120 0 A120 115 0 0 1 0 -120\" fill=\"#1976d2\"/>\n" +
                "        </g>\n" +
                "        <line stroke=\"black\" x1=\"0\" x2=\"300\" y1=\"150\" y2=\"150\"></line>\n" +
                "        <line stroke=\"black\" x1=\"150\" x2=\"150\" y1=\"0\" y2=\"300\"></line>\n" +
                "\n" +
                "        <text x=\"275\" y=\"143\">R</text>\n" +
                "        <line x1=\"270\" x2=\"270\" y1=\"148\" y2=\"152\"></line>\n" +
                "\n" +
                "        <text x=\"215\" y=\"143\">R/2</text>\n" +
                "        <line x1=\"210\" x2=\"210\" y1=\"148\" y2=\"152\"></line>\n" +
                "\n" +
                "        <text x=\"90\" y=\"143\">-R/2</text>\n" +
                "        <line x1=\"90\" x2=\"90\" y1=\"148\" y2=\"152\"></line>\n" +
                "\n" +
                "        <text x=\"30\" y=\"143\">-R</text>\n" +
                "        <line x1=\"30\" x2=\"30\" y1=\"148\" y2=\"152\"></line>\n" +
                "\n" +
                "        <text x=\"150\" y=\"26\">R</text>\n" +
                "        <line x1=\"148\" x2=\"152\" y1=\"30\" y2=\"30\"></line>\n" +
                "\n" +
                "        <text x=\"150\" y=\"86\">R/2</text>\n" +
                "        <line x1=\"148\" x2=\"152\" y1=\"90\" y2=\"90\"></line>\n" +
                "\n" +
                "        <text x=\"150\" y=\"210\">-R/2</text>\n" +
                "        <line x1=\"148\" x2=\"152\" y1=\"210\" y2=\"210\"></line>\n" +
                "\n" +
                "        <text x=\"150\" y=\"280\">-R</text>\n" +
                "        <line x1=\"148\" x2=\"152\" y1=\"270\" y2=\"270\"></line>\n" +
                "\n" +
                "        <circle r=\"2\" stroke=\"black\" cx=\"150\" cy=\"150\"></circle>\n" +
                "\n" +
                "\n" +
                "        <polygon fill=\"black\" points=\"300,150 295,145 295,155\" stroke=\"black\"></polygon>\n" +
                "        <polygon fill=\"black\" points=\"150,0  145,5   155,5\" stroke=\"black\"></polygon>" + data.split("kotlin")[1]
            // document.querySelectorAll('[id=pointer]').forEach(element => {
            //     element.setAttribute("cx", (X / R * 2 * 60 + 150).toString());
            //     element.setAttribute("cy", (-Y / R * 2 * 60 + 150).toString());
            // })
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            statusImg.hidden=false
        }
    });
}

function changeStatus(text) {
    statusBar.textContent=text
    statusBar.style.display = "inline-block"
}

statusBar.style.display = "none";

document.getElementById("clearButton").onclick = function clear() {
    $.ajax({
        type: "DELETE",
        url: "controller",
        success: function (data) {
            if (data === "Cleared") {
                answerValues.innerHTML=""
                pointsPlace.innerHTML = "<polygon fill=\"#1976d2\" fill-opacity=\"1\" points=\"150,210 270,150 150,150\"></polygon>\n" +
                    "        <rect fill=\"#1976d2\" fill-opacity=\"1\" x=\"150\" y=\"90\" height=\"60\" width=\"120\"></rect>\n" +
                    "        <g transform=\"translate(150,150)\">\n" +
                    "            <path d=\"M0 0 -120 0 A120 115 0 0 1 0 -120\" fill=\"#1976d2\"/>\n" +
                    "        </g>\n" +
                    "        <line stroke=\"black\" x1=\"0\" x2=\"300\" y1=\"150\" y2=\"150\"></line>\n" +
                    "        <line stroke=\"black\" x1=\"150\" x2=\"150\" y1=\"0\" y2=\"300\"></line>\n" +
                    "\n" +
                    "        <text x=\"275\" y=\"143\">R</text>\n" +
                    "        <line x1=\"270\" x2=\"270\" y1=\"148\" y2=\"152\"></line>\n" +
                    "\n" +
                    "        <text x=\"215\" y=\"143\">R/2</text>\n" +
                    "        <line x1=\"210\" x2=\"210\" y1=\"148\" y2=\"152\"></line>\n" +
                    "\n" +
                    "        <text x=\"90\" y=\"143\">-R/2</text>\n" +
                    "        <line x1=\"90\" x2=\"90\" y1=\"148\" y2=\"152\"></line>\n" +
                    "\n" +
                    "        <text x=\"30\" y=\"143\">-R</text>\n" +
                    "        <line x1=\"30\" x2=\"30\" y1=\"148\" y2=\"152\"></line>\n" +
                    "\n" +
                    "        <text x=\"150\" y=\"26\">R</text>\n" +
                    "        <line x1=\"148\" x2=\"152\" y1=\"30\" y2=\"30\"></line>\n" +
                    "\n" +
                    "        <text x=\"150\" y=\"86\">R/2</text>\n" +
                    "        <line x1=\"148\" x2=\"152\" y1=\"90\" y2=\"90\"></line>\n" +
                    "\n" +
                    "        <text x=\"150\" y=\"210\">-R/2</text>\n" +
                    "        <line x1=\"148\" x2=\"152\" y1=\"210\" y2=\"210\"></line>\n" +
                    "\n" +
                    "        <text x=\"150\" y=\"280\">-R</text>\n" +
                    "        <line x1=\"148\" x2=\"152\" y1=\"270\" y2=\"270\"></line>\n" +
                    "\n" +
                    "        <circle r=\"2\" stroke=\"black\" cx=\"150\" cy=\"150\"></circle>\n" +
                    "\n" +
                    "\n" +
                    "        <polygon fill=\"black\" points=\"300,150 295,145 295,155\" stroke=\"black\"></polygon>\n" +
                    "        <polygon fill=\"black\" points=\"150,0  145,5   155,5\" stroke=\"black\"></polygon>"
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            statusImg.hidden=false
        }
    });
}

document.getElementById("svgField").onmousedown = function submit(event) {
    const svgSize = 300;
    let rowX = event.offsetX
    let rowY = event.offsetY
    X = (((R / 50) * (svgSize / 2 - rowX) * -1) / 2).toFixed(1);
    Y = (((R / 50) * (svgSize / 2 - rowY)) / 2).toFixed(1);
    if (checkR()) {
        processSubmit()
    }else changeStatus("Выберите R next time, please.")
}

document.getElementById("submitButton").onclick = function submit() {
    if (checkX()) {
        if (checkY()) {
            if (checkR()) {
                processSubmit()
            }else changeStatus("Выберите R next time, please.")
        }
    }
}