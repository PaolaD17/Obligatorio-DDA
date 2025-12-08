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
        alert("No se recibió el ID del usuario");
        return;
    }

    document.getElementById("usuarioId").value = id;

    // 🔹 TRAER USUARIO Y CARGAR FORMULARIO
    fetch(`/api/usuarios/${id}`)
        .then(response => response.json())
        .then(usuario => {

            document.getElementById("nombreCompleto").value = usuario.nombreCompleto;
            document.getElementById("email").value = usuario.email;
            document.getElementById("fechaRegistro").value = usuario.fechaRegistro;

            if (usuario.fechaInicioMembresia) {
                // ES PREMIUM
                document.getElementById("tipoUsuario").value = "PREMIUM";
                document.getElementById("campoPremium").classList.remove("oculto");
                document.getElementById("fechaMembresia").value = usuario.fechaInicioMembresia;
            } else {
                // ES ESTÁNDAR
                document.getElementById("tipoUsuario").value = "ESTANDAR";
                document.getElementById("campoPremium").classList.add("oculto");
            }
        });


    // 🔹 MOSTRAR / OCULTAR FECHA PREMIUM
    document.getElementById("tipoUsuario").addEventListener("change", function () {
        const campoPremium = document.getElementById("campoPremium");
        campoPremium.classList.toggle("oculto", this.value !== "PREMIUM");
    });

    // 🔹 GUARDAR CAMBIOS
    document.getElementById("formUsuario").addEventListener("submit", function (e) {
        e.preventDefault();

        const usuarioActualizado = {
            nombreCompleto: document.getElementById("nombreCompleto").value,
            email: document.getElementById("email").value,
            fechaRegistro: document.getElementById("fechaRegistro").value,
            tipoUsuario: document.getElementById("tipoUsuario").value,
            fechaMembresia: document.getElementById("fechaMembresia").value || null
        };

        fetch(`/api/usuarios/${id}`, {

            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(usuarioActualizado)
        })
            .then(response => {
                if (response.ok) {
                    alert("Usuario modificado correctamente");
                    window.location.href = "/usuarios";
                } else {
                    alert("Error al modificar usuario");
                }
            });
    });
});
