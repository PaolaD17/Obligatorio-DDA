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

// Form submit
const formulario = document.getElementById("formContenido");

formulario.addEventListener("submit", async function(e) {
    e.preventDefault();

    const portada = document.getElementById("portadaFile").files[0];
    const trailer = document.getElementById("trailerFile").files[0];

    if (!portada || !trailer) {
        alert("Selecciona ambos archivos");
        return;
    }

    const formData = new FormData();
    formData.append("titulo", document.getElementById("titulo").value);
    formData.append("descripcion", document.getElementById("descripcion").value);
    formData.append("categoria", document.getElementById("categoria").value);
    formData.append("duracion", document.getElementById("duracion").value);
    formData.append("anioEstreno", document.getElementById("anioEstreno").value);
    formData.append("tipoOperacion", document.getElementById("tipoOperacion").value);
    formData.append("precioSuscripcion", document.getElementById("precioSuscripcion").value);
    formData.append("exclusivoPremium", document.getElementById("exclusivoPremium").checked);
    formData.append("portada", portada);
    formData.append("trailer", trailer);

    try {
        const res = await fetch("http://localhost:8080/api/contenidos/upload", {
            method: "POST",
            body: formData
        });

        if (!res.ok) {
            const errorData = await res.json().catch(() => ({}));
            alert("Error al guardar contenido: " + (errorData.error || "Desconocido"));
            return;
        }

        const data = await res.json();
        alert("Contenido agregado correctamente");
        window.location.href = "/contenidos";

    } catch (error) {
        alert("Error al guardar contenido: " + error.message);
        console.error(error);
    }
});
