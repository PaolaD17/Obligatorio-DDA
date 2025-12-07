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

const formulario = document.getElementById("formContenido");

formulario.addEventListener("submit", function (e) {
    e.preventDefault();

    const contenido = {
        titulo: document.getElementById("titulo").value,
        descripcion: document.getElementById("descripcion").value,
        categoria: document.getElementById("categoria").value,
        duracion: document.getElementById("duracion").value,
        anioEstreno: document.getElementById("anioEstreno").value,
        tipoOperacion: document.getElementById("tipoOperacion").value,
        precioSuscripcion: document.getElementById("precioSuscripcion").value,
        portadaUrl: document.getElementById("portadaUrl").value,
        trailerUrl: document.getElementById("trailerUrl").value,
        exclusivoPremium: document.getElementById("exclusivoPremium").checked
    };

    fetch("http://localhost:8080/api/contenidos", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(contenido)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(text);
                });
            }
            return response.json();
        })
        .then(data => {
            alert("Contenido agregado correctamente");
            window.location.href = "/contenidos";
        })
        .catch(error => {
            alert("Error al guardar contenido" + error.message);
            console.error(error);
        });
});

document.getElementById("uploadBtn").addEventListener("click", async () => {
  const portada = document.getElementById("portadaFile").files[0];
  const trailer = document.getElementById("trailerFile").files[0];

  if (!portada || !trailer) {
    alert("Selecciona ambos archivos");
    return;
  }

  const formData = new FormData();
  formData.append("portada", portada);
  formData.append("trailer", trailer);

  const res = await fetch("/api/upload/media", {
    method: "POST",
    body: formData
  });

  const data = await res.json();
  console.log("Archivos subidos:", data);
});