
document.getElementById("navbar-mobile-list").innerHTML = document.getElementById("navbar-desktop").innerHTML;

/* Commande du burgerMenu*/
const icone = document.querySelector('.navbar-mobile i');

const modal = document.querySelector('.modal');

const header = document.getElementById("header");

let indexMenu = 0;

icone.addEventListener('click', function () {

    modal.classList.toggle('change-modal');
    icone.classList.toggle('fa-times');
    if (indexMenu % 2 === 0) {
        changerHeader();
    } else {
        setTimeout(changerHeader, 400);
    }
    indexMenu ++;
});

function changerHeader() {
    header.classList.toggle('change');
}