const dropdowns = document.querySelectorAll(".dropdown-btn");
dropdowns.forEach(btn => {
    btn.addEventListener("click", function (e) {
        e.preventDefault();

        const parent = this.parentElement;

        document.querySelectorAll(".dropdown").forEach(drop => {
            if (drop !== parent) {
                drop.classList.remove("open");
            }
        });
        parent.classList.toggle("open");
    });
});

document.addEventListener("click", function (e) {
    if (!e.target.closest(".dropdown")) {
        document.querySelectorAll(".dropdown").forEach(drop => {
            drop.classList.remove("open");
        });
    }
});

document.addEventListener("DOMContentLoaded", () => {

    const partes = window.location.pathname.split("/");
    const id = partes[partes.length - 1];

    if (!id) {
        alert("No se recibió el ID del contenido");
        return;
    }

    document.getElementById("contenidoId").value = id;

    // 🔹 TRAER CONTENIDO Y CARGAR FORMULARIO
    fetch(`/api/contenidos/${id}`)
        .then(response => response.json())
        .then(contenido => {

            console.log("CONTENIDO RECIBIDO:", contenido); // ✅ debug

            document.getElementById("titulo").value = contenido.titulo;
            document.getElementById("descripcion").value = contenido.descripcion;
            document.getElementById("categoria").value = contenido.categoria;
            document.getElementById("duracion").value = contenido.duracion;
            document.getElementById("anioEstreno").value = contenido.anioEstreno;
            document.getElementById("tipoOperacion").value = contenido.tipoOperacion?.toUpperCase().trim();
            document.getElementById("precioSuscripcion").value = contenido.precioSuscripcion;
            document.getElementById("exclusivoPremium").checked = contenido.exclusivoPremium === true;
        });

    // 🔹 GUARDAR CAMBIOS
    document.getElementById("formContenido").addEventListener("submit", function (e) {
        e.preventDefault();

        const contenidoActualizado = {
            titulo: document.getElementById("titulo").value,
            descripcion: document.getElementById("descripcion").value,
            categoria: document.getElementById("categoria").value,
            duracion: document.getElementById("duracion").value,
            anioEstreno: document.getElementById("anioEstreno").value,
            tipoOperacion: document.getElementById("tipoOperacion").value,
            precioSuscripcion: document.getElementById("precioSuscripcion").value,
            exclusivoPremium: document.getElementById("exclusivoPremium").checked
        };

        fetch(`/api/contenidos/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(contenidoActualizado)
        })
            .then(response => {
                if (response.ok) {
                    alert("Contenido modificado correctamente");
                    window.location.href = `/contenidos`;
                } else {
                    alert("Error al modificar contenido");
                }
            });
    });

});