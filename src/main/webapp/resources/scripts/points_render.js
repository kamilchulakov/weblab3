let svgField = document.getElementById("svgField"),
    svgWrapper = document.getElementById("svg-wrapper"),
    defaultSvg;

window.onload = function () {
    defaultSvg = svgField.innerHTML
    svgWrapper.onmousedown = function submit(event) {
        const svgSize = 300;
        let rowX = event.offsetX
        let rowY = event.offsetY
        let R = document.getElementById("form:r").getAttribute("value");
        let X = (((R / 50) * (svgSize / 2 - rowX) * -1) / 2).toFixed(1);
        let Y = (((R / 50) * (svgSize / 2 - rowY)) / 2).toFixed(1);
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
                }
            });

    }

}
