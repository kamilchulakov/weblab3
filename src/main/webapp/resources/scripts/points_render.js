let svgField = document.getElementById("svgField"),
    svgWrapper = document.getElementById("svg-wrapper"),
    submitter = document.getElementById("form:sumbitter"),
    rInput = document.getElementById("form:r"),
    statusImg = document.getElementById("cat"),
    defaultSvg = svgField.innerHTML;

window.onload = function () {
    defaultSvg = svgField.innerHTML
    justGetAllSvg()
    svgWrapper.onmousedown = function submit(event) {
        const svgSize = 300;
        let rowX = event.offsetX
        let rowY = event.offsetY
        let R = rInput.getAttribute("value");
        let X = (((svgSize / 2 - rowX) * -R) / 120 - 0.7).toFixed(1);
        let Y = (((R / 60) * (svgSize / 2 - rowY)) / 2 + 0.7).toFixed(1);
        updateSvgAndSubmit(X, Y, R);
        updateHtmlTable()
    }
    submitter.addEventListener('click', function () {
        // alert("Click") this works
        // setTimeout(function(){
        //     console.log('timedout');
        // },300);
        justGetAllSvg()
    })
}

function updateHtmlTable() {
    let table = document.getElementById("result-table");
    let tableData = table.innerHTML;
    $.ajax({
        type: "GET",
        url: "table",
        success: function (data) {
            table.innerHTML =  data + tableData
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            showError503Cat()
        }
    });
}

function clear() {
    svgField.innerHTML = defaultSvg
}

function updateSvgAndSubmit(X, Y, R) {
    $.ajax({
        type: "GET",
        url: "points",
        data: {
            "x": X,
            "y": Y,
            "r": R
        },
        success: function (data) {
            svgField.innerHTML = defaultSvg + data
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            showError503Cat()
        }
    });
}



function justGetAllSvg() {
    let R = document.getElementById("form:r").getAttribute("value");
    $.ajax({
        type: "GET",
        url: "points",
        data: {
            "x": -1000,
            "y": 0,
            "r": R
        },
        success: function (data) {
            svgField.innerHTML = defaultSvg + data
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            showError503Cat()
        }
    });
}

function showError503Cat() {
    statusImg.setAttribute("src", "resources/img/503.png")
}

function justGetNewSvg() {
    let lastSvg = svgField.innerHTML;
    let R = document.getElementById("form:r").getAttribute("value");
    $.ajax({
        type: "GET",
        url: "points",
        data: {
            "x": 0,
            "y": -1000,
            "r": R
        },
        success: function (data) {
            svgField.innerHTML = lastSvg + data
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            showError503Cat();
        }
    });
}
