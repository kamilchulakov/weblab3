let svgField = document.getElementById("svgField"),
    svgWrapper = document.getElementById("svg-wrapper"),
    submitter = document.getElementById("submitter"),
    defaultSvg = svgField.innerHTML;

window.onload = function () {
    defaultSvg = svgField.innerHTML
    justGetAllSvg()
    svgWrapper.onmousedown = function submit(event) {
        const svgSize = 300;
        let rowX = event.offsetX
        let rowY = event.offsetY
        let R = document.getElementById("form:r").getAttribute("value");
        let X = (((R / 50) * (svgSize / 2 - rowX) * -1) / 2).toFixed(1);
        let Y = (((R / 50) * (svgSize / 2 - rowY)) / 2).toFixed(1);
        updateSvgAndSubmit(X, Y, R);
    }
    submitter.onclick = function () {
        justGetNewSvg()
    }
}

function clear() {
    svgField.innerHTML = defaultSvg
}

function updateSvgAndSubmit(X, Y, R) {
    let lastSvg = svgField.innerHTML;
    $.ajax({
        type: "GET",
        url: "points",
        data: {
            "x": X,
            "y": Y,
            "r": R
        },
        success: function (data) {
            svgField.innerHTML = lastSvg + data
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
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
        }
    });
}

function justGetNewSvg() {
    let lastSvg = svgField.innerHTML;
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
        }
    });
}
