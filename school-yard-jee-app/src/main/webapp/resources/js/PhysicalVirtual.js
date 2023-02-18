const physicalButton = document.getElementById("physicalbtn");

const physicalForm = document.getElementById("physicalForm");
const virtualForm = document.getElementById("virtualForm");

physicalButton.addEventListener('click', function () {
   if (physicalButton.value == "Pour un cours en présentiel") {
       physicalButton.value = "Pour un cours en distanciel";
   } else {
       physicalButton.value = "Pour un cours en présentiel";
   }
    physicalForm.classList.toggle('hidden');
    virtualForm.classList.toggle('hidden');
});

