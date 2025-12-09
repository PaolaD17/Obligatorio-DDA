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

document.getElementById("btnListarReproducciones").addEventListener("click", listarReproducciones);
document.getElementById("btnListarPromedio").addEventListener("click", listarPromedio);

// CONTENIDOS CON MÁS DE N REPRODUCCIONES
function listarReproducciones() {
    const cantidad = document.getElementById("inputReproducciones").value;

    fetch(`/api/reportes/contenidos-mas-reproducidos/${cantidad}`)
        .then(res => res.json())
        .then(data => {

            const tabla = document.getElementById("tablaReproducciones");
            const mensaje = document.getElementById("mensajeVacio");
            const contenedorTabla = document.getElementById("tablaResultado");

            tabla.innerHTML = "";
            mensaje.innerHTML = "";
            contenedorTabla.style.display = "table";

            if (data.length === 0) {
                contenedorTabla.style.display = "none";
                mensaje.innerHTML = "❌ No hay contenido para esa cantidad de reproducciones";
                return;
            }

            data.forEach(c => {
                tabla.innerHTML += `
                    <tr>
                        <td>${c.titulo}</td>
                        <td>${c.totalReproducciones}</td>
                    </tr>
                `;
            });

        })
        .catch(err => console.error("ERROR:", err));
}

// CARGAR SELECT CON CONTENIDOS
fetch("/api/contenidos")
    .then(res => res.json())
    .then(data => {
        const select = document.getElementById("selectContenido");

        data.forEach(c => {
            select.innerHTML += `
                <option value="${c.id}">${c.titulo}</option>
            `;
        });
    });

// PROMEDIO DE CALIFICACIONES
function listarPromedio() {
    const id = document.getElementById("selectContenido").value;

    fetch(`/api/reportes/promedio-calificacion/${id}`)
        .then(res => res.json())
        .then(data => {
            document.getElementById("resultadoPromedio").innerHTML =
                `⭐ Promedio de calificación: <strong>${data.promedio}</strong>`;
        });
}
