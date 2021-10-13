window.onload = function () {
    setTimeout(check, 0)
    setInterval(check, 500);
}

function check() {
    fetch("" ,{
        method: "OPTIONS",
    }).then( function (res) {
            statusImg.setAttribute("src", "resources/img/200.png");
            if (res.status !== 200) statusImg.setAttribute("src", "resources/img/" + res.statusText + ".jpg");
        }
    ).catch(function () {
        statusImg.setAttribute("src", "resources/img/503.png")
    })
}

