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

fetch("http://localhost:8080/api/contenidos")
    .then(response => response.json())
    .then(data => {
        const cuerpo = document.getElementById("tablaCuerpo");
        cuerpo.innerHTML = ""; // Limpiamos solo el cuerpo

        data.forEach(contenido => {
            const fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${contenido.id}</td>
                <td>${contenido.titulo}</td>
                <td>${contenido.descripcion}</td>
                <td>${contenido.categoria}</td>
                <td>${contenido.duracion}</td>
                <td>${contenido.anioEstreno}</td>
                <td>${contenido.tipoOperacion}</td>
                <td>$${contenido.precioSuscripcion}</td>
                <td>${contenido.exclusivoPremium ? 'Sí' : 'No'}</td>
                <td><img src="${contenido.portadaUrl}" alt="Portada" width="100"></td>
                <td>
                    <video width="150" controls>
                        <source src="${contenido.trailerUrl}" type="video/mp4">
                        Tu navegador no soporta el video.
                    </video>
                </td>
                <td>
                    <button onclick="eliminarContenido(${contenido.id})">Eliminar</button>
                    <button onclick="editarContenido(${contenido.id})">Editar</button>
                </td>
            `;
            cuerpo.appendChild(fila);
        });

    })
    .catch(error => console.error("Error al cargar contenidos:", error));


function eliminarContenido(id) {
    if (!confirm("¿Seguro que deseas eliminar este contenido?")) return;

    fetch(`http://localhost:8080/api/contenidos/${id}`, {
        method: "DELETE"
    })
        .then(() => location.reload());
}

function editarContenido(id) {
    window.location.href = `/contenidos/editar/${id}`;
}