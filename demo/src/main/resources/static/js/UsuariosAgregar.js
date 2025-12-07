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

        const hoy = new Date().toISOString().split("T")[0];
        document.getElementById("fechaRegistro").value = hoy;

        const tipoUsuario = document.getElementById("tipoUsuario");
        const campoPremium = document.getElementById("campoPremium");
        const fechaMembresia = document.getElementById("fechaMembresia");

        tipoUsuario.addEventListener("change", function () {
            if (this.value === "PREMIUM") {
                campoPremium.classList.remove("oculto");
                campoPremium.classList.add("visible");
                fechaMembresia.required = true;
            } else {
                campoPremium.classList.remove("visible");
                campoPremium.classList.add("oculto");
                fechaMembresia.required = false;
                fechaMembresia.value = "";
            }
        });

        const formulario = document.getElementById("formUsuario");

        formulario.addEventListener("submit", function (e) {
            e.preventDefault();

            const usuario = {
                nombreCompleto: document.getElementById("nombre").value,
                email: document.getElementById("email").value,
                fechaRegistro: document.getElementById("fechaRegistro").value,
                tipoUsuario: document.getElementById("tipoUsuario").value,
                fechaMembresia: document.getElementById("tipoUsuario").value === "PREMIUM"
                    ? document.getElementById("fechaMembresia").value
                    : null
            };

            fetch("http://localhost:8080/api/usuarios", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(usuario)
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
                    alert("Usuario agregado correctamente");
                    window.location.href = "/Usuarios.html";
                    document.getElementById("fechaRegistro").value = new Date().toISOString().split("T")[0];
                    document.getElementById("campoPremium").classList.add("oculto");
                })
                .catch(error => {
                    alert("Error al guardar usuario");
                });

        });