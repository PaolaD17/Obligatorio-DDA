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
    .then(usuarios => {
        const select = document.getElementById("usuario");

        usuarios.forEach(usuario => {
            const option = document.createElement("option");
            option.value = usuario.id; // se guarda el ID
            option.textContent = usuario.nombreCompleto + " - " + usuario.email;
            select.appendChild(option);
        });
    })
    .catch(error => {
        console.error("Error al cargar usuarios:", error);
    });

fetch("http://localhost:8080/api/contenidos")
    .then(response => response.json())
    .then(contenidos => {
        const select = document.getElementById("contenido");

        contenidos.forEach(contenido => {
            const option = document.createElement("option");
            option.value = contenido.id;

            option.textContent =
                contenido.titulo + " - " +
                contenido.categoria + " - " +
                contenido.tipoOperacion + " - $" +
                contenido.costo;

            select.appendChild(option);
        });
    })
    .catch(error => {
        console.error("Error al cargar contenidos:", error);
    });