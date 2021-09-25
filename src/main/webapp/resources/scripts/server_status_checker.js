let statusImg = document.getElementById("cat");
check()
window.onload = function () {
    window.setInterval(check, 500);
}

function check() {
    fetch("" ,{
        method: "OPTIONS",
    }).then( function () {
            statusImg.setAttribute("src", "resources/img/200.png")
        }
    ).catch(function () {
        statusImg.setAttribute("src", "resources/img/503.png")
    })
}

