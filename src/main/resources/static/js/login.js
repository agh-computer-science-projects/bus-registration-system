function showregform() {
    document.querySelector(".vldauth").style.display = "none";
    document.querySelector(".vldreg").style.display = "flex";
}

function showauthform() {
    document.querySelector(".vldauth").style.display = "flex";
    document.querySelector(".vldreg").style.display = "none";
    document.querySelector(".vldrecpass").style.display = "none";
}

function showrecoveryform() {
    document.title = "Password recovery";
    document.querySelector(".vldauth").style.display = "none";
    document.querySelector(".vldrecpass").style.display = "flex";
}
