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

// ✅ 1) CONTENIDOS CON MÁS DE N REPRODUCCIONES
function listarReproducciones() {
    const cantidad = document.getElementById("inputReproducciones").value;

    fetch(`/api/reportes/contenidos-mas-reproducidos/${cantidad}`)
        .then(res => res.json())
        .then(data => {
            const tabla = document.getElementById("tablaReproducciones");
            tabla.innerHTML = "";

            data.forEach(c => {
                tabla.innerHTML += `
                    <tr>
                        <td>${c[0]}</td>   // TITULO
                        <td>${c[1]}</td>   // TOTAL REPRODUCCIONES
                    </tr>
                `;
            });

        });
}

// ✅ 2) CARGAR SELECT CON CONTENIDOS
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

// ✅ 3) PROMEDIO DE CALIFICACIONES
function listarPromedio() {
    const id = document.getElementById("selectContenido").value;

    fetch(`/api/reportes/promedio-calificacion/${id}`)
        .then(res => res.json())
        .then(data => {
            document.getElementById("resultadoPromedio").innerHTML =
                `⭐ Promedio de calificación: <strong>${data.promedio}</strong>`;
        });
}
