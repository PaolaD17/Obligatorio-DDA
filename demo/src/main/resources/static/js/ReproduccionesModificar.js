const apiUrl = "http://localhost:8080/api/reproducciones";

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

// Función para modificar una reproducción
function modificarReproduccion() {
    const id = document.getElementById("reproduccionId").value;
    const calificacion = parseInt(document.getElementById("calificacion").value);

    const reproduccion = { calificacion: calificacion };

    fetch(`${apiUrl}/${id}`, {
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
        // opcional: redirigir o recargar página
    })
    .catch(err => console.error(err));
}

// Capturar submit del formulario
document.getElementById("formReproduccion").addEventListener("submit", function(e) {
    e.preventDefault();
    modificarReproduccion();
});