document.addEventListener('DOMContentLoaded', function() {
    // --- Gestión del menú desplegable en dispositivos móviles ---
    const menuToggle = document.querySelector('.menu-toggle');
    const nav = document.querySelector('nav');

    if (menuToggle) {
        menuToggle.addEventListener('click', () => {
            nav.classList.toggle('open');
            menuToggle.classList.toggle('open');
            const isExpanded = menuToggle.getAttribute('aria-expanded') === 'true' || false;
            menuToggle.setAttribute('aria-expanded', !isExpanded);
        });
    }

    // --- Control del carrusel de investigadores ---
    const investigadoresContainer = document.getElementById('investigadores-container');
    const flechaIzquierda = document.querySelector('.flecha-izquierda');
    const flechaDerecha = document.querySelector('.flecha-derecha');

    if (investigadoresContainer && flechaIzquierda && flechaDerecha) {
        flechaIzquierda.addEventListener('click', () => {
            investigadoresContainer.scrollBy({ left: -280, behavior: 'smooth' }); // Ajustar el valor según el ancho del investigador
        });

        flechaDerecha.addEventListener('click', () => {
            investigadoresContainer.scrollBy({ left: 280, behavior: 'smooth' }); // Ajustar el valor según el ancho del investigador
        });

        // Ocultar flechas si no hay suficiente contenido para hacer scroll
        const updateFlechaVisibility = () => {
            if (investigadoresContainer.scrollWidth <= investigadoresContainer.clientWidth) {
                flechaIzquierda.style.display = 'none';
                flechaDerecha.style.display = 'none';
            } else {
                flechaIzquierda.style.display = 'block';
                flechaDerecha.style.display = 'block';
            }
        };

        updateFlechaVisibility();
        window.addEventListener('resize', updateFlechaVisibility);
    }

    // --- Abrir y cerrar las modales ---
    const modalTriggers = document.querySelectorAll('[data-modal]');
    const closeButtons = document.querySelectorAll('.close-button');
    const modals = document.querySelectorAll('.modal');

    modalTriggers.forEach(trigger => {
        trigger.addEventListener('click', () => {
            const modalId = trigger.getAttribute('data-modal');
            const modal = document.getElementById(modalId);
            if (modal) {
                modal.style.display = 'block';
                modal.setAttribute('aria-hidden', 'false');
                // Focus en el botón de cerrar al abrir la modal para accesibilidad
                const firstFocusableElement = modal.querySelector('.close-button');
                if (firstFocusableElement) {
                    firstFocusableElement.focus();
                }
            }
        });
    });

    closeButtons.forEach(button => {
        button.addEventListener('click', () => {
            const modal = button.closest('.modal');
            if (modal) {
                modal.style.display = 'none';
                modal.setAttribute('aria-hidden', 'true');
                // Volver el foco al elemento que abrió la modal (si se puede rastrear)
                const triggeringElement = document.querySelector(`[data-modal="${modal.id}"]`);
                if (triggeringElement) {
                    triggeringElement.focus();
                }
            }
        });
    });

    modals.forEach(modal => {
        modal.addEventListener('click', (event) => {
            if (event.target === modal) {
                modal.style.display = 'none';
                modal.setAttribute('aria-hidden', 'true');
                // Volver el foco al elemento que abrió la modal (si se puede rastrear)
                const triggeringElement = document.querySelector(`[data-modal="${modal.id}"]`);
                if (triggeringElement) {
                    triggeringElement.focus();
                }
            }
        });
    });

    // --- Accesibilidad para elementos que abren modales ---
    modalTriggers.forEach(trigger => {
        trigger.addEventListener('keydown', (event) => {
            if (event.key === 'Enter' || event.key === ' ') {
                const modalId = trigger.getAttribute('data-modal');
                const modal = document.getElementById(modalId);
                if (modal) {
                    modal.style.display = 'block';
                    modal.setAttribute('aria-hidden', 'false');
                    const firstFocusableElement = modal.querySelector('.close-button');
                    if (firstFocusableElement) {
                        firstFocusableElement.focus();
                    }
                }
            }
        });
    });

    // --- Accesibilidad para cerrar modales con teclado ---
    closeButtons.forEach(button => {
        button.addEventListener('keydown', (event) => {
            if (event.key === 'Enter' || event.key === ' ') {
                const modal = button.closest('.modal');
                if (modal) {
                    modal.style.display = 'none';
                    modal.setAttribute('aria-hidden', 'true');
                    const triggeringElement = document.querySelector(`[data-modal="${modal.id}"]`);
                    if (triggeringElement) {
                        triggeringElement.focus();
                    }
                }
            }
        });
    });
});