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

fetch("http://localhost:8080/api/reproducciones")
    .then(response => response.json())
    .then(data => {
        const tabla = document.getElementById("tablaReproducciones");
        tabla.innerHTML = "";

        data.forEach(reproduccion => {
            const fila = document.createElement("tr");

            fila.innerHTML = `
                <td>${reproduccion.id}</td>
                <td>${reproduccion.usuario.nombreCompleto}</td>
                <td>${reproduccion.contenido.titulo}</td>
                <td>${reproduccion.fechaHora}</td>
                <td>
                    <button onclick="editarReproduccion(${reproduccion.id})">Modificar</button>
                    <button onclick="eliminarReproduccion(${reproduccion.id})">Eliminar</button>
                </td>
            `;

            tabla.appendChild(fila);
        });
    })
    .catch(error => console.error("Error al cargar reproducciones:", error));

function eliminarReproduccion(id) {
    if (!confirm("¿Seguro que deseas eliminar esta reproducción?")) return;

    fetch(`http://localhost:8080/api/reproducciones/${id}`, {
        method: "DELETE"
    })
        .then(() => location.reload());
}

function editarReproduccion(id) {
    window.location.href = `/reproducciones/modificar?id=${id}`;
}