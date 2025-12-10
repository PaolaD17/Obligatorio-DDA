document.addEventListener("DOMContentLoaded", () => {

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

    fetch("/api/usuarios")
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("selectUsuario");

            data.forEach(u => {
                select.innerHTML += `
                    <option value="${u.id}">
                        ${u.nombreCompleto} (${u.email})
                    </option>
                `;
            });
        });

    document.getElementById("btnListarReproducciones")
        .addEventListener("click", listarReproducciones);

    function listarReproducciones() {

        const usuarioId = document.getElementById("selectUsuario").value;
        const tabla = document.getElementById("tablaReproducciones");
        const mensaje = document.getElementById("mensajeVacio");

        tabla.innerHTML = "";
        mensaje.innerHTML = "";

        if (!usuarioId) {
            mensaje.innerHTML = "⚠️ Debe seleccionar un usuario";
            return;
        }

        fetch(`/api/reportes/reproducciones-por-usuario/${usuarioId}`)
            .then(res => res.json())
            .then(data => {

                if (data.length === 0) {
                    mensaje.innerHTML = "❌ Este usuario no tiene reproducciones";
                    return;
                }

                data.forEach(r => {
                    tabla.innerHTML += `
                        <tr>
                            <td>${r.id}</td>
                            <td>${r.usuario.nombreCompleto}</td>
                            <td>${r.contenido.titulo}</td>
                            <td>${r.fechaHora}</td>
                            <td>${r.duracionMinutos} min</td>
                            <td>${r.calificacion}</td>
                        </tr>
                    `;
                });
            })
            .catch(err => {
                console.error(err);
                mensaje.innerHTML = "❌ Error al cargar las reproducciones";
            });
    }

});

document.getElementById("btnListarPorFecha")
    .addEventListener("click", listarContenidosPorFecha);

function listarContenidosPorFecha() {

    const fecha = document.getElementById("inputFecha").value;
    const tabla = document.getElementById("tablaContenidosFecha");
    const mensaje = document.getElementById("mensajeFecha");

    tabla.innerHTML = "";
    mensaje.innerHTML = "";

    if (!fecha) {
        mensaje.innerHTML = "⚠️ Debe seleccionar una fecha";
        return;
    }

    fetch(`/api/reportes/contenidos-por-fecha?fecha=${fecha}`)
        .then(res => res.json())
        .then(data => {

            if (data.length === 0) {
                mensaje.innerHTML = "❌ No hay contenidos reproducidos en esa fecha";
                return;
            }

            data.forEach(c => {
                tabla.innerHTML += `
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.titulo}</td>
                        <td>${c.categoria}</td>
                        <td>${c.duracion}</td>
                        <td>$${c.precioSuscripcion}</td>
                        <td>${c.exclusivoPremium ? "Sí" : "No"}</td>
                    </tr>
                `;
            });
        })
        .catch(err => {
            console.error(err);
            mensaje.innerHTML = "❌ Error al cargar los contenidos";
        });
}
