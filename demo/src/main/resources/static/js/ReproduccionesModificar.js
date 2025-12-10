// Dropdowns
const dropdowns = document.querySelectorAll(".dropdown-btn");
dropdowns.forEach(btn => {
    btn.addEventListener("click", function (e) {
        e.preventDefault();
        const parent = this.parentElement;
        document.querySelectorAll(".dropdown").forEach(drop => {
            if (drop !== parent) drop.classList.remove("open");
        });
        parent.classList.toggle("open");
    });
});

document.addEventListener("click", function (e) {
    if (!e.target.closest(".dropdown")) {
        document.querySelectorAll(".dropdown").forEach(drop => drop.classList.remove("open"));
    }
});

const apiUrl = "http://localhost:8080/api/reproducciones";

// 1️⃣ Leer ID de la URL y precargar datos
const params = new URLSearchParams(window.location.search);
const reproduccionId = params.get("id");
document.getElementById("reproduccionId").value = reproduccionId;

// Precargar la reproducción existente
fetch(`${apiUrl}/${reproduccionId}`)
    .then(res => res.json())
    .then(data => {
        document.getElementById("calificacion").value = data.calificacion;
    })
    .catch(err => console.error("Error al cargar reproducción:", err));

document.addEventListener("click", function (e) {
    if (!e.target.closest(".dropdown")) {
        document.querySelectorAll(".dropdown").forEach(drop => drop.classList.remove("open"));
    }
});

// 3️⃣ Función para modificar la reproducción
function modificarReproduccion() {
    const calificacion = parseInt(document.getElementById("calificacion").value);

    const reproduccion = { calificacion: calificacion };

    fetch(`${apiUrl}/${reproduccionId}`, {  // usamos la variable del ID
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(reproduccion)
    })
    .then(res => {
        if (!res.ok) throw new Error("Error al modificar");
        return res.json();
    })
    .then(data => {
        alert("Reproducción modificada correctamente");
        window.location.href = "/reproducciones"; // opcional: volver a la lista
    })
    .catch(err => console.error(err));
}

// 4️⃣ Capturar submit del formulario
document.getElementById("formReproduccion").addEventListener("submit", function(e) {
    e.preventDefault();
    modificarReproduccion();
});
