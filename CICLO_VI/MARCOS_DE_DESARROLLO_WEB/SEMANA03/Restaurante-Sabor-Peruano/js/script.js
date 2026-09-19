/**
 * ==========================================================================
 * Restaurante Sabor Peruano - Lógica de Interacción & Validación
 * Responsable Técnico: Max Anderson Benites Corazón
 * ==========================================================================
 */

document.addEventListener("DOMContentLoaded", function () {
    const formReserva = document.getElementById("formReserva");
    const alertaExito = document.getElementById("alertaExito");

    if (formReserva) {
        formReserva.addEventListener("submit", function (event) {
            event.preventDefault();
            event.stopPropagation();

            // Comprobación de validez con estilos nativos de Bootstrap
            if (!formReserva.checkValidity()) {
                formReserva.classList.add("was-validated");
                return;
            }

            // Si es válido, mostramos alerta visual dentro del modal
            alertaExito.classList.remove("d-none");
            formReserva.classList.remove("was-validated");
            formReserva.reset();

            // Ocultar alerta después de 4 segundos y cerrar modal
            setTimeout(() => {
                alertaExito.classList.add("d-none");
                const modalElement = document.getElementById("modalReserva");
                const modalInstance = bootstrap.Modal.getInstance(modalElement);
                if (modalInstance) {
                    modalInstance.hide();
                }
            }, 3000);
        });
    }
});
