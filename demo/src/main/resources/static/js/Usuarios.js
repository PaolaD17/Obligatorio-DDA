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

fetch("http://localhost:8080/api/usuarios")
    .then(response => response.json())
    .then(data => {
        const tabla = document.getElementById("tablaUsuarios");
        tabla.innerHTML = "";

        data.forEach(usuario => {
            const fila = document.createElement("tr");

            fila.innerHTML = `
                <td>${usuario.id}</td>
                <td>${usuario.nombreCompleto}</td>
                <td>${usuario.email}</td>
                <td>${usuario.fechaRegistro}</td>
                <td>${usuario.fechaInicioMembresia ? "Premium" : "Estándar"}</td>
                <td>${usuario.fechaInicioMembresia ?? "-"}</td>
                <td>${usuario.reproducciones ? usuario.reproducciones.length : 0}</td>
                <td>
                    <button onclick="editarUsuario(${usuario.id})">Modificar</button>
                    <button onclick="eliminarUsuario(${usuario.id})">Eliminar</button>
                </td>
            `;

            tabla.appendChild(fila);
        });
    })
    .catch(error => console.error("Error al cargar usuarios:", error));

function eliminarUsuario(id) {
    if (!confirm("¿Seguro que deseas eliminar este usuario?")) return;

    fetch(`http://localhost:8080/api/usuarios/${id}`, {
        method: "DELETE"
    })
        .then(() => location.reload());
}

function editarUsuario(id) {
    window.location.href = `/usuarios/modificar/${id}`;
}