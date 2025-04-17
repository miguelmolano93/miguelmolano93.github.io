document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.querySelector('.menu-toggle');
    const nav = document.querySelector('nav');
    const flechaIzquierda = document.querySelector('.flecha-izquierda');
    const flechaDerecha = document.querySelector('.flecha-derecha');
    const investigadoresContainer = document.getElementById('investigadores-container');
    const modalTriggers = document.querySelectorAll('[data-modal]');
    const closeButtons = document.querySelectorAll('.close-button');
    const investigadorCards = document.querySelectorAll('#investigadores-container .investigador');
    let investigadorIndex = 0;
    const investigadoresVisible = 3; // Adjust based on your layout

    if (menuToggle && nav) {
        menuToggle.addEventListener('click', () => {
            const expanded = menuToggle.getAttribute('aria-expanded') === 'true' || false;
            menuToggle.setAttribute('aria-expanded', !expanded);
            nav.style.display = expanded ? 'none' : 'flex'; /* Adjust to 'block' if stacking */
        });
    }

    if (investigadoresContainer && flechaIzquierda && flechaDerecha) {
        flechaIzquierda.addEventListener('click', () => {
            investigadoresContainer.scrollLeft -= 300; // Adjust scroll amount as needed
        });

        flechaDerecha.addEventListener('click', () => {
            investigadoresContainer.scrollLeft += 300; // Adjust scroll amount as needed
        });
    }

    modalTriggers.forEach(trigger => {
        trigger.addEventListener('click', (event) => {
            event.preventDefault();
            const modalId = trigger.getAttribute('data-modal');
            const modal = document.getElementById(modalId);
            if (modal) {
                modal.style.display = 'block';
            }
        });
    });

    closeButtons.forEach(button => {
        button.addEventListener('click', () => {
            const modal = button.closest('.modal');
            if (modal) {
                modal.style.display = 'none';
            }
        });
    });

    window.addEventListener('click', (event) => {
        if (event.target.classList.contains('modal')) {
            event.target.style.display = 'none';
        }
    });
});
}