const physicalButton = document.getElementById("physicalbtn");

const physicalForm = document.getElementById("physicalForm");
const virtualForm = document.getElementById("virtualForm");

physicalButton.addEventListener('click', function () {
   if (physicalButton.value == "Créer cours en présentiel") {
       physicalButton.value = "Créer cours en distanciel";
   } else {
       physicalButton.value = "Créer cours en présentiel";
   }
    physicalForm.classList.toggle('hidden');
    virtualForm.classList.toggle('hidden');
});

