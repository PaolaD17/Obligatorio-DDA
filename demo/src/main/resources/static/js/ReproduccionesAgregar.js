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

// Cargar usuarios
fetch("http://localhost:8080/api/usuarios")
    .then(res => res.json())
    .then(usuarios => {
        const select = document.getElementById("usuario");
        usuarios.forEach(u => {
            const option = document.createElement("option");
            option.value = u.id;
            option.textContent = u.nombreCompleto + " - " + u.email;
            select.appendChild(option);
        });
    })
    .catch(err => console.error("Error al cargar usuarios:", err));

document.getElementById("usuario").addEventListener("change", function () {
    const usuarioId = this.value;

    if (!usuarioId) return; // por seguridad

    cargarContenidosParaUsuario(usuarioId);
});

function cargarContenidosParaUsuario(usuarioId) {
    fetch(`http://localhost:8080/api/reproducciones/contenidos-usuario/${usuarioId}`)
        .then(res => res.json())
        .then(contenidos => {
            const select = document.getElementById("contenido");
            select.innerHTML = "";

            contenidos.forEach(c => {
                const option = document.createElement("option");
                option.value = c.id;
                option.textContent =
                    `${c.titulo} - ${c.categoria} - $${c.precioSuscripcion}`;

                select.appendChild(option);
            });
        })
        .catch(err => console.error("Error al cargar contenidos:", err));
}


// Enviar formulario
const form = document.getElementById("formUsuario");
form.addEventListener("submit", function (e) {
    e.preventDefault();

    const usuarioId = document.getElementById("usuario").value;
    const contenidoId = document.getElementById("contenido").value;
    const fechaHora = document.getElementById("fechaHora").value;
    const duracionMinutos = parseInt(document.getElementById("duracionMinutos").value);
    const calificacion = parseInt(document.getElementById("calificacion").value);

    const data = {
        usuarioId: parseInt(usuarioId),
        contenidoId: parseInt(contenidoId),
        fechaHora: fechaHora,
        duracionMinutos: duracionMinutos,
        calificacion: calificacion
    };


    fetch("http://localhost:8080/api/reproducciones", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    })
        .then(res => {
            if (!res.ok) throw new Error("Error al agregar la reproducción");
            return res.json();
        })
        .then(reproduccion => {
            alert("Reproducción agregada correctamente con ID.");
            window.location.href = "/reproducciones";
        })
        .catch(err => alert(err.message));
});