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

    fetch(`/api/contenidos/${id}`)
        .then(response => response.json())
        .then(contenido => {

            console.log("CONTENIDO RECIBIDO:", contenido);

            document.getElementById("titulo").value = contenido.titulo;
            document.getElementById("descripcion").value = contenido.descripcion;
            document.getElementById("categoria").value = contenido.categoria;
            document.getElementById("duracion").value = contenido.duracion;
            document.getElementById("anioEstreno").value = contenido.anioEstreno;
            document.getElementById("tipoOperacion").value = contenido.tipoOperacion?.toUpperCase().trim();
            document.getElementById("precioSuscripcion").value = contenido.precioSuscripcion;
            document.getElementById("exclusivoPremium").checked = contenido.exclusivoPremium === true;
        });

    document.getElementById("formContenido").addEventListener("submit", function (e) {
        e.preventDefault();

        const contenidoActualizado = {
            titulo: document.getElementById("titulo").value,
            descripcion: document.getElementById("descripcion").value,
            categoria: document.getElementById("categoria").value,
            duracion: Number(document.getElementById("duracion").value),
            anioEstreno: Number(document.getElementById("anioEstreno").value),
            tipoOperacion: document.getElementById("tipoOperacion").value,
            precioSuscripcion: Number(document.getElementById("precioSuscripcion").value),
            exclusivoPremium: document.getElementById("exclusivoPremium").checked
        };

        fetch(`/api/contenidos/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(contenidoActualizado)
        })
            .then(async response => {
                if (!response.ok) {
                    let errorMessage = "Error desconocido";
                    try {
                        const errorData = await response.json();
                        if (errorData.message) errorMessage = errorData.message;
                    } catch {
                        errorMessage = await response.text();
                    }
                    alert(errorMessage);
                    return;
                }
                alert("Contenido modificado correctamente");
                window.location.href = `/contenidos`;
            });
    });
});