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

document.getElementById("btnFiltrarUsuarios").addEventListener("click", filtrarUsuarios);

function filtrarUsuarios() {

    const desde = document.getElementById("fechaDesde").value;
    const hasta = document.getElementById("fechaHasta").value;

    fetch(`/api/reportes/usuarios?desde=${desde}&hasta=${hasta}`)
        .then(res => res.json())
        .then(data => {

            const premium = data.filter(u => u.tipoUsuario === "premium");
            const estandar = data.filter(u => u.tipoUsuario === "estandar");

            const tbodyPremium = document.getElementById("tablaUsuariosPremium");
            const tbodyEstandar = document.getElementById("tablaUsuariosEstandar");

            const msgPremium = document.getElementById("mensajePremium");
            const msgEstandar = document.getElementById("mensajeEstandar");

            tbodyPremium.innerHTML = "";
            tbodyEstandar.innerHTML = "";
            msgPremium.innerHTML = "";
            msgEstandar.innerHTML = "";

            if (premium.length === 0) {
                document.getElementById("tablaPremium").style.display = "none";
                msgPremium.innerHTML = "No hay usuarios registrados en ese rango de fechas";
            } else {
                document.getElementById("tablaPremium").style.display = "table";

                premium.forEach(u => {
                    tbodyPremium.innerHTML += `
                        <tr>
                            <td>${u.nombreCompleto}</td>
                            <td>${u.email}</td>
                            <td>${u.fechaRegistro}</td>
                            <td>${u.fechaMembresia}</td>
                        </tr>
                    `;
                });
            }
            
            if (estandar.length === 0) {
                document.getElementById("tablaEstandar").style.display = "none";
                msgEstandar.innerHTML = "No hay usuarios registrados en ese rango de fechas";
            } else {
                document.getElementById("tablaEstandar").style.display = "table";

                estandar.forEach(u => {
                    tbodyEstandar.innerHTML += `
                        <tr>
                            <td>${u.nombreCompleto}</td>
                            <td>${u.email}</td>
                            <td>${u.fechaRegistro}</td>
                        </tr>
                    `;
                });
            }

        })
        .catch(err => console.error("ERROR:", err));
}