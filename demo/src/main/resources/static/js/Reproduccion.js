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