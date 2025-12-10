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

document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get("id");
    fetch(`/api/contenidos/${id}`)

    if (id) {
        fetch(`/api/contenidos/${id}`)
            .then(res => res.json())
            .then(contenido => {
                document.getElementById("titulo").textContent = contenido.titulo;
                document.getElementById("descripcion").textContent = contenido.descripcion;
                document.getElementById("categoria").textContent = contenido.categoria;
                document.getElementById("duracion").textContent = contenido.duracion;
                document.getElementById("anioEstreno").textContent = contenido.anioEstreno;
                document.getElementById("tipoOperacion").textContent = contenido.tipoOperacion;
                document.getElementById("precioSuscripcion").textContent = contenido.precioSuscripcion;
                document.getElementById("exclusivoPremium").textContent = contenido.exclusivoPremium ? "Sí" : "No";
                document.getElementById("portada").src = contenido.portadaUrl;
                document.getElementById("trailerSrc").src = contenido.trailerUrl;
                document.getElementById("trailer").load();

                document.getElementById("modificarBtn").onclick = () => {
                    window.location.href = `/contenidos/modificar/${contenido.id}`;
                };
                document.getElementById("eliminarBtn").onclick = () => {
                    if (confirm("¿Desea eliminar este contenido?")) {
                        fetch(`/api/contenidos/${contenido.id}`, { method: 'DELETE' })
                            .then(() => window.location.href = '/contenidos');
                    }
                };
            });
    }
});

const btnModificar = document.getElementById('modificarBtn');

btnModificar.addEventListener('click', () => {
    window.location.href = '/ContenidosModificar.html';
});